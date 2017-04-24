package webplang.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Michał on 2017-04-24.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @RequestMapping(method = GET)
    public String home(Model model) {
        return "register";
    }
}
