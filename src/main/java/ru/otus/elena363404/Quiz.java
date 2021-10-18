package ru.otus.elena363404;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.elena363404.config.AppConfig;
import ru.otus.elena363404.domain.Question;
import ru.otus.elena363404.exception.QuestionReadingException;
import ru.otus.elena363404.service.QuestionService;

import java.util.Scanner;

@ComponentScan
@Configuration
public class Quiz {

  public static void main(String[] args) throws QuestionReadingException {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    QuestionService service = context.getBean(QuestionService.class);
    service.printAllQuestions();
  }
}
