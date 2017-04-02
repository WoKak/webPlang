package webplang.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Michał on 2017-03-26.
 */
public class Word {

    @NotNull
    @Size(min = 2, max = 50)
    private String wordInEnglish;

    @NotNull
    @Size(min = 2, max = 50)
    private String wordInPolish;

    public Word(String wordInPolish, String wordInEnglish) {
        this.wordInEnglish = wordInEnglish;
        this.wordInPolish = wordInPolish;
    }

    public Word() {}

    public String getWordInEnglish() {
        return wordInEnglish;
    }

    public void setWordInEnglish(String wordInEnglish) {
        this.wordInEnglish = wordInEnglish;
    }

    public String getWordInPolish() {
        return wordInPolish;
    }

    public void setWordInPolish(String wordInPolish) {
        this.wordInPolish = wordInPolish;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordInEnglish='" + wordInEnglish + '\'' +
                ", wordInPolish='" + wordInPolish + '\'' +
                '}';
    }
}
