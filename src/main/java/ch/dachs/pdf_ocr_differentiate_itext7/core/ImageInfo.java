package ch.dachs.pdf_ocr_differentiate_itext7.core;

import com.itextpdf.kernel.geom.Matrix;
import com.itextpdf.kernel.geom.Vector;

import lombok.Data;

/**
 * Represents the dimensions of an image.
 * 
 * @author Szőke Attila
 */
@Data
public class ImageInfo {

	private static final int X = 0;
	private static final int Y = 1;

	private float bottomLeftX;
	private float bottomLeftY;
	private float topRightX;
	private float topRightY;

	/**
	 * Default constructor of ImageInfo.
	 * 
	 * @param startPoint a vector representing the bottom left point of an image
	 * @param ctm        the transformation matrix used to get the image
	 *                   width/height
	 */
	public ImageInfo(Vector startPoint, Matrix ctm) {
		this.bottomLeftX = startPoint.get(X);
		this.bottomLeftY = startPoint.get(Y);
		this.topRightX = this.bottomLeftX + getScalingFactorX(ctm);
		this.topRightY = this.bottomLeftY + getScalingFactorY(ctm);
	}

	/**
	 * Gets the scaled width of the image based on the transformation matrix.
	 * 
	 * @param matrix the transformation matrix of the image
	 * @return the scaled width
	 */
	private float getScalingFactorX(Matrix matrix) {
		if (Float.compare(matrix.get(1), 0.0f) != 0) {
			return (float) Math.sqrt(Math.pow(matrix.get(0), 2) + Math.pow(matrix.get(1), 2));
		}
		return matrix.get(0);
	}

	/**
	 * Gets the scaled height of the image based on the transformation matrix.
	 * 
	 * @param matrix the transformation matrix of the image
	 * @return the scaled height
	 */
	private float getScalingFactorY(Matrix matrix) {
		if (Float.compare(matrix.get(3), 0.0f) != 0) {
			return (float) Math.sqrt(Math.pow(matrix.get(3), 2) + Math.pow(matrix.get(4), 2));
		}
		return matrix.get(4);
	}
}
