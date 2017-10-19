package webplang.domain;

import com.fasterxml.jackson.annotation.JsonView;
import webplang.web.Views;

import java.util.List;

/**
 * Created by Michał on 2017-10-19.
 */
public class StatAnswerResponseBody {

    @JsonView(Views.Public.class)
    private List<String> words;

    @JsonView(Views.Public.class)
    private List<Double> percentages;

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<Double> getPercentages() {
        return percentages;
    }

    public void setPercentages(List<Double> percentages) {
        this.percentages = percentages;
    }
}
