package webplang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Answer;
import webplang.domain.ApplicationControllerInformation;
import webplang.domain.Exercise;
import webplang.service.AnswerService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Michał on 2017-03-24.
 */

/**
 * Main application controller
 */
@Controller
@RequestMapping("/application")
public class ApplicationController {

    private AnswerService answerService;
    private ApplicationControllerInformation applicationControllerInformation;
    private Exercise exercise;

    @Autowired
    public ApplicationController(AnswerService as, Exercise e) {

        this.exercise = e;
        this.answerService = as;
        this.applicationControllerInformation = new ApplicationControllerInformation();
    }

    /**
     * Gets answer from form
     * @param model - model
     * @return new answer created from form
     */
    @RequestMapping(method = GET)
    public String getWordInEnglishFromApplicationForm(Model model) {

        model.addAttribute("wordToTranslate", exercise.getWords().get(applicationControllerInformation.getIndex()).getWordInPolish());
        Answer userAnswer = new Answer();
        model.addAttribute("userAnswer", userAnswer);
        return "application";
    }

    /**
     * Uses service to process answer
     * @param model - model
     * @param userAnswer - answer typed by user
     * @return updated view
     */
    @RequestMapping(method = POST)
    public String processApplicationForm(Model model, @ModelAttribute("userAnswer") Answer userAnswer) {

        this.answerService.processApplicationForm(userAnswer, this.exercise, model, this.applicationControllerInformation);
        return "application";
    }

    /**
     * Uses service to handle main application exception
     * @return - see service docs
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ModelAndView handleAppException() {

        return this.answerService.handleApplicationException(this.applicationControllerInformation);
    }
}
