package webplang.domain;

import com.fasterxml.jackson.annotation.JsonView;
import webplang.web.Views;

/**
 * Created by Michał on 2017-09-02.
 * Class used for exchanging data between browser and server's code
 */
public class WordAnswerResponseBody {

    @JsonView(Views.Public.class)
    private String correctAnswer;

    @JsonView(Views.Public.class)
    private String isCorrect;

    @JsonView(Views.Public.class)
    private String points;

    @JsonView(Views.Public.class)
    private String nextWord;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getNextWord() {
        return nextWord;
    }

    public void setNextWord(String nextWord) {
        this.nextWord = nextWord;
    }
}
