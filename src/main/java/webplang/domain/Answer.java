package webplang.domain;

/**
 * Created by Michał on 2017-03-26.
 */
public class Answer {

    public String wordInEnglish;

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
