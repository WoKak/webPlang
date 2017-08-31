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

        String insertWords = "INSERT INTO words (wordInPolish, wordInEnglish) VALUES (?, ?)";
        String check = "SELECT * FROM words ORDER BY id DESC LIMIT 1";
        String insertStats = "INSERT INTO stats (word_id, num_correct, num_total) VALUES (?, 0, 1)";

        PreparedStatement pstatWords = conn.prepareStatement(insertWords);
        pstatWords.setString(1, wordToAdd.getWordInPolish());
        pstatWords.setString(2, wordToAdd.getWordInEnglish());
        pstatWords.executeUpdate();

        //init stat
        PreparedStatement pstatCheck = conn.prepareStatement(check);
        ResultSet checkResult = pstatCheck.executeQuery();

        //checks id of added word
        int id = 0;
        if (checkResult.next()) {
            id = checkResult.getInt(1);
        }

        PreparedStatement pstatStats = conn.prepareStatement(insertStats);
        pstatStats.setInt(1, id);
        pstatStats.executeUpdate();
    }

    public void initializeFirstExercise(Exercise exercise) throws SQLException{

        ArrayList<Integer> alreadyInExercise = new ArrayList<Integer>(0);
        Random random = new Random();
        String query = "SELECT * FROM words WHERE id = CAST(? AS integer)";
        String update = "UPDATE stats SET num_total=? WHERE word_id=?";
        String chceck = "SELECT * FROM stats WHERE word_id=CAST(? AS integer)";
        Integer size = 0;
        int counter = 0;

        Connection conn = dataSource.getConnection();

        Statement stat = conn.createStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM words");

        while (result.next()) {
            size = Integer.parseInt(result.getString(1));
        }
        size++;

        while (counter < 20) {

            Integer idx = random.nextInt(size);

            //chcecks if exercise got this word
            while (alreadyInExercise.contains(idx)) {
                idx = random.nextInt(size);
            }

            //adds word to exercise
            PreparedStatement pstat = conn.prepareStatement(query);
            pstat.setString(1, String.valueOf(idx));
            result = pstat.executeQuery();

            if (result.next()) {

                Word tmp = new Word(result.getString(2).trim(), result.getString(3).trim());
                exercise.getWords().add(tmp);
                counter++;
            }
            alreadyInExercise.add(idx);

            //updates stats
            PreparedStatement prepUpdate = conn.prepareStatement(update);
            PreparedStatement prepCheck = conn.prepareStatement(chceck);

            //checks previous value
            prepCheck.setString(1, String.valueOf(idx));
            result = prepCheck.executeQuery();
            int num_total = 0;
            if (result.next()) {
                num_total = result.getInt(3);
            }

            //updates value
            num_total = num_total + 1;
            prepUpdate.setInt(1, num_total);
            prepUpdate.setInt(2, idx);
            prepUpdate.executeUpdate();
        }

    }

    public void updateStats(String wordInPolish) throws SQLException {

        Connection conn = dataSource.getConnection();

        String update = "UPDATE stats SET num_correct=? WHERE word_id=?";
        PreparedStatement pstatUpdate = conn.prepareStatement(update);

        String checkId = "SELECT * FROM words WHERE wordInPolish=?";
        PreparedStatement pstatCheckId = conn.prepareStatement(checkId);
        pstatCheckId.setString(1, wordInPolish);
        ResultSet resultCheckId = pstatCheckId.executeQuery();

        int id = 0;
        if (resultCheckId.next()) {
            id = resultCheckId.getInt(1);
        }

        String checkCorrects = "SELECT * FROM stats WHERE word_id=?";
        PreparedStatement pstatCheckCorrects = conn.prepareStatement(checkCorrects);
        pstatCheckCorrects.setInt(1, id);
        ResultSet resultCheckCorrects = pstatCheckCorrects.executeQuery();

        int corrects = 0;
        if (resultCheckCorrects.next()) {
            corrects = resultCheckCorrects.getInt(2) + 1;
        }

        pstatUpdate.setInt(1, corrects);
        pstatUpdate.setInt(2, id);
        pstatUpdate.executeUpdate();
    }

}
