package ch.dachs.pdf_ocr_differentiate_itext7.core;

import com.itextpdf.kernel.geom.Vector;

import lombok.Data;

/**
 * Represents a line of text stripped from the PDF.
 * 
 * @author Szőke Attila
 */
@Data
public class TextLine {

	private static final int X = 0;
	private static final int Y = 1;

	private String text;
	private Vector startPoint;
	private Vector endPoint;
	private int renderingMode;

	/**
	 * Basic constructor. Adds the texts start and end point Vectors and its
	 * rendering mode.
	 * 
	 * @param text          the text
	 * @param startPoint    the first character's coordinate vector
	 * @param endPoint      the last character's coordinate vector
	 * @param renderingMode the rendering mode of the text
	 */
	public TextLine(String text, Vector startPoint, Vector endPoint, int renderingMode) {
		this.text = text;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.renderingMode = renderingMode;
	}

	/**
	 * Concatenates another line to this line.
	 * 
	 * @param textLine the line to concatenate
	 */
	public void concatTextLine(TextLine textLine) {
		this.text = this.text.concat(textLine.getText());
		this.endPoint = textLine.getEndPoint();
	}

	/**
	 * Gets the first character's X coordinate.
	 * 
	 * @return the x coordinate
	 */
	public float getFirstCharacterX() {
		return this.startPoint.get(X);
	}

	/**
	 * Gets the first character's Y coordinate.
	 * 
	 * @return the y coordinate
	 */
	public float getFirstCharacterY() {
		return this.startPoint.get(Y);
	}

	/**
	 * Gets the last character's X coordinate.
	 * 
	 * @return the x coordinate
	 */
	public float getLastCharacterX() {
		return this.endPoint.get(X);
	}

	/**
	 * Gets the last character's Y coordinate.
	 * 
	 * @return the y coordinate
	 */
	public float getLastCharacterY() {
		return this.endPoint.get(Y);
	}
}
