package webplang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webplang.domain.Exercise;
import webplang.repository.WordRepository;

import java.sql.SQLException;

/**
 * Created by Michał on 2017-04-05.
 */

@Service
public class ExerciseService {

    private WordRepository wordRepository;

    @Autowired
    public ExerciseService(WordRepository wr) {
        this.wordRepository = wr;
    }

    /**
     * Initialize first exercise - adds words from database
     * @param exercise - exercise to be initialized
     */
    public void initializeFirstExercise(Exercise exercise) throws SQLException{

        wordRepository.initializeFirstExercise(exercise);
    }

    /**
     * Initialize later exercises - adds words from database
     * @param exercise - exercise to be initialized
     */
    public void initializeLaterExercises(Exercise exercise) throws SQLException{

        wordRepository.initializeLaterExercises(exercise);
    }

    public void swap(Exercise exercise, Exercise newExercise) {

        exercise.setWords(newExercise.getWords());
    }
}
