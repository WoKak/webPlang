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
import webplang.service.WordService;
import webplang.exception.ApplicationException;

import javax.validation.Valid;

import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Michał on 2017-03-28.
 */

/**
 * Controller of the add word section
 */
@Controller
@RequestMapping("/addWord")
public class AddedWordController {


    private WordService addWordService;

    @Autowired
    public AddedWordController(WordService aws) {
        this.addWordService = aws;
    }

    /**
     * Gets text from form then makes new word from it
     * @param model - model
     * @return word from form
     */
    @RequestMapping(method = GET)
    public String getWordToAddFromAddWordForm(Model model) {

        Word wordToAdd = new Word();
        model.addAttribute("wordToAdd", wordToAdd);
        return "addWord";
    }

    /**
     * Uses webplang.service to add word to database
     * @param wordToAdd - word from form
     * @param result - result of validation
     * @return if everything's ok redirects to the homepage
     */
    @RequestMapping(method = POST)
    public String processAddNewWordForm(@ModelAttribute("wordToAdd") @Valid Word wordToAdd, BindingResult result)
            throws SQLException{

        this.addWordService.addWord(wordToAdd, result);

        return "redirect:/home";
    }

    /**
     * Uses webplang.service to handle exception
     * @param exception - exception thrown during adding
     * @return - see webplang.service docs
     */
    @ExceptionHandler(ApplicationException.class)
    public ModelAndView handleAddWordToBaseException(ApplicationException exception) {

        return this.addWordService.handleApplicationException(exception);
    }
}
