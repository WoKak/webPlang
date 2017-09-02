package webplang.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Michał on 2017-09-02.
 */

@Controller
public class ApplicationController {

    @RequestMapping(value = "/application", method = RequestMethod.GET)
    public String mainController() {

        return "application";
    }
}