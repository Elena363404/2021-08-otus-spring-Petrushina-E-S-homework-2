package ru.otus.elena363404.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


@Service
public class ConsoleIOService implements IOService {
  private final PrintStream out;
  private final Scanner sc;

  public ConsoleIOService(PrintStream out, InputStream in) {
    this.out = out;
    this.sc = new Scanner(in);
  }

  @Override
  public void out(String message) {
    out.println(message);
  }

  @Override
  public int readInt() {
    return sc.nextInt();
  }
}
