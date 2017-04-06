package webplang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import webplang.domain.Answer;
import webplang.domain.Exercise;
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

    private int idx = 0;

    private CreateExerciseService ces;
    private Exercise exercise;
    private ProcessUserAnswerService pua;

    @Autowired
    public ApplicationController(CreateExerciseService ces, ProcessUserAnswerService pua) {
        this.ces = ces;
        this.pua = pua;
        this.exercise = new Exercise();

        this.ces.initializeExercise(this.exercise);
    }

    @RequestMapping(method = GET)
    public String getWordInEnglishFromApplicationForm(Model model) {

        model.addAttribute("wordToTranslate", exercise.getWords().get(idx).getWordInPolish());

        Answer userAnswer = new Answer();
        model.addAttribute("userAnswer", userAnswer);
        return "application";
    }

    @RequestMapping(method = POST)
    public String processApplicationForm(Model model, @ModelAttribute("userAnswer") Answer userAnswer) {

        if (this.pua.checkAnswer(userAnswer, this.exercise)) {

            model.addAttribute("wordToTranslate", "Dobrze!");

        } else {

            model.addAttribute("wordToTranslate", "Źle!");
        }

        idx++;

        return "application";
    }
}
