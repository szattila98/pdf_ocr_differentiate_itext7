package ch.dachs.pdf_ocr_differentiate_itext7.core;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents and image dimensions.
 * 
 * @author Szőke Attila
 */
@Data
@AllArgsConstructor
public class ImageInfo {
	private float bottomLeftX;
	private float bottomLeftY;
	private float topRightX;
	private float topRightY;
}
