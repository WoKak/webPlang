package webplang.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import webplang.domain.Exercise;
import webplang.domain.Word;
import webplang.exception.ApplicationException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Michał on 2017-08-27.
 */
public class WordRepository {

    private DataSource dataSource;

    @Autowired
    public WordRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addWord(Word wordToAdd, BindingResult bindingResult) throws SQLException {


        if (Optional.ofNullable(bindingResult).isPresent())
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
    }

    public void initializeExercise(Exercise exercise) {

        ArrayList<Integer> alreadyInExercise = new ArrayList<Integer>(0);
        Random random = new Random();
        String query = "SELECT * FROM words WHERE id = CAST(? AS integer)";
        Integer size = 0;
        int counter = 0;

        try {

            Connection conn = dataSource.getConnection();

            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM words");

            while (result.next()) {
                size = Integer.parseInt(result.getString(1));
            }
            size++;

            while (counter < 20) {

                Integer idx = random.nextInt(size);

                while (alreadyInExercise.contains(idx)) {
                    idx = random.nextInt(size);
                }
                PreparedStatement pstat = conn.prepareStatement(query);
                pstat.setString(1, String.valueOf(idx));
                result = pstat.executeQuery();

                if (result.next()) {

                    Word tmp = new Word(result.getString(2).trim(), result.getString(3).trim());
                    exercise.getWords().add(tmp);
                    counter++;
                }
                alreadyInExercise.add(idx);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
