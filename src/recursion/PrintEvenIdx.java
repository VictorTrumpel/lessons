package recursion;

import java.util.*;

public class PrintEvenIdx {
  void printEven(List<Integer> list, int idx) {
    if (list.size() == idx)
      return;

    Integer num = list.get(idx);

    if (idx % 2 == 0)
      System.out.println(num);

    printEven(list, idx + 1);
  }

  public void printEven(List<Integer> list) {
    printEven(list, 0);
  }
}
