package com.winja.application;

import com.winja.application.in.GeoLocationUseCase;
import com.winja.domain.GeoLocDetails;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@Service
public class GeoLocationService implements GeoLocationUseCase {

    public GeoLocDetails parse(InputStream inputStream) throws IOException, ParserConfigurationException, SAXException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        HandlerService handler = new HandlerService();
        parser.parse(inputStream, handler);
        return handler.getUpdate();

    }
}
