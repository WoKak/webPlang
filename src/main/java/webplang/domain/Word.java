package webplang.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Michał on 2017-03-26.
 */

/**
 * Word is validated pair of Strings (polish and english word)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (!wordInEnglish.equals(word.wordInEnglish)) return false;
        return wordInPolish.equals(word.wordInPolish);
    }

    @Override
    public int hashCode() {
        int result = wordInEnglish.hashCode();
        result = 31 * result + wordInPolish.hashCode();
        return result;
    }
}
