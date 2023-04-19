package recursion;

import java.util.*;

public class PrintEventItems {
  void printEven(List<Integer> list, int idx) {
    if (list.size() == idx)
      return;

    Integer num = list.get(idx);

    if (num % 2 == 0)
      System.out.println(num);

    printEven(list, idx + 1);
  }

  public void printEven(List<Integer> list) {
    printEven(list, 0);
  }
}
