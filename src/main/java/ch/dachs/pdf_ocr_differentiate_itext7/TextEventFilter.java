package ch.dachs.pdf_ocr_differentiate_itext7;

import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.filter.IEventFilter;

public class TextEventFilter implements IEventFilter {

	@Override
	public boolean accept(IEventData data, EventType type) {
		return type == EventType.RENDER_TEXT;
	}
}
