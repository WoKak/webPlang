package webplang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webplang.domain.StatAnswerResponseBody;
import webplang.domain.WordStatResponseBody;
import webplang.repository.StatsRepository;

import java.sql.SQLException;

/**
 * Created by Michał on 2017-10-19.
 * Service for REST progress controller
 */
@Service
public class StatsService {

    private StatsRepository statsRepository;

    @Autowired
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public void processProgressForm(String howMany, String order, StatAnswerResponseBody result) throws SQLException{
        this.statsRepository.processWords(howMany, order, result);
    }

    public void processWordProgressForm(String word, WordStatResponseBody result) throws SQLException {
        this.statsRepository.processWord(word, result);
    }
}
