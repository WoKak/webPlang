package webplang.domain;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Michał on 2017-04-02.
 */

/**
 * Exercise is the list of words
 */
public class Exercise {

    private ArrayList<Word> words;

    public Exercise() {
        words = new ArrayList<>(0);
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public String findWordInEnglish(String wordInPolish) {

        String toReturn = null;

        for (Word t: words) {
            if (t.getWordInPolish().trim().equals(wordInPolish)) {
                toReturn = t.getWordInEnglish();
            }
        }

        return Optional.ofNullable(toReturn).orElse("ERROR");
    }

    public String findWordInPolish(String wordInEnglish){

        String toReturn = null;

        for (Word t: words) {
            if (t.getWordInEnglish().trim().equals(wordInEnglish)) {
                toReturn = t.getWordInPolish();
            }
        }

        return Optional.ofNullable(toReturn).orElse("ERROR");
    }
}
