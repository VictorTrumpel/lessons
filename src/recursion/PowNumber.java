package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

public class PowNumber {
  public int pow(int n, int m) {
    if (m == 0) {
      return 1;
    }

    if (m == 1) {
      return n;
    }

    return n * pow(n, m - 1);
  }

  @Test
  public void test_1() {
    assertEquals(this.pow(2, 2), 4);

    assertEquals(this.pow(2, 4), 16);

    assertEquals(this.pow(3, 2), 9);

    assertEquals(this.pow(4, 2), 16);

    assertEquals(this.pow(5, 3), 125);

    assertEquals(this.pow(5, 0), 1);
  }
}
