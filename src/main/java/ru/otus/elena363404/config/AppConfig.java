package ru.otus.elena363404.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.elena363404.dao.QuestionDao;
import ru.otus.elena363404.dao.QuestionDaoCsv;
import ru.otus.elena363404.service.QuestionService;

import java.sql.SQLException;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig {

  int cntAnswerForPassTest;

  @Bean
  public QuestionDaoCsv questionDaoCsv(@Value("${quiz.path}") String quizPath) throws SQLException {
    return new QuestionDaoCsv(quizPath);
  }

  @Bean
  QuestionService questionService (QuestionDao dao, @Value("${cntAnswerForPassTest}") int cntAnswerForPassTest) {
    return new QuestionService(dao, cntAnswerForPassTest);
  }


}
