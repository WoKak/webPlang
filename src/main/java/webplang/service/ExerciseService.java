package webplang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webplang.domain.Exercise;
import webplang.domain.Word;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Michał on 2017-04-05.
 */

@Service
public class ExerciseService {

    private DataSource dataSource;

    @Autowired
    public ExerciseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Initialize exercise - adds words from database
     * @param exercise - exercise to be initialized
     */
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
