package webplang.repository;

import org.springframework.beans.factory.annotation.Autowired;
import webplang.domain.StatAnswerResponseBody;
import webplang.domain.WordStatResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Michał on 2017-10-19.
 */
public class StatsRepository {

    private DataSource dataSource;

    @Autowired
    public StatsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * method used for returning best/least known words
     * @param howMany - number of words returned in response
     * @param order - asc/desc
     * @param result - class which will be mapped to JSON response
     * @throws SQLException
     */
    public void processWords(String howMany, String order, StatAnswerResponseBody result) throws SQLException{

        ArrayList<String> labels = new ArrayList<>(0);
        ArrayList<Double> data = new ArrayList<>(0);

        Connection conn = dataSource.getConnection();
        ResultSet resultSet;

        String query1 = "SELECT" +
                " w.wordinpolish, w.wordinenglish," +
                " CAST(s.num_correct AS DECIMAL(10,2))/CAST(s.num_total AS DECIMAL(10,2)) AS ratio, s.id" +
                " FROM stats s INNER JOIN words w ON (s.id = w.id) ORDER BY ratio ASC LIMIT ?";

        String query2 = "SELECT" +
                " w.wordinpolish, w.wordinenglish," +
                " CAST(s.num_correct AS DECIMAL(10,2))/CAST(s.num_total AS DECIMAL(10,2)) AS ratio, s.id" +
                " FROM stats s INNER JOIN words w ON (s.id = w.id) ORDER BY ratio DESC LIMIT ?";

        PreparedStatement prepStat;

        if(order.equals("asc")) {
            prepStat = conn.prepareStatement(query1);
            prepStat.setInt(1, Integer.parseInt(howMany));
            resultSet = prepStat.executeQuery();
        } else {
            prepStat = conn.prepareStatement(query2);
            prepStat.setInt(1, Integer.parseInt(howMany));
            resultSet = prepStat.executeQuery();
        }

        while (resultSet.next()) {
            labels.add(resultSet.getString(1));
            data.add(resultSet.getDouble(3) * 100);
        }

        result.setWords(labels);
        result.setPercentages(data);
    }

    /**
     * method used for returning stats of particular word
     * @param word - which word
     * @param result - class which will be mapped to JSON response
     * @throws SQLException
     */
    public void processWord(String word, WordStatResponseBody result) throws SQLException{

        ArrayList<String> labels = new ArrayList<>(0);
        ArrayList<Integer> data = new ArrayList<>(0);

        labels.add("dobre odpowiedzi");
        labels.add("złe odpowiedzi");

        Connection conn = dataSource.getConnection();
        ResultSet resultSet;

        String query = "SELECT s.num_correct, s.num_total, w.wordinpolish FROM stats s INNER JOIN words w" +
                        " ON (s.id = w.id) WHERE w.wordinpolish = ?";

        PreparedStatement prepStat;
        prepStat = conn.prepareStatement(query);
        prepStat.setString(1, word);
        resultSet = prepStat.executeQuery();

        while(resultSet.next()) {

            data.add(resultSet.getInt(1));
            int tmp = resultSet.getInt(2) - resultSet.getInt(1);
            data.add(tmp);
        }

        result.setWord(word);
        result.setSignatures(labels);
        result.setNumbers(data);
    }
}
