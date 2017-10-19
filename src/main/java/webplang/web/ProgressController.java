package webplang.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Michał on 2017-10-19.
 */

@Controller
@RequestMapping("/progress")
public class ProgressController {

    @RequestMapping(method = GET)
    public String page(Model model) {
        return "progress";
    }
}
