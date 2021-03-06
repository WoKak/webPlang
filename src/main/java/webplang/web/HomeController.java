package webplang.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller of the home section
 */
@Controller
@RequestMapping("/home")
public class HomeController {

  @RequestMapping(method = GET)
  public String page(Model model) {
    return "home";
  }

}
