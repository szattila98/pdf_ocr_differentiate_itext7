package ch.dachs.pdf_ocr_differentiate_itext7.extraction;

import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.filter.IEventFilter;

import lombok.RequiredArgsConstructor;

/**
 * This class filters the events based on the given type.
 * 
 * @author Attila Szőke
 */
@RequiredArgsConstructor
public class EventTypeFilter implements IEventFilter {

	private final EventType eventType;

	@Override
	public boolean accept(IEventData data, EventType type) {
		return type == eventType;
	}
}
