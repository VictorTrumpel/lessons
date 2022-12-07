import bracket_balance.BracketBalance;

import postfix_sum.*;

public class Main {

  public static void main(String[] args) {
    PostfixSum postfixSum = new PostfixSum();

    Integer sum = postfixSum.sum("8 2 / 5 -");

    System.out.println(sum);
  }
}
