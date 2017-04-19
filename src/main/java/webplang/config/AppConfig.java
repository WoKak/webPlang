package webplang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import webplang.domain.Exercise;
import webplang.service.ExerciseService;

import javax.annotation.Resources;
import javax.sql.DataSource;

/**
 * Created by Michał on 2017-04-01.
 */

@Configuration
@PropertySource(value = {"classpath:database/database.properties"})
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
    public Exercise exercise() {

        Exercise e = new Exercise();
        ExerciseService es = new ExerciseService(dataSource());
        es.initializeExercise(e);
        return e;
    }
}
