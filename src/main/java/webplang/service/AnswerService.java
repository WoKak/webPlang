package webplang.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Answer;
import webplang.domain.ApplicationControllerInformation;
import webplang.domain.Exercise;

/**
 * Created by Michał on 2017-04-06.
 */

@Service
public class AnswerService {

    public boolean checkAnswer(Answer answer, Exercise exercise, String wordInPl) {

        String result = exercise.findWordInPl(answer.getWordInEnglish());

        return result.equals(wordInPl);
    }

    public void processApplicationForm(Answer userAnswer, Exercise exercise, Model model, ApplicationControllerInformation apc) {

        if ( checkAnswer(userAnswer, exercise, exercise.getWords().get(apc.getIndex()).getWordInPolish()) ) {

            apc.setPoints(apc.getPoints() + 1);
            apc.setIndex(apc.getIndex() + 1);
            model.addAttribute("result", "Dobrze!");
            model.addAttribute("correct_answer", "Odpowiedź prawidłowa!");
            model.addAttribute("points", "Masz już: " + apc.getPoints() + " pkt.");
            model.addAttribute("wordToTranslate", exercise.getWords().get(apc.getIndex()).getWordInPolish());

        } else {

            apc.setIndex(apc.getIndex() + 1);
            model.addAttribute("result", "Źle!");
            model.addAttribute("correct_answer", "Prawidłowa odpowiedź: "
                    + exercise.getWords().get(apc.getIndex() - 1).getWordInEnglish() + ".");
            model.addAttribute("points", "Masz już: " + apc.getPoints() + " pkt.");
            model.addAttribute("wordToTranslate", exercise.getWords().get(apc.getIndex()).getWordInPolish());
        }
    }

    public ModelAndView handleApplicationException(ApplicationControllerInformation apc) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Koniec ćwiczenia!\n" + "Zdobyłeś " + apc.getPoints() + " pkt!");
        mav.setViewName("appExeption");
        return mav;
    }
}
