package ch.dachs.pdf_ocr_differentiate_itext7;

import java.io.IOException;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;

/**
 * Writes String results into a PDF file.
 * 
 * @author Szőke Attila
 */
public class ResultWriter {

	private static final String RESULT_PDF_NAME = "pdf_ocr_results.pdf";
	private static final float FONT_SIZE = 12;
	private static final float MARGIN = 72;
	private static final String FONT_TYPE = StandardFonts.TIMES_ROMAN;

	/**
	 * Writes lists of strings per image into a PDF file.
	 * 
	 * @param imageLinesList list of text per image
	 * @throws IOException thrown when PDF cannot be written
	 */
	public void write(List<List<String>> imageLinesList) throws IOException {
		try (var doc = new Document(new PdfDocument(new PdfWriter(RESULT_PDF_NAME)))) {
			doc.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
			doc.setFont(PdfFontFactory.createFont(FONT_TYPE));
			doc.setFontSize(FONT_SIZE);
			for (var imageTextList : imageLinesList) {
				for (var text : imageTextList) {
					doc.add(new Paragraph(text));
				}
				if (imageLinesList.indexOf(imageTextList) < imageLinesList.size() - 1) {
					doc.add(new AreaBreak());
				}
			}
		}
	}
}