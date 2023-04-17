package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntCharSum {
  public int sumChars(int n) {
    if (n == 0) {
      return n;
    }

    return n % 10 + sumChars(n / 10);
  }

  @Test
  public void test_1() {
    assertEquals(this.sumChars(123), 6);

    assertEquals(this.sumChars(444), 12);

    assertEquals(this.sumChars(0), 0);

    assertEquals(this.sumChars(10), 1);

    assertEquals(this.sumChars(101), 2);

    assertEquals(this.sumChars(1010005), 7);

    assertEquals(this.sumChars(10100056), 13);
  }
}
