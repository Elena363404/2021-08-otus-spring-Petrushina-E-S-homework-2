package ru.otus.elena363404.dao;

import org.springframework.context.annotation.Configuration;
import ru.otus.elena363404.domain.Answer;
import ru.otus.elena363404.domain.Option;
import ru.otus.elena363404.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Configuration
public class QuestionDaoCsv implements QuestionDao {

  private final String quizPath;

  public QuestionDaoCsv(String quizPath) throws SQLException {
    this.quizPath = quizPath;
  }

  public List<Question> getAllQuestions() throws Exception {

    List<Question> listQuestion = new ArrayList<>();

    try (InputStream is = getClass().getClassLoader().getResourceAsStream(quizPath)) {

      Scanner scanner = new Scanner(is);
      scanner.useDelimiter(",|\\r\\n");
      scanner.nextLine();
      while (scanner.hasNext()) {
        Integer num = scanner.nextInt();
        String question = scanner.next();
        List<String> options = Arrays.asList(scanner.next().split("/"));
        List<Option> listOptions = new ArrayList<Option>();
        Integer idOption = 0;
        for (String option: options) {
          idOption = idOption + 1;
          listOptions.add(new Option(idOption, num, option));
        }

        listQuestion.add(new Question(num, question, listOptions, new Answer(num, Integer.parseInt(scanner.next()))));
      }
    }

    return listQuestion;
  }
}
