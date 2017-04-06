package webplang.service;

import org.springframework.stereotype.Service;
import webplang.domain.Answer;
import webplang.domain.Exercise;

/**
 * Created by Michał on 2017-04-06.
 */

@Service
public class ProcessUserAnswerService {

    public boolean checkAnswer(Answer answer, Exercise exercise) {

        String result = exercise.findWordInPl(answer.getWordInEnglish());

        return !result.equals("ERROR");
    }
}
