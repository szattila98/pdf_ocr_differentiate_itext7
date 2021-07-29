package ch.dachs.pdf_ocr_differentiate_itext7.stripper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import ch.dachs.pdf_ocr_differentiate_itext7.core.TextLine;

/**
 * Extension of PDFTextStripper. Strips text, word character positions and
 * rendering modes of characters from a page.
 * 
 * @author Szőke Attila
 */
public class TextAndTextStateStripper {

//	private static final int MAX_WORD_OFFSET_Y = 5;
//	private static final int MAX_WORD_OFFSET_X = 15;
//
//	private final List<TextLine> textLines;
//	private final Map<TextPosition, RenderingMode> characterRenderingModes;
//
//	/**
//	 * Basic constructor. Sets the result list and rendering mode map.
//	 * 
//	 * @param documentImageCaptions result list
//	 * @throws IOException thrown when the pdf cannot be processed
//	 */
//	public TextAndTextStateStripper(List<TextLine> textLines, Map<TextPosition, RenderingMode> characterRenderingModes)
//			throws IOException {
//		this.textLines = textLines;
//		this.characterRenderingModes = characterRenderingModes;
//	}
//
//	/**
//	 * Called when, getText is called. It is overridden so it extracts the text, and
//	 * text positions.
//	 */
//	@Override
//	public void writeString(String text, List<TextPosition> textPositions) throws IOException {
//		var trimmed = replaceNotSupportedChars(text.trim());
//		if (!textLines.isEmpty()) {
//			var lastLine = textLines.get(textLines.size() - 1);
//			var newLine = new TextLine(trimmed, textPositions);
//			// coupling text that should be one line but pdfbox broke it up
//			if (Math.abs(newLine.getYPosition() - lastLine.getYPosition()) < MAX_WORD_OFFSET_Y
//					&& newLine.getFirstCharacterXPosition()
//							- lastLine.getLastCharacterXPosition() < MAX_WORD_OFFSET_X) {
//				lastLine.concatTextLine(newLine);
//			} else {
//				textLines.add(new TextLine(trimmed, textPositions));
//			}
//		} else {
//			textLines.add(new TextLine(trimmed, textPositions));
//		}
//		super.writeString(trimmed, textPositions);
//	}
//
//	/**
//	 * Called before writeString. Gets every character on the page and their
//	 * rendering modes into a map.
//	 * 
//	 * @param character the character
//	 */
//	@Override
//	protected void processTextPosition(TextPosition character) {
//		characterRenderingModes.put(character, getGraphicsState().getTextState().getRenderingMode());
//		super.processTextPosition(character);
//	}
//
//	/**
//	 * Replaces not supported characters.
//	 * 
//	 * @param text the text
//	 * @return text without not supported characters
//	 */
//	private String replaceNotSupportedChars(String text) {
//		var notSup = List.of("τ");
//		var newStr = text;
//		for (String ch : notSup) {
//			newStr = newStr.replace(ch, "");
//		}
//		return newStr;
//	}
}