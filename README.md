# pdf_ocr_differentiate
A pdf OCR software that differentiates scanned OCR images from real text.

## Usage

- Build with

`.\mvnw clean package`

- Run jar with dependencies in target dir. Alternatively run pre-packaged jar in dist dir.

`java -jar pdf_ocr_differentiate-0.0.1-SNAPSHOT-jar-with-dependencies.jar pdf.pdf`

- The result is a PDF doc with the OCR image text.

## Requirements
- Java 16