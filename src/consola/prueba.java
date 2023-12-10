package consola;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;

public class prueba {

	public static void main(String[] args) {
        try {
            // Step 1: Create a new document
            PDDocument document = new PDDocument();

            // Step 2: Add a page to the document
            PDPage page = new PDPage();
            document.addPage(page);

            // Step 3: Create a content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Step 4: Add content to the page
            contentStream.beginText();
            //contentStream.setFont(Standard14Fonts.getMappedFontName("HELVETICA_BOLD").HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Hello, this is a sample PDF file created using Apache PDFBox.");
            contentStream.endText();

            // Step 5: Close the content stream
            contentStream.close();

            // Step 6: Save the document to a file
            document.save("example_apache.pdf");

            // Step 7: Close the document
            document.close();

            System.out.println("PDF created successfully with Apache PDFBox.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
