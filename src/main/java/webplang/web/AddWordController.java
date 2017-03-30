package webplang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webplang.domain.Word;
import webplang.exception.WordInBaseException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Michał on 2017-03-28.
 */

@Controller
@RequestMapping("/addWord")
@PropertySource(value = {"classpath:database/database.properties"})
public class AddWordController {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getRequiredProperty("jdbc.drivers"));
        ds.setUrl(env.getRequiredProperty("jdbc.url"));
        ds.setUsername(env.getRequiredProperty("jdbc.username"));
        ds.setPassword(env.getRequiredProperty("jdbc.password"));

        return ds;
    }

    @Autowired
    private DataSource ds;

    @RequestMapping(method = GET)
    public String getWordInEnglishFromApplicationForm(Model model) {

        Word wordToAdd = new Word();
        model.addAttribute("wordToAdd", wordToAdd);
        return "addWord";
    }

    @RequestMapping(method = POST)
    public String processApplicationForm(@ModelAttribute("wordToAdd") Word wordToAdd) throws IOException {

        try {

            Connection conn = ds.getConnection();

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

        return "redirect:/home";
    }

    @ExceptionHandler(WordInBaseException.class)
    public ModelAndView handleError(HttpServletRequest req, WordInBaseException exception) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidWord", exception.getWordInPolish());
        mav.setViewName("wordInBase");
        return mav;
    }
}
