package ch.dachs.pdf_ocr_differentiate_itext7.extraction;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IEventListener;

import ch.dachs.pdf_ocr_differentiate_itext7.core.TextLine;
import lombok.RequiredArgsConstructor;

/**
 * This class describes a strategy that is used to extract lines of text.
 * 
 * @author Attila Szőke
 */
@RequiredArgsConstructor
public class TextInfoExtractionStrategy implements IEventListener {

	private static final int MAX_WORD_OFFSET_Y = 5;
	private static final int MAX_WORD_OFFSET_X = 15;

	private final List<TextLine> textLines;

	@Override
	public void eventOccurred(IEventData data, EventType type) {
		TextRenderInfo renderInfo = (TextRenderInfo) data;
		var segment = renderInfo.getBaseline();
		var text = renderInfo.getText();
		if (!textLines.isEmpty()) {
			var lastLine = textLines.get(textLines.size() - 1);
			var newLine = new TextLine(text, segment.getStartPoint(), segment.getEndPoint(),
					renderInfo.getTextRenderMode());
			// coupling text that should be one line
			if (Math.abs(newLine.getFirstCharacterY() - lastLine.getFirstCharacterY()) < MAX_WORD_OFFSET_Y
					&& newLine.getFirstCharacterX() - lastLine.getLastCharacterX() < MAX_WORD_OFFSET_X) {
				lastLine.concatTextLine(newLine);
			} else {
				textLines.add(new TextLine(text, segment.getStartPoint(), segment.getEndPoint(),
						renderInfo.getTextRenderMode()));
			}

		} else {
			textLines.add(
					new TextLine(text, segment.getStartPoint(), segment.getEndPoint(), renderInfo.getTextRenderMode()));
		}
	}

	@Override
	public Set<EventType> getSupportedEvents() {
		return Collections.unmodifiableSet(new LinkedHashSet<>(Collections.singletonList(EventType.RENDER_TEXT)));
	}
}
