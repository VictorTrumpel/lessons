import bracket_balance.BracketBalance;

import postfix_sum.*;
import bracket_balance.*;
import test_queue_consol.TestQueueConsol;

import ordered_list.*;

public class Main {

  public static void main(String[] args) {

    OrderedList<Integer> list = new OrderedList<Integer>(false);

    list.add(2);

    list.delete(2);

    System.out.println(list.count() == 0);
  }
}
