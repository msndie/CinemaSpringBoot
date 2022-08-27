package edu.school21.cinema.config;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJpaRepositories("edu.school21.cinema.repositories")
@ComponentScan("edu.school21.cinema")
public class SpringConfig {

    @Autowired
    private Environment env;

    @Bean
    public String path() {
        return env.getProperty("storage.path");
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
