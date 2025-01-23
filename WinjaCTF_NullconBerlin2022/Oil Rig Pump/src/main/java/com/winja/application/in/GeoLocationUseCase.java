package com.winja.application.in;

import com.winja.domain.GeoLocDetails;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public interface GeoLocationUseCase {

    GeoLocDetails parse(InputStream inputStream) throws IOException, ParserConfigurationException, SAXException;
}
