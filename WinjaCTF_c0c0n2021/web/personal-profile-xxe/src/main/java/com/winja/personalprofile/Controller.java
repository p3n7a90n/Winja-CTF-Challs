package com.winja.personalprofile;
import com.winja.personalprofile.models.ProfileDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@org.springframework.stereotype.Controller
public class Controller {
    private static Logger log = LoggerFactory.getLogger(Controller.class);
    private final String defaultUrl = "https://gist.githubusercontent.com/p3n7a90n/2aa9e9f71df33da9b47aa64f88a7cd93/raw/62c68828306b9879c4a6274a481d9bc68a7ee178/profile.xml";
    @GetMapping("/")
    public String home(@RequestParam(value="profile",defaultValue=defaultUrl) String url, Model model)
    {   log.info("Retrieving profile Details");
        //Backend logic
        ParseXML parseXML = new ParseXML(url);
        ProfileDetails profileDetails = parseXML.parse();
        log.info(profileDetails.toString());
        model.addAttribute("profileDetails",profileDetails);
        return "index";
    }


}
