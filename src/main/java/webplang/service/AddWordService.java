package webplang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Word;
import webplang.exception.ApplicationException;

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

    public void addWord(Word wordToAdd, BindingResult bindingResult) {

        try {

            if (bindingResult.hasErrors()) {
                throw new ApplicationException("Błąd walidacji! Słówka muszą być długości od 2 do 50 znaków!");
            }

            Connection conn = dataSource.getConnection();

            ResultSet result;
            String query = "SELECT * FROM words WHERE wordInPolish=?";
            PreparedStatement checkStat = conn.prepareStatement(query);
            checkStat.setString(1, wordToAdd.getWordInPolish());
            result = checkStat.executeQuery();


            if (result.next()) {
                throw new ApplicationException("Słówko '" + wordToAdd.getWordInPolish() + "' jest już w bazie.");
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

    public ModelAndView handleApplicationException(ApplicationException exception) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage());
        mav.setViewName("appExeption");
        return mav;
    }
}
