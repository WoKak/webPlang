package webplang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Word;
import webplang.service.AddWordService;
import webplang.exception.ApplicationException;

import javax.validation.Valid;

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
    public String getWordToAddFromAddWordForm(Model model) {

        Word wordToAdd = new Word();
        model.addAttribute("wordToAdd", wordToAdd);
        return "addWord";
    }

    @RequestMapping(method = POST)
    public String processAddNewWordForm(@ModelAttribute("wordToAdd") @Valid Word wordToAdd, BindingResult result) {

        this.addWordService.addWord(wordToAdd, result);

        return "redirect:/home";
    }

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView handleAddWordToBaseException(ApplicationException exception) {

        return this.addWordService.handleApplicationException(exception);
    }
}
