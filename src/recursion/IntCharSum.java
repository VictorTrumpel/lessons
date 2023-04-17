package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntCharSum {
  int sum = 0;

  public int sumChars(int n) {
    if (n == 0) {
      int result = sum;
      sum = 0;
      return result;
    }

    int del = (int) Math.pow(10, Integer.toString(n).length() - 1);

    int reminder = n % del;
    int num = (int) Math.floor(n / del);

    sum += num;

    return sumChars(reminder);
  }

  @Test
  public void test_1() {
    assertEquals(this.sumChars(123), 6);

    assertEquals(this.sumChars(444), 12);

    assertEquals(this.sumChars(0), 0);

    assertEquals(this.sumChars(10), 1);

    assertEquals(this.sumChars(101), 2);

    assertEquals(this.sumChars(1010005), 7);
  }
}
