import bracket_balance.BracketBalance;

import postfix_sum.*;

import java.util.ArrayList;

import bracket_balance.*;
import test_queue_consol.TestQueueConsol;

import ordered_list.*;

import java.util.*;

import recursion.PrintEventItems;

public class Main {

  public static void main(String[] args) {
    PrintEventItems printItems = new PrintEventItems();

    List<Integer> list = new ArrayList<Integer>();
    list.add(1);
    list.add(2);
    list.add(5);
    list.add(6);

    printItems.printEven(list);

    printItems.printEven(list);

  }

  static int hash1(String str1) {
    int seed = 17;
    int result = 0;

    for (int i = 0; i < str1.length(); i++) {
      int code = (int) str1.charAt(i);

      result = (seed * result + code) % 32;
    }

    return result;
  }
}
