package ch.dachs.pdf_ocr_differentiate_itext7;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants.TextRenderingMode;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import ch.dachs.pdf_ocr_differentiate_itext7.core.TextLine;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TextInfoExtractionStrategy implements ITextExtractionStrategy {

	private static final int MAX_WORD_OFFSET_Y = 5;
	private static final int MAX_WORD_OFFSET_X = 10;
	private static final int X = 0;
	private static final int Y = 1;
	
	private final List<TextLine> textLines;
	
	@Override
	public void eventOccurred(IEventData data, EventType type) {
		TextRenderInfo renderInfo = (TextRenderInfo) data;
		var segment = renderInfo.getBaseline();
		
		var trimmed = renderInfo.getText()/*.trim()*/;
		if (!textLines.isEmpty()) {
			var lastLine = textLines.get(textLines.size() - 1);
			var newLine = new TextLine(trimmed, segment.getStartPoint(), segment.getEndPoint(), renderInfo.getTextRenderMode());
			
			// coupling text that should be one line
			if (Math.abs(newLine.getStartPoint().get(Y) - lastLine.getStartPoint().get(Y)) < MAX_WORD_OFFSET_Y
					&& newLine.getStartPoint().get(X) - lastLine.getEndPoint().get(X) < MAX_WORD_OFFSET_X) { // TODO try renderingmode == 
				lastLine.concatTextLine(newLine);
			} else {
				textLines.add(new TextLine(trimmed, segment.getStartPoint(), segment.getEndPoint(), renderInfo.getTextRenderMode()));
			}
			
		} else {
			textLines.add(new TextLine(trimmed, segment.getStartPoint(), segment.getEndPoint(), renderInfo.getTextRenderMode()));
		}
		
		//System.out.println(renderInfo.getText()  + " | " +" "+ segment.getStartPoint()  +" "+ segment.getEndPoint() + " | " + renderInfo.getTextRenderMode());
	}

    @Override
    public Set<EventType> getSupportedEvents() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(Collections.singletonList(EventType.RENDER_TEXT)));
    }

    /**
     * Returns the result so far.
     * @return	a String with the resulting text.
     */
    @Override
    public String getResultantText() {
        return "";
    }

}
