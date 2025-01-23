package com.winja.application;
import com.winja.domain.GeoLocDetails;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerService extends DefaultHandler {

    private GeoLocDetails geoLocDetails;
    private StringBuilder builder;

    public GeoLocDetails getUpdate() {
        return geoLocDetails;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if (this.geoLocDetails != null) {
            builder.append(ch, start, length);
        }
    }


    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        super.endElement(uri, localName, name);

        if (this.geoLocDetails != null) {
            if (name.equals("name")) {
                geoLocDetails.setName(builder.toString().trim());
            } else if (name.equals("latitude")) {
                geoLocDetails.setLatitude(builder.toString().trim());
            } else if (name.equals("longitude")) {
                geoLocDetails.setLongitude(builder.toString().trim());
            } else if (name.equals("time")) {
                geoLocDetails.setTime(builder.toString().trim());
            }
            else if (name.equals("ele")) {
                geoLocDetails.setEle(builder.toString().trim());
            }

            builder.setLength(0);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName,
                             String name, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, name, attributes);

        if (name.equals("GeoLocDetails")) {
            geoLocDetails = new GeoLocDetails();
        }
    }

}
