package com.winja.application.in;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Set;

public interface XMLSearchUseCase {
     Set<String> searchKey(String key) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException;

}
