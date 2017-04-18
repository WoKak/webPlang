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
import webplang.service.ExerciseService;
import webplang.service.AnswerService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Michał on 2017-03-24.
 */

@Controller
@RequestMapping("/application")
public class ApplicationController {

    private ExerciseService exerciseService;
    private AnswerService answerService;
    private ApplicationControllerInformation applicationControllerInformation;
    private Exercise exercise;

    @Autowired
    public ApplicationController(ExerciseService es, AnswerService as, Exercise e) {

        this.exercise = e;
        this.exerciseService = es;
        this.answerService = as;
        this.applicationControllerInformation = new ApplicationControllerInformation();
        this.exerciseService.initializeExercise(this.exercise);
    }

    @RequestMapping(method = GET)
    public String getWordInEnglishFromApplicationForm(Model model) {

        model.addAttribute("wordToTranslate", exercise.getWords().get(applicationControllerInformation.getIndex()).getWordInPolish());
        Answer userAnswer = new Answer();
        model.addAttribute("userAnswer", userAnswer);
        return "application";
    }

    @RequestMapping(method = POST)
    public String processApplicationForm(Model model, @ModelAttribute("userAnswer") Answer userAnswer) {

        this.answerService.processApplicationForm(userAnswer, this.exercise, model, this.applicationControllerInformation);
        return "application";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ModelAndView handleAppException() {

        return this.answerService.handleApplicationException(this.applicationControllerInformation);
    }
}
