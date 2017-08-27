package webplang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Word;
import webplang.exception.ApplicationException;
import webplang.repository.WordRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Michał on 2017-04-01.
 */

@Service
public class WordService {

    private WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wr) {
        this.wordRepository = wr;
    }

    /**
     * Invokes addWord method in repository
     * @param wordToAdd - word created from the form on website
     * @param bindingResult - keeps the information about validation errors
     */
    public void addWord(Word wordToAdd, BindingResult bindingResult) throws SQLException {

        wordRepository.addWord(wordToAdd, bindingResult);
    }

    /**
     * Handles the exception
     * @param exception - exception thrown in the addWord method
     * @return model and view of the exception page
     */
    public ModelAndView handleApplicationException(ApplicationException exception) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage());
        mav.setViewName("appExeption");
        return mav;
    }
}
