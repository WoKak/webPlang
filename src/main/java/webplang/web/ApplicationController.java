package webplang.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import webplang.domain.Answer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Michał on 2017-03-24.
 */

@Controller
@RequestMapping("/application")
public class ApplicationController {

    @RequestMapping(method = GET)
    public String getWordInEnglishFromApplicationForm(Model model) {

        Answer userAnswer = new Answer();
        model.addAttribute("userAnswer", userAnswer);
        return "application";
    }

    @RequestMapping(method = POST)
    public String processApplicationForm(@ModelAttribute("userAnswer") Answer userAnswer) {

        System.out.println(userAnswer.toString());
        return "application";
    }

}
