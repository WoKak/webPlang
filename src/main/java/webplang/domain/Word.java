package webplang.domain;

/**
 * Created by Michał on 2017-03-26.
 */
public class Word {

    private String wordInEnglish;
    private String wordInPolish;

    public Word(String wordInPolish, String wordInEnglish) {
        this.wordInEnglish = wordInEnglish;
        this.wordInPolish = wordInPolish;
    }

    public Word() {
        this.wordInEnglish = "";
        this.wordInPolish = "";
    }

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
