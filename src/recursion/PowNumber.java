package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

public class PowNumber {
  Integer count = null;

  public int pow(int n, int m) {
    if (count == null) {
      count = n;
    }

    if (m == 1) {
      count = null;
      return n;
    }

    int multiplex = n * count;

    return pow(multiplex, m - 1);
  }

  @Test
  public void test_1() {
    assertEquals(this.pow(2, 2), 4);

    assertEquals(this.pow(2, 4), 16);

    assertEquals(this.pow(3, 2), 9);

    assertEquals(this.pow(4, 2), 16);
  }
}
