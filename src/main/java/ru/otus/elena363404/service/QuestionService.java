package ru.otus.elena363404.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.elena363404.dao.QuestionDao;
import ru.otus.elena363404.domain.Option;
import ru.otus.elena363404.domain.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.otus.elena363404.service.SystemConsoleService.systemOutPrintConsole;
import static ru.otus.elena363404.service.SystemConsoleService.systemOutPrintlnConsole;

@Configuration
public class QuestionService {

  @Value("${cntRightAnswers}")
  private Integer cntRightAnswers;

  private final QuestionDao dao;

  public QuestionService(QuestionDao dao) {
    this.dao = dao;
  }

  public void printAllQuestions() {

    List<Question> questionList = new ArrayList<>();
    try {
      questionList = dao.getAllQuestions();
    } catch (Exception err) {
      systemOutPrintlnConsole("Error while reading file");
    }

    Integer cntRightAnswer = 0;
    StringBuilder sbAnswers = new StringBuilder();

    try (Scanner in = new Scanner(System.in)) {
      for (int i = 0; i < questionList.size(); i++) {
        String question = questionList.get(i).getQuestion();
        int answer = questionList.get(i).getAnswer().getAnswer();
        List<Option> optionList = questionList.get(i).getOptions();
        StringBuilder options = new StringBuilder();
        for (int j = 0; j < optionList.size(); j++) {
          Option option = optionList.get(j);
          options = options.append(option.getId()).append(".").append(option.getOption()).append("\n");
        }
        systemOutPrintlnConsole(question);
        systemOutPrintlnConsole(options.toString());

        systemOutPrintConsole("Input num of answer: ");
        int inAnswer = in.nextInt();
        if (answer == inAnswer) {
          cntRightAnswer = cntRightAnswer + 1;
        }
        sbAnswers = sbAnswers.append(questionList.get(i).getNum()).append(".");
      }
    }
    if (cntRightAnswer >= cntRightAnswers) {
      systemOutPrintlnConsole("\nCongratulations! You have successfully passed the test. " +  + cntRightAnswer + "/" + questionList.size());
    } else {
      systemOutPrintlnConsole("\nYou failed test! " + cntRightAnswer + "/" + questionList.size());
    }

  }

}
