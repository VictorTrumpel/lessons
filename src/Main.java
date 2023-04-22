import bracket_balance.BracketBalance;

import postfix_sum.*;

import java.util.ArrayList;

import java.util.*;

import recursion.*;

public class Main {

  public static void main(String[] args) {
    ReadDir readDir = new ReadDir();

    List<String> listFiles = readDir.findFiles("/Users/victortrumpel/Desktop/study/asd-1/src/recursion/test");

    System.out.println(listFiles);
  }
}
