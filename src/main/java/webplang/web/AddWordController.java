package webplang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Word;
import webplang.domain.service.AddWordService;
import webplang.exception.WordInBaseException;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Michał on 2017-03-28.
 */

@Controller
@RequestMapping("/addWord")
public class AddWordController {


    private AddWordService addWordService;

    @Autowired
    public AddWordController(AddWordService aws) {
        this.addWordService = aws;
    }

    @RequestMapping(method = GET)
    public String getWordInEnglishFromApplicationForm(Model model) {

        Word wordToAdd = new Word();
        model.addAttribute("wordToAdd", wordToAdd);
        return "addWord";
    }

    @RequestMapping(method = POST)
    public String processApplicationForm(@ModelAttribute("wordToAdd") Word wordToAdd) throws IOException {

        this.addWordService.addWord(wordToAdd);

        return "redirect:/home";
    }

    @ExceptionHandler(WordInBaseException.class)
    public ModelAndView handleError(WordInBaseException exception) {

        return this.addWordService.handleWordInBaseException(exception);
    }
}
