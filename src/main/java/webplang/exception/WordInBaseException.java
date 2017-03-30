package webplang.exception;

/**
 * Created by Michał on 2017-03-30.
 */
public class WordInBaseException extends RuntimeException{

    private String wordInPolish;

    public WordInBaseException(String wordInPolish) {

        this.wordInPolish = wordInPolish;
    }

    public String getWordInPolish() {
        return wordInPolish;
    }
}
