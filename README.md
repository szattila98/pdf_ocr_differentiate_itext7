# pdf_ocr_differentiate
A pdf OCR software that differentiates OCR image text from non-OCR text. It uses iText7.

## Usage

- Build with

`.\mvnw clean package`

- Run jar with dependencies in target dir. Alternatively run pre-packaged jar in dist dir.

`java -jar pdf_ocr_differentiate_itext7-0.0.1-SNAPSHOT-jar-with-dependencies.jar pdf.pdf`

- The result is a PDF doc with the OCR image text.

## Requirements
- Java 16