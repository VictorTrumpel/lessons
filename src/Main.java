import bracket_balance.BracketBalance;

import postfix_sum.*;
import bracket_balance.*;
import test_queue_consol.TestQueueConsol;

import ordered_list.*;

public class Main {

  public static void main(String[] args) {
    String str = "10234567890";

    System.out.println(hash1(str));
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
