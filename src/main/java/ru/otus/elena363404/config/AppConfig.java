package ru.otus.elena363404.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.elena363404.dao.QuestionDao;
import ru.otus.elena363404.dao.QuestionDaoCsv;
import ru.otus.elena363404.service.ConsoleIOService;
import ru.otus.elena363404.service.QuestionService;

import java.io.InputStream;
import java.io.PrintStream;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig {

  @Bean
  public QuestionDaoCsv questionDaoCsv(@Value("${quiz.path}") String quizPath) {
    return new QuestionDaoCsv(quizPath);
  }

  @Bean
  QuestionService questionService (QuestionDao dao, @Value("${cntAnswerForPassTest}") int cntAnswerForPassTest, ConsoleIOService consoleIOService) {
    return new QuestionService(dao, cntAnswerForPassTest, consoleIOService);
  }

  @Bean
  ConsoleIOService consoleIOService(@Value("#{T(java.lang.System).out}") PrintStream out, @Value("#{T(java.lang.System).in}") InputStream in) {

    return new ConsoleIOService(out, in);
  }


}
