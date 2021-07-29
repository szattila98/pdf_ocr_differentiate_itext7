package ch.dachs.pdf_ocr_differentiate_itext7.extraction;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.ImageRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IEventListener;

import ch.dachs.pdf_ocr_differentiate_itext7.core.ImageInfo;
import lombok.RequiredArgsConstructor;

/**
 * This class describes a strategy that is used to extract images.
 * 
 * @author Attila Szőke
 */
@RequiredArgsConstructor
public class ImageInfoExtractionStrategy implements IEventListener {

	private final List<ImageInfo> pageImages;

	@Override
	public void eventOccurred(IEventData data, EventType type) {
		ImageRenderInfo imageRenderInfo = (ImageRenderInfo) data;
		pageImages.add(new ImageInfo(imageRenderInfo.getStartPoint(), imageRenderInfo.getImageCtm()));
	}

	@Override
	public Set<EventType> getSupportedEvents() {
		return Collections.unmodifiableSet(new LinkedHashSet<>(Collections.singletonList(EventType.RENDER_IMAGE)));
	}
}
