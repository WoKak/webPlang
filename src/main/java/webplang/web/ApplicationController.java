package webplang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Answer;
import webplang.domain.AppControllerInfo;
import webplang.domain.Exercise;
import webplang.exception.ApplicationException;
import webplang.service.CreateExerciseService;
import webplang.service.ProcessUserAnswerService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Michał on 2017-03-24.
 */

@Controller
@RequestMapping("/application")
public class ApplicationController {

    private CreateExerciseService ces;
    private Exercise exercise;
    private ProcessUserAnswerService pua;
    private AppControllerInfo apc;

    @Autowired
    public ApplicationController(CreateExerciseService ces, ProcessUserAnswerService pua) {

        this.ces = ces;
        this.pua = pua;
        this.apc = new AppControllerInfo();
        this.exercise = new Exercise();
        this.ces.initializeExercise(this.exercise);
    }

    @RequestMapping(method = GET)
    public String getWordInEnglishFromApplicationForm(Model model) {

        model.addAttribute("wordToTranslate", exercise.getWords().get(apc.getIdx()).getWordInPolish());
        Answer userAnswer = new Answer();
        model.addAttribute("userAnswer", userAnswer);
        return "application";
    }

    @RequestMapping(method = POST)
    public String processApplicationForm(Model model, @ModelAttribute("userAnswer") Answer userAnswer) {

        this.pua.processApplicationForm(userAnswer, this.exercise, model, this.apc);
        return "application";
    }

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView handleAddWordToBaseException(ApplicationException exception) {

        return this.pua.handleApplicationException(exception);
    }
}
