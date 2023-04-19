package recursion;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class ListLength {

  public int length(List list) {
    if (list.size() == 0)
      return 0;

    list.remove(0);

    return 1 + length(list);
  }

  @Test
  public void test_1() {
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    arrayList.add(1);
    arrayList.add(2);

    assertEquals(length(arrayList), 2);
    assertEquals(length(arrayList), 0);

    ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
    arrayList1.add(1);
    arrayList1.add(2);
    arrayList1.add(3);

    assertEquals(length(arrayList1), 3);
    assertEquals(length(arrayList1), 0);
  }
}