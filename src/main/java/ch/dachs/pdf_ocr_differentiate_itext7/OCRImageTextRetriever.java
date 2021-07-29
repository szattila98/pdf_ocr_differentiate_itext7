package ch.dachs.pdf_ocr_differentiate_itext7;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredEventListener;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredTextEventListener;

import ch.dachs.pdf_ocr_differentiate_itext7.core.ImageInfo;
import ch.dachs.pdf_ocr_differentiate_itext7.core.TextLine;
import ch.dachs.pdf_ocr_differentiate_itext7.stripper.ImageInfoStripper;
import ch.dachs.pdf_ocr_differentiate_itext7.stripper.TextAndTextStateStripper;

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
				// retrieve images from the page
				/*var pageImages = new ArrayList<ImageInfo>();
				new ImageInfoStripper(pageImages).processPage(doc.getPage(currentPageNum - 1));
				// retrieve text lines only if there is an image on the page
				if (pageImages.isEmpty()) {
					continue;
				}*/
				var pageTextLines = new ArrayList<TextLine>();
				var listener = new FilteredEventListener();
				var strat = new TextInfoExtractionStrategy(pageTextLines);
				listener.attachEventListener(strat, new TextEventFilter());
				PdfCanvasProcessor processor = new PdfCanvasProcessor(listener);
				processor.processPageContent(doc.getPage(currentPageNum));
				
				for (var textLine : pageTextLines) {
					System.out.println(textLine.getText());
				}
				
				// only retain text lines which are contained by the image and their first
				// character's rendering mode is invisible
				/*var imageTextLines = new ArrayList<TextLine>();
				for (var image : pageImages) {
					pageTextLines.stream().filter(textLine -> textIsInImage(image, textLine))
							.filter(textLine -> pageCharacterRenderingModes
									.get(textLine.firstCharacter()) == RenderingMode.NEITHER)
							.forEach(imageTextLines::add);
				}
				documentTextLinesPerImage.add(imageTextLines);*/
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
//	private boolean textIsInImage(ImageInfo imageInfo, TextLine textLine) {
//		return textLine.getFirstCharacterXPosition() > imageInfo.getBottomLeftX()
//				&& textLine.getFirstCharacterXPosition() < imageInfo.getTopRightX()
//				&& textLine.getLastCharacterXPosition() > imageInfo.getBottomLeftX()
//				&& textLine.getLastCharacterXPosition() < imageInfo.getTopRightX()
//				&& textLine.getYPosition() > imageInfo.getBottomLeftY()
//				&& textLine.getYPosition() < imageInfo.getTopRightY();
//	}
}
