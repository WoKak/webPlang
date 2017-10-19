package webplang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webplang.domain.StatAnswerResponseBody;
import webplang.repository.StatsRepository;

import java.sql.SQLException;

/**
 * Created by Michał on 2017-10-19.
 */

@Service
public class StatsService {

    private StatsRepository statsRepository;

    @Autowired
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public void processProgressForm(String howMany, String order, StatAnswerResponseBody result) throws SQLException{

        this.statsRepository.process(howMany, order, result);
    }
}
