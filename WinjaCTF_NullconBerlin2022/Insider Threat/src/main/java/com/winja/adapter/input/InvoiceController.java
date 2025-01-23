package com.winja.adapter.input;

import com.winja.application.in.InvoiceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceUseCase invoiceUseCase;

    @Value("${pdf_loc}")
    private String pdfLoc;


    @GetMapping("/")
    public String welcome()
    {
        return "Welcome to Winja CTF!";
    }

        @GetMapping("/getInvoice")
    public ResponseEntity<byte[]> getInvoice(@RequestParam("orderId") String orderId, @RequestParam(name = "avatar", defaultValue = "", required = false) String avatarUrl){

        try {
            invoiceUseCase.generateInvoice(avatarUrl);
        }catch (IOException exception)
        {
            System.out.println("Error retrieving the invoice");
        }

        byte[] fileContent = new byte[]{};
        try {
            fileContent = Files.readAllBytes(Path.of(pdfLoc));

        }

        catch (IOException e)
        {
            System.out.println("Something wrong in reading the file");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = String.format("invoice_%s.pdf",orderId);
        headers.setContentDispositionFormData(filename, filename);
        ResponseEntity<byte[]> response = new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        return response;

    }
}
