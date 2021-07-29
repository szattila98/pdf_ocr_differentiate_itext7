package ch.dachs.pdf_ocr_differentiate_itext7.core;

import java.util.List;
import com.itextpdf.kernel.geom.Vector;

import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants.TextRenderingMode;

import lombok.Data;

/**
 * Represents a line of text stripped from the pdf.
 * 
 * @author Szőke Attila
 */
@Data
public class TextLine {
//	private static final String SEPARATOR = " ";
//
	private String text;
	private Vector startPoint;
	private Vector endPoint;
	private int renderingMode;

	/**
	 * Basic constructor. Adds text, character text positions and sets position data
	 * with the help of character positions.
	 * 
	 * @param text               the text of the line
	 * @param characterPositions the character position information
	 */
	public TextLine(String text, Vector vector, Vector vector2, int renderingMode) {
		this.text = text;
		this.startPoint = vector;
		this.endPoint = vector2;
		this.renderingMode = renderingMode;
	}

	/**
	 * Concats another line to this line.
	 * 
	 * @param textLine the line to concat
	 */
	public void concatTextLine(TextLine textLine) {
		this.text = this.text.concat(textLine.getText());
		this.endPoint = textLine.getEndPoint();
	}
}
