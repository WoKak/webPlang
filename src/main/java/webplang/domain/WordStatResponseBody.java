package webplang.domain;

import com.fasterxml.jackson.annotation.JsonView;
import webplang.web.Views;

import java.util.List;

/**
 * Created by Michał on 2017-10-20.
 */
public class WordStatResponseBody {

    @JsonView(Views.Public.class)
    private String word;

    @JsonView(Views.Public.class)
    private List<Integer> numbers;

    @JsonView(Views.Public.class)
    private List<String> signatures;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<String> signatures) {
        this.signatures = signatures;
    }
}
