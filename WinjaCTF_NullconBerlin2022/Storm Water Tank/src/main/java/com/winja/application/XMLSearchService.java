package com.winja.application;

import com.winja.application.in.XMLSearchUseCase;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Service
public class XMLSearchService implements XMLSearchUseCase {

    public Set<String> searchKey(String key) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        Set<String> results = new HashSet<>();

        InputStream inputStream = getClass().getResourceAsStream("/XMLFile.xml");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(inputStream);
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//Contents/Key[contains(.,"+key+")]/text()";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        for(int i=0;i<nodeList.getLength();i++){
            results.add(nodeList.item(i).getNodeValue());
        }

        return results;
    }
}
