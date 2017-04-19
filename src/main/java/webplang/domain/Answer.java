package webplang.domain;

/**
 * Created by Michał on 2017-03-26.
 */

/**
 * Answer is the text which user type during doing of the exercise
 */
public class Answer {

    private String wordInEnglish;

    public Answer(String wordInEnglish) {
        this.wordInEnglish = wordInEnglish;
    }

    public Answer() {
        this.wordInEnglish = "";
    }

    public String getWordInEnglish() {
        return wordInEnglish;
    }

    public void setWordInEnglish(String wordInEnglish) {
        this.wordInEnglish = wordInEnglish;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "wordInEnglish='" + wordInEnglish + '\'' +
                '}';
    }
}
