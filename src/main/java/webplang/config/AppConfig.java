package webplang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import webplang.domain.AppInfo;
import webplang.domain.Exercise;
import webplang.repository.UserRepository;
import webplang.repository.WordRepository;
import webplang.service.ExerciseService;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Michał on 2017-04-01.
 */

@Configuration
@PropertySource(value = {"classpath:database/database_MySQL.properties"})
public class AppConfig {


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

    @Bean
    public LocalValidatorFactoryBean validator() {

        LocalValidatorFactoryBean v = new LocalValidatorFactoryBean();
        return  v;
    }

    @Bean
    public Exercise exercise() throws SQLException{

        Exercise e = new Exercise();
        ExerciseService es = new ExerciseService(wordRepository());
        es.initializeFirstExercise(e);
        return e;
    }

    @Bean
    public WordRepository wordRepository() {

        return new WordRepository(dataSource());
    }

    @Bean
    public AppInfo appInfo() {

        return new AppInfo();
    }

    /**
     * Encoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder(){

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public UserRepository userRepository() {

        return new UserRepository(dataSource(), passwordEncoder());
    }
}
