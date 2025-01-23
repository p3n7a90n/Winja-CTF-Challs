package com.winja.adapter.input;

import com.winja.application.XMLSearchService;
import com.winja.application.in.XMLSearchUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

@RestController
public class XMLController {


    @Autowired
    private XMLSearchUseCase xmlSearchUseCase;

@GetMapping("/")
    public ResponseEntity welcome(){

    return  ResponseEntity.status(HttpStatus.OK).body("Welcome to Winja CTF!");
}

@PostMapping("/search")
    public ResponseEntity login(@RequestBody String key) {
    Set<String> results = null;
    try {
        results = xmlSearchUseCase.searchKey(key);
    }catch (Exception e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(results);
}

}
