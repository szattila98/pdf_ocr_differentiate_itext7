package ch.dachs.pdf_ocr_differentiate_itext7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Writes String results into a PDF file.
 * 
 * @author Szőke Attila
 */
public class ResultWriter {

//	private static final PDFont FONT_TYPE = PDType1Font.TIMES_ROMAN;
//	private static final float FONT_SIZE = 12;
//	private static final float LEADING = 1.6f * FONT_SIZE;
//	private static final float MARGIN = 72;
//
//	/**
//	 * Writes a list of strings into a PDF file.
//	 * 
//	 * @param imageLinesList list of lines per image
//	 * @throws IOException thrown when PDF cannot be written
//	 */
//	public void write(List<List<String>> imageLinesList) throws IOException {
//		try (var document = new PDDocument()) {
//			// page information
//			PDRectangle mediabox = new PDPage().getMediaBox();
//			int allowedWidth = (int) (mediabox.getWidth() - 2 * MARGIN);
//			float startX = mediabox.getLowerLeftX() + MARGIN;
//			float startY = mediabox.getUpperRightY() - MARGIN;
//			var lineNoOnPage = (int) ((mediabox.getHeight() - 1.7 * MARGIN) / LEADING);
//			// breaking long lines
//			List<List<String>> brokenLinesList = new ArrayList<>();
//			for (var imageLines : imageLinesList) {
//				var brokenLines = breakStringToLines(imageLines, allowedWidth);
//				brokenLinesList.add(brokenLines);
//			}
//			// sublists for pages based on lines
//			List<List<String>> lineLists = new ArrayList<>();
//			for (var brokenLines : brokenLinesList) {
//				lineLists.addAll(Lists.partition(brokenLines, lineNoOnPage));
//			}
//			// writing to doc
//			for (var subList : lineLists) {
//				// new page
//				var page = new PDPage();
//				document.addPage(page);
//				// writing to page
//				try (var contentStream = new PDPageContentStream(document, page)) {
//					contentStream.beginText();
//					contentStream.setFont(FONT_TYPE, FONT_SIZE);
//					contentStream.newLineAtOffset(startX, startY);
//					for (var line : subList) {
//						contentStream.showText(line);
//						contentStream.newLineAtOffset(0, -LEADING);
//					}
//					contentStream.endText();
//				}
//			}
//			// saving doc
//			document.save("pdf_ocr_results.pdf");
//		}
//	}
//
//	/**
//	 * Breaks a long String to lines so it does not clip out from a page.
//	 * 
//	 * @param documentLines the list of captions
//	 * @param allowedWidth  the width of writable space
//	 * @return broken list of lines to write
//	 * @throws IOException thrown when there is an error getting font width info
//	 */
//	private List<String> breakStringToLines(List<String> documentLines, int allowedWidth) throws IOException {
//		List<String> brokenLines = new ArrayList<>();
//		for (var docLine : documentLines) {
//			var text = docLine.toString();
//			String[] words = text.split(" ");
//			var newLine = new StringBuilder();
//			for (String word : words) {
//				if (!newLine.isEmpty()) {
//					newLine.append(" ");
//				}
//				int size = (int) (FONT_SIZE * FONT_TYPE.getStringWidth(newLine + word) / 1000);
//				if (size > allowedWidth) {
//					brokenLines.add(newLine.toString());
//					newLine.replace(0, newLine.length(), word);
//				} else {
//					newLine.append(word);
//				}
//			}
//			brokenLines.add(newLine.toString());
//		}
//		return brokenLines;
//	}
}