package ch.dachs.pdf_ocr_differentiate_itext7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants.TextRenderingMode;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredEventListener;

import ch.dachs.pdf_ocr_differentiate_itext7.core.ImageInfo;
import ch.dachs.pdf_ocr_differentiate_itext7.core.TextLine;
import ch.dachs.pdf_ocr_differentiate_itext7.extraction.EventTypeFilter;
import ch.dachs.pdf_ocr_differentiate_itext7.extraction.ImageInfoExtractionStrategy;
import ch.dachs.pdf_ocr_differentiate_itext7.extraction.TextInfoExtractionStrategy;

/**
 * Retrieves scanned image text from the document.
 * 
 * @author Szőke Attila
 */
public class OCRImageTextRetriever {

	/**
	 * Retrieves OCR image text from the given document.
	 * 
	 * @param path the PDF doc path
	 * @return the list of text lines
	 * @throws IOException thrown when PDF cannot be processed
	 */
	public List<List<TextLine>> retrieve(String path) throws IOException {
		// open document
		try (var doc = new PdfDocument(new PdfReader(path))) {
			int numberOfPages = doc.getNumberOfPages();
			List<List<TextLine>> documentTextLinesPerImage = new ArrayList<>();
			for (var currentPageNum = 1; currentPageNum < numberOfPages + 1; currentPageNum++) {
				// retrieve images and text from the page
				List<TextLine> pageTextLines = new ArrayList<>();
				List<ImageInfo> pageImages = new ArrayList<>();
				var textStrat = new TextInfoExtractionStrategy(pageTextLines);
				var imageStrat = new ImageInfoExtractionStrategy(pageImages);
				var listener = new FilteredEventListener();
				listener.attachEventListener(textStrat, new EventTypeFilter(EventType.RENDER_TEXT));
				listener.attachEventListener(imageStrat, new EventTypeFilter(EventType.RENDER_IMAGE));
				PdfCanvasProcessor processor = new PdfCanvasProcessor(listener);
				processor.processPageContent(doc.getPage(currentPageNum));
				// only retain text lines which are contained by the image and their rendering
				// mode is invisible
				var imageTextLines = new ArrayList<TextLine>();
				for (var image : pageImages) {
					pageTextLines.stream()
							.filter(textLine -> textIsInImage(image, textLine)
									&& textLine.getRenderingMode() == TextRenderingMode.INVISIBLE)
							.forEach(imageTextLines::add);
				}
				documentTextLinesPerImage.add(imageTextLines);
			}
			return documentTextLinesPerImage;
		}
	}

	/**
	 * Determines whether textLine is in the image or not.
	 * 
	 * @param imageInfo the image
	 * @param textLine  the text line
	 * @return true if line is in the image, otherwise false
	 */
	private boolean textIsInImage(ImageInfo imageInfo, TextLine textLine) {
		return textLine.getFirstCharacterX() > imageInfo.getBottomLeftX()
				&& textLine.getFirstCharacterX() < imageInfo.getTopRightX()
				&& textLine.getLastCharacterX() > imageInfo.getBottomLeftX()
				&& textLine.getLastCharacterX() < imageInfo.getTopRightX()
				&& textLine.getFirstCharacterY() > imageInfo.getBottomLeftY()
				&& textLine.getFirstCharacterY() < imageInfo.getTopRightY();
	}
}
