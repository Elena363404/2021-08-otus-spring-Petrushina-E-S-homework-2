package ru.otus.elena363404.service;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConsoleService {

  public static void systemOutPrintConsole(String txtOut) {
    System.out.print(txtOut);
  }
  public static void systemOutPrintlnConsole(String txtOut) {
    System.out.println(txtOut);
  }
}
