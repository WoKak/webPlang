package webplang.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Word;
import webplang.exception.WordInBaseException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michał on 2017-04-01.
 */

@Service
public class AddWordService {

    private DataSource dataSource;

    @Autowired
    public AddWordService(DataSource ds) {
        this.dataSource = ds;
    }

    public void addWord(Word wordToAdd) {

        try {

            Connection conn = dataSource.getConnection();

            ResultSet result;
            String query = "SELECT * FROM words WHERE wordInPolish=?";
            PreparedStatement checkStat = conn.prepareStatement(query);
            checkStat.setString(1, wordToAdd.getWordInPolish());
            result = checkStat.executeQuery();


            if (result.next()) {
                throw new WordInBaseException(wordToAdd.getWordInPolish());
            }


            String insert = "INSERT INTO words (wordInPolish, wordInEnglish) VALUES (?, ?)";

            PreparedStatement pstat = conn.prepareStatement(insert);
            pstat.setString(1, wordToAdd.getWordInPolish());
            pstat.setString(2, wordToAdd.getWordInEnglish());
            pstat.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();

        }
    }

    public ModelAndView handleWordInBaseException(WordInBaseException exception) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidWord", exception.getWordInPolish());
        mav.setViewName("wordInBase");
        return mav;
    }
}
