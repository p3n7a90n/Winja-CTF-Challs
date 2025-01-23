package com.winja.application;

import com.lowagie.text.DocumentException;
import com.winja.application.in.InvoiceUseCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.*;

@Service
public class GenerateInvoiceService implements InvoiceUseCase {

    @Value("${pdf_loc}")
    private String pdfLoc;

    @Override
    public void generateInvoice(String avatarUrl) throws IOException {

        File inputHTML = getResourceAsFile("/template.html");
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.getElementsByTag("img").attr("src",avatarUrl);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        try (OutputStream outputStream = new FileOutputStream(pdfLoc)) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(document.html());
            renderer.layout();
            renderer.createPDF(outputStream);
        } catch (DocumentException | IOException exception) {
            System.out.println("Error generating the invoice");
        }

    }

    private File getResourceAsFile(String fileName){
        try {
            InputStream in = getClass().getResourceAsStream(fileName);
            if (in == null) {
                return null;
            }

            File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            tempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                //copy stream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}

