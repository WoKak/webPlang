package webplang.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.AnswerResponseBody;
import webplang.domain.AppInfo;
import webplang.domain.Exercise;
import webplang.service.AnswerService;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Michał on 2017-03-24.
 */

/**
 * Main application controller
 */
@RestController
public class RestApplicationController {

    private AnswerService answerService;
    private AppInfo appInfo;
    private Exercise exercise;

    @Autowired
    public RestApplicationController(AnswerService as, Exercise e, AppInfo ai) {

        this.exercise = e;
        this.answerService = as;
        this.appInfo = ai;
    }

    /**
     * Gets answer from form
     * @param model - model
     * @return new answer created from form
     */
    @JsonView(Views.Public.class)
    @RequestMapping(value = {"/process"}, method = RequestMethod.GET)
    public AnswerResponseBody processAnswer(@RequestParam String answer, Model model) throws SQLException{

        AnswerResponseBody result = new AnswerResponseBody();

        if (Optional.ofNullable(answer).isPresent()) {

            this.answerService.processApplicationForm(answer, this.exercise, this.appInfo, result);
        }

        return result;
    }

    /**
     * Uses service to handle main application exception
     * @return - see webplang.service docs
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String handleAppException() throws SQLException{

        int score = appInfo.getPoints();
        this.answerService.handleApplicationException(this.appInfo, this.exercise);
        String firstWord = exercise.getWords().get(0).getWordInPolish();

        return String.valueOf(score) + " " + firstWord;
    }
}
