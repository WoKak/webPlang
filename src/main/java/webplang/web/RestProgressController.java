package webplang.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webplang.domain.StatAnswerResponseBody;
import webplang.domain.WordAnswerResponseBody;
import webplang.domain.WordStatResponseBody;
import webplang.service.StatsService;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Michał on 2017-10-19.
 */

@RestController
public class RestProgressController {

    private StatsService statsService;

    @Autowired
    public RestProgressController(StatsService statsService) {
        this.statsService = statsService;
    }

    /**
     * Gets criteria from form
     * @param model - model
     * @return new answer created from form
     */
    @JsonView(Views.Public.class)
    @RequestMapping(value = {"/processCriteria"}, method = RequestMethod.GET)
    public StatAnswerResponseBody processWordsStats(
            @RequestParam String howMany,
            @RequestParam String order,
            Model model)
            throws SQLException {

        StatAnswerResponseBody result = new StatAnswerResponseBody();

        if (Optional.ofNullable(howMany).isPresent() && Optional.ofNullable(order).isPresent()) {

            this.statsService.processProgressForm(howMany, order, result);
        }

        return result;
    }

    /**
     * Gets criteria from form
     * @param model - model
     * @return new answer created from form
     */
    @JsonView(Views.Public.class)
    @RequestMapping(value = {"/processWordCriteria"}, method = RequestMethod.GET)
    public WordStatResponseBody processWordStats(
            @RequestParam String word,
            Model model)
            throws SQLException {

        WordStatResponseBody result = new WordStatResponseBody();

        if (Optional.ofNullable(word).isPresent()) {

            this.statsService.processWordProgressForm(word, result);
        }

        return result;
    }
}
