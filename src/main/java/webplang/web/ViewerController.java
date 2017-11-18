package webplang.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Michał on 2017-11-01.
 */
@Controller
@RequestMapping("/wordbase")
public class ViewerController {

    @RequestMapping(method = GET)
    public String page(Model model) {
        return "wordbase";
    }
}
