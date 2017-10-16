package webplang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.AnswerResponseBody;
import webplang.domain.AppInfo;
import webplang.domain.Exercise;
import webplang.repository.WordRepository;

import java.sql.SQLException;

/**
 * Created by Michał on 2017-04-06.
 */

@Service
public class AnswerService {

    private static boolean isTrainingInitiated = false;
    private ExerciseService exerciseService;
    private WordRepository wordRepository;

    @Autowired
    public AnswerService(ExerciseService es, WordRepository wr) {
        this.exerciseService = es;
        this.wordRepository = wr;
    }

    public boolean checkAnswer(String answer, Exercise exercise, String wordInPl) {

        String result = exercise.findWordInPolish(answer);

        return result.equals(wordInPl);
    }

    /**
     * Used for processing main application form - uses checkAnswer method
     *
     * @param userAnswer - what user typed
     * @param exercise   - current exercise
     * @param apc        - controller information about current exercise
     * @param result
     */
    public void processApplicationForm(
            String userAnswer,
            Exercise exercise,
            AppInfo apc,
            AnswerResponseBody result) throws SQLException {

        if (isTrainingInitiated) {

            if (checkAnswer(userAnswer, exercise, exercise.getWords().get(apc.getIndex()).getWordInPolish())) {

                //wordRepository.updateStats(exercise.getWords().get(apc.getIndex()).getWordInPolish());
                apc.setPoints(apc.getPoints() + 1);
                apc.setIndex(apc.getIndex() + 1);
                result.setIsCorrect("Dobrze!");
                result.setCorrectAnswer("Odpowiedź prawidłowa!");
                result.setPoints("Masz już: " + apc.getPoints() + " pkt.");
                result.setNextWord(exercise.getWords().get(apc.getIndex()).getWordInPolish());

            } else {

                apc.setIndex(apc.getIndex() + 1);
                result.setIsCorrect("Źle!");
                result.setCorrectAnswer("Prawidłowa odpowiedź: "
                        + exercise.getWords().get(apc.getIndex() - 1).getWordInEnglish() + ".");
                result.setPoints("Masz już: " + apc.getPoints() + " pkt.");
                result.setNextWord(exercise.getWords().get(apc.getIndex()).getWordInPolish());
            }
        }

        if (userAnswer.equals("OK")) {

            isTrainingInitiated = true;
            result.setNextWord(exercise.getWords().get(apc.getIndex()).getWordInPolish());
        }
    }

    /**
     * Handles the main application exceptions
     *
     * @param apc - controller information about current exercise
     * @return model and view of the exception page
     */
    public void handleApplicationException(AppInfo apc, Exercise exercise) throws SQLException {

        Exercise newExercise = new Exercise();
        this.exerciseService.initializeExercise(newExercise);
        this.exerciseService.swap(exercise, newExercise);
        apc.setIndex(0);
        apc.setPoints(0);
    }
}
