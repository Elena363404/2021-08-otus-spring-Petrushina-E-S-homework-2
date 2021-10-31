package ru.otus.elena363404.service;

import ru.otus.elena363404.dao.QuestionDao;
import ru.otus.elena363404.domain.Option;
import ru.otus.elena363404.domain.Question;
import ru.otus.elena363404.exception.QuestionReadingException;
import java.util.List;

public class QuestionService {

  private final int cntAnswerForPassTest;
  private final QuestionDao dao;
  private final IOService ioService;

  public QuestionService(QuestionDao dao, int cntAnswerForPassTest, ConsoleIOService consoleIOService) {
    this.dao = dao;
    this.cntAnswerForPassTest = cntAnswerForPassTest;
    this.ioService = consoleIOService;
  }


  public void printAllQuestions() throws QuestionReadingException {

    int cntRightAnswer = 0;

    List<Question> questionList = getQuestionList();
    int questionListSize = questionList.size();

    for (int i = 0; i < questionList.size(); i++) {
      String question = questionList.get(i).getQuestion();
      int answer = questionList.get(i).getAnswer().getAnswer();
      List<Option> optionList = questionList.get(i).getOptions();

      String options = getOptions(optionList);

      ioService.out(question);
      ioService.out(options);
      ioService.out("Input num of answer: ");

      int inAnswer = ioService.readInt();

      if (answer == inAnswer) {
        cntRightAnswer = cntRightAnswer + 1;
      }
    }

    sendResultTest(cntRightAnswer, questionListSize);

  }

  private List<Question> getQuestionList() throws QuestionReadingException {
    List<Question> questionList = dao.getAllQuestions();
    return questionList;
  }

  private String getOptions(List<Option> optionList) {
    StringBuilder options = new StringBuilder();
    for (int j = 0; j < optionList.size(); j++) {
      Option option = optionList.get(j);
      options = options.append(option.getId()).append(".").append(option.getOption()).append("\n");
    }
    return options.toString();
  }

  private void sendResultTest (int cntRightAnswer, int questionListSize) {
    if (cntRightAnswer >= cntAnswerForPassTest) {

      ioService.out("\nCongratulations! You have successfully passed the test. " +  + cntRightAnswer + "/" + questionListSize);
    } else {
      ioService.out("\nYou failed test! " + cntRightAnswer + "/" + questionListSize);
    }
  }

}
