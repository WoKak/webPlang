package webplang.domain;

import java.util.ArrayList;

/**
 * Created by Michał on 2017-04-02.
 */
public class Exercise {

    private ArrayList<Word> words;

    public Exercise() {
        words = new ArrayList<>(0);
    }

    public ArrayList<Word> getWords() {
        return words;
    }
}
