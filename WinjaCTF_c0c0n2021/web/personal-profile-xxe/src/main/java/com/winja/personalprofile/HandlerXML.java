package com.winja.personalprofile;
import com.winja.personalprofile.models.ProfileDetails;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerXML extends DefaultHandler {

    private ProfileDetails profileDetails;
    private StringBuilder builder;

    public ProfileDetails getUpdate() {
        return profileDetails;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if (this.profileDetails != null) {
            builder.append(ch, start, length);
        }
    }


    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        super.endElement(uri, localName, name);

        if (this.profileDetails != null) {
            if (name.equals("name")) {
                profileDetails.setName(builder.toString().trim());
            } else if (name.equals("age")) {
                profileDetails.setAge(Integer.valueOf(builder.toString().trim()));
            } else if (name.equals("experience")) {
                profileDetails.setExp(Integer.valueOf(builder.toString().trim()));
            } else if (name.equals("country")) {
                profileDetails.setCountry(builder.toString().trim());
            }
            else if (name.equals("location")) {
                profileDetails.setLocation(builder.toString().trim());
            }
            else if (name.equals("email")) {
                profileDetails.setEmail(builder.toString().trim());
            }
            else if (name.equals("phone")) {
                profileDetails.setPhone(builder.toString().trim());
            }
            else if (name.equals("freelance")) {
                profileDetails.setFreelance(builder.toString().trim());
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

        if (name.equals("ProfileDetails")) {
            profileDetails = new ProfileDetails();
        }
    }

}
