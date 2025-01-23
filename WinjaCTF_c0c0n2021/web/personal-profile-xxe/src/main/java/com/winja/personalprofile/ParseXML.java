package com.winja.personalprofile;

import com.winja.personalprofile.models.ProfileDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ParseXML {
    private URL changeLogUrl ;
    private static final Logger log= LoggerFactory.getLogger(ParseXML.class);

    ParseXML(String changeLogUrl)
    {
        try {
            this.changeLogUrl = new URL(changeLogUrl);
        } catch (MalformedURLException e) {
           log.error("Something wrong in URL",e);
            e.printStackTrace();
        }
    }
    protected ProfileDetails parse()
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        InputStream inputStream = null;
        try {
            URLConnection connection = changeLogUrl.openConnection();
            inputStream = connection.getInputStream();
            SAXParser parser = factory.newSAXParser();
            HandlerXML handler = new HandlerXML();
            parser.parse(inputStream, handler);
            return handler.getUpdate();
        }catch (Exception e)
        {
            log.error("Something wrong in parsing the XML content",e);
            e.printStackTrace();
        }
        return null;
    }

}
