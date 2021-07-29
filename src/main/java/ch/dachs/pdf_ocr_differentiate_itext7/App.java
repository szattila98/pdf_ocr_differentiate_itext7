package ch.dachs.pdf_ocr_differentiate_itext7;

import java.io.IOException;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ch.dachs.pdf_ocr_differentiate_itext7.core.TextLine;

/**
 * Main class of the application.
 * 
 * @author Szőke Attila
 */
public class App {

	private static final String PDF_PATH_ERR_MSG = "Please specify a pdf path to continue with the extraction!";
	private static final String FILE_OPERATION_ERR_MSG = "File cound not be opened/parsed/written!";
	private static final String SUCCESS_MSG = "OCR image text differentiated from real text and extracted to new PDF document!";

	private static final Log logger = LogFactory.getLog(App.class);

	public static void main(String[] args) {
		if (args.length == 0) {
			logger.error(PDF_PATH_ERR_MSG);
			return;
		}
		var retriever = new OCRImageTextRetriever();
		try {
			var textLines = retriever.retrieve(args[0]).stream()
					.map(textLineList -> textLineList.stream().map(TextLine::getText).collect(Collectors.toList()))
					.collect(Collectors.toList());
			
			for (var list : textLines) {
				for (var str : list) {
					System.out.println(str);
				}
			}
			
			// new ResultWriter().write(textLines);
			logger.info(SUCCESS_MSG);
		} catch (IOException e) {
			logger.error(FILE_OPERATION_ERR_MSG);
		}
	}
}
