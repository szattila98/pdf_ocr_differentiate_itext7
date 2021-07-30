package ch.dachs.pdf_ocr_differentiate_itext7;

import java.io.IOException;
import java.util.stream.Collectors;

import ch.dachs.pdf_ocr_differentiate_itext7.core.TextLine;
import lombok.extern.log4j.Log4j2;

/**
 * Main class of the application.
 * 
 * @author Szőke Attila
 */
@Log4j2
public class App {

	private static final String PDF_PATH_ERR_MSG = "Please specify a pdf path to continue with the extraction!";
	private static final String FILE_OPERATION_ERR_MSG = "File cound not be opened/parsed/written!";
	private static final String SUCCESS_MSG = "OCR image text differentiated from real text and extracted to a new PDF document!";

	public static void main(String[] args) {
		if (args.length == 0) {
			log.error(PDF_PATH_ERR_MSG);
			return;
		}
		var retriever = new OCRImageTextRetriever();
		try {
			var textLines = retriever.retrieve(args[0]).stream()
					.map(textLineList -> textLineList.stream().map(TextLine::getText).collect(Collectors.toList()))
					.collect(Collectors.toList());
			new ResultWriter().write(textLines);
			log.info(SUCCESS_MSG);
		} catch (IOException e) {
			log.error(FILE_OPERATION_ERR_MSG);
		}
	}
}
