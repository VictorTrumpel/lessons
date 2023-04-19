package recursion;

import java.util.*;

public class PrintEventItems {
  public void printEven(List<Integer> list) {
    if (list.size() == 0)
      return;

    Integer num = list.remove(0);

    if (num % 2 == 0)
      System.out.println(num);

    printEven(list);
  }
}
