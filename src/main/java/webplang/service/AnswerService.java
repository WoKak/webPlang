package webplang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Answer;
import webplang.domain.AppInfo;
import webplang.domain.Exercise;
import webplang.repository.WordRepository;

import java.sql.SQLException;

/**
 * Created by Michał on 2017-04-06.
 */

@Service
public class AnswerService {

    private ExerciseService exerciseService;
    private WordRepository wordRepository;

    @Autowired
    public AnswerService(ExerciseService es, WordRepository wr) {
        this.exerciseService = es;
        this.wordRepository = wr;
    }

    public boolean checkAnswer(Answer answer, Exercise exercise, String wordInPl) {

        String result = exercise.findWordInPolish(answer.getWordInEnglish());

        return result.equals(wordInPl);
    }

    /**
     * Used for processing main application form - uses checkAnswer method
     * @param userAnswer - what user typed
     * @param exercise - current exercise
     * @param model - model
     * @param apc - controller information about current exercise
     */
    public void processApplicationForm(Answer userAnswer, Exercise exercise, Model model, AppInfo apc) throws SQLException{

        if ( checkAnswer(userAnswer, exercise, exercise.getWords().get(apc.getIndex()).getWordInPolish()) ) {

            wordRepository.updateStats(exercise.getWords().get(apc.getIndex()).getWordInPolish());
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

    /**
     * Handles the main application exceptions
     * @param apc - controller information about current exercise
     * @return model and view of the exception page
     */
    public ModelAndView handleApplicationException(AppInfo apc, Exercise exercise) throws SQLException{

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Koniec ćwiczenia!\n" + "Zdobyłeś " + apc.getPoints() + " pkt!" +
                "\n Kliknij \"aplikacja\" aby rozpocząć od nowa");
        mav.setViewName("appExeption");

        Exercise newExercise = new Exercise();
        this.exerciseService.initializeExercise(newExercise);
        this.exerciseService.swap(exercise, newExercise);
        apc.setIndex(0);
        apc.setPoints(0);

        return mav;
    }
}
