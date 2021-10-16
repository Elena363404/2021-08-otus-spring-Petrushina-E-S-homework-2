package ru.otus.elena363404.dao;

import ru.otus.elena363404.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {

  List<Question> getAllQuestions() throws IOException, Exception;
}
