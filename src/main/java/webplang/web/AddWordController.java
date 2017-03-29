package webplang.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import webplang.domain.Word;
import webplang.config.JdbcConfig;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Michał on 2017-03-28.
 */

@Controller
@RequestMapping("/addWord")
public class AddWordController {

    @RequestMapping(method = GET)
    public String getWordInEnglishFromApplicationForm(Model model) {

        Word wordToAdd = new Word();
        model.addAttribute("wordToAdd", wordToAdd);
        return "addWord";
    }

    @RequestMapping(method = POST)
    public String processApplicationForm(@ModelAttribute("wordToAdd") Word wordToAdd) {


        try (java.sql.Connection conn = JdbcConfig.getConnection()) {

            Statement stat = conn.createStatement();
            Integer size = 0;

            try (ResultSet result = stat.executeQuery("SELECT * FROM Main")) {
                while (result.next())
                    size++;
            }

            String insertUpdate = "INSERT INTO Main VALUES (?, ?, ?)";

            PreparedStatement pstat = conn.prepareStatement(insertUpdate);
            pstat.setString(1, wordToAdd.getWordInPolish());
            pstat.setString(2, wordToAdd.getWordInEnglish());
            pstat.setString(3, size.toString());
            pstat.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQL error!");
        } catch (IOException ex) {
            System.out.println("IO error!");
            ex.printStackTrace();
        }

        return "addWord";
    }
}
