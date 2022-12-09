import bracket_balance.BracketBalance;

import postfix_sum.*;
import bracket_balance.*;

public class Main {

  public static void main(String[] args) {
    PostfixSum postfixSum = new PostfixSum();

    Character open_bracket = "(".charAt(0);
    Character close_bracket = ")".charAt(0);

    BracketBalance bracketBalance = new BracketBalance();

    bracketBalance.is_balance(")(");

  }
}
