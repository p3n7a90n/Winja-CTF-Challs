package com.winja.adapter.input;

import com.winja.application.HandlerService;
import com.winja.application.in.GeoLocationUseCase;
import com.winja.domain.GeoLocDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class Controller {

    @Autowired
    GeoLocationUseCase geoLocationUseCase;

    @GetMapping("/")
    public ResponseEntity<String> welcome()
    {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to Winja!");

    }

    @PostMapping(value = "/parse",consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity parseXML(@RequestBody String body ) throws ParserConfigurationException, SAXException, IOException {
        try {
            InputStream inputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
            GeoLocDetails geoLocDetails = geoLocationUseCase.parse(inputStream);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        catch (FileNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e)
        {   e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
