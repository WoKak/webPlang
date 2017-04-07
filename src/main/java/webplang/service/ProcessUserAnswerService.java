package webplang.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Answer;
import webplang.domain.AppControllerInfo;
import webplang.domain.Exercise;
import webplang.exception.ApplicationException;

/**
 * Created by Michał on 2017-04-06.
 */

@Service
public class ProcessUserAnswerService {

    public boolean checkAnswer(Answer answer, Exercise exercise, String wordInPl) {

        String result = exercise.findWordInPl(answer.getWordInEnglish());

        return result.equals(wordInPl);
    }

    public void processApplicationForm(Answer userAnswer, Exercise exercise, Model model, AppControllerInfo apc) {

        if ( checkAnswer(userAnswer, exercise, exercise.getWords().get(apc.getIdx()).getWordInPolish()) ) {

            apc.setIdx(apc.getIdx() + 1);
            apc.setPts(apc.getPts() + 1);
            if (apc.getIdx() >= 20) {
                throw new ApplicationException("Koniec ćwiczenia!\nMasz " + apc.getPts() +" punktów!");
            }
            model.addAttribute("result", "Dobrze! - Masz już: " + apc.getPts() + " pkt.");
            model.addAttribute("wordToTranslate", exercise.getWords().get(apc.getIdx()).getWordInPolish());

        } else {

            apc.setIdx(apc.getIdx() + 1);

            if (apc.getIdx() >= 20) {
                throw new ApplicationException("Koniec ćwiczenia! Masz " + apc.getPts() +" punktów!");
            }
            model.addAttribute("result", "Źle! - Masz już: " + apc.getPts() + " pkt.");
            model.addAttribute("wordToTranslate", exercise.getWords().get(apc.getIdx()).getWordInPolish());
        }
    }

    public ModelAndView handleApplicationException(ApplicationException exception) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage());
        mav.setViewName("appExeption");
        return mav;
    }
}
