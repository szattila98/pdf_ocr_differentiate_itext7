package ch.dachs.pdf_ocr_differentiate_itext7.stripper;

import java.io.IOException;
import java.util.List;

import ch.dachs.pdf_ocr_differentiate_itext7.core.ImageInfo;

/**
 * Extension of PDFStreamEngine class. It finds real images and extracts
 * information about them.
 * 
 * @author Szőke Attila
 */
public class ImageInfoStripper {

//	private final List<ImageInfo> pageImages;
//
//	/**
//	 * Basic constructor. Adds all needed operators and result image list.
//	 * 
//	 * @param pageImages the image result list
//	 * @throws IOException operator adding exception
//	 */
//	public ImageInfoStripper(List<ImageInfo> pageImages) throws IOException {
//		addOperator(new Concatenate());
//		addOperator(new DrawObject());
//		addOperator(new SetGraphicsStateParameters());
//		addOperator(new Save());
//		addOperator(new Restore());
//		addOperator(new SetMatrix());
//		this.pageImages = pageImages;
//	}
//
//	/**
//	 * ProcessPage calls this on every object. It checks whether the object is an
//	 * image then adds info about images into the result list.
//	 */
//	@Override
//	protected void processOperator(Operator operator, List<COSBase> operands) throws IOException {
//		String operation = operator.getName();
//		if ("Do".equals(operation)) {
//			COSName objectName = (COSName) operands.get(0);
//			// get the PDF object
//			PDXObject xobject = getResources().getXObject(objectName);
//			// check if the object is an image object
//			if (xobject instanceof PDImage) {
//				// gather image info
//				var trMatrix = getGraphicsState().getCurrentTransformationMatrix();
//				int imageWidth = (int) trMatrix.getScalingFactorX(); // displayed size in user space units
//				int imageHeight = (int) trMatrix.getScalingFactorY(); // displayed size in user space units
//				float xPosition = trMatrix.getTranslateX(); // positions in userSpaceUnits
//				float yPosition = trMatrix.getTranslateY(); // positions in userSpaceUnits
//				if (imageWidth > 1 && imageHeight > 1) {
//					pageImages
//							.add(new ImageInfo(xPosition, yPosition, xPosition + imageWidth, yPosition + imageHeight));
//				}
//			} else if (xobject instanceof PDFormXObject) {
//				PDFormXObject form = (PDFormXObject) xobject;
//				showForm(form);
//			}
//		} else {
//			super.processOperator(operator, operands);
//		}
//	}
}
