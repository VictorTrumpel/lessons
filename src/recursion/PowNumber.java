package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

public class PowNumber {
  Integer factor = null;

  public int pow(int n, int m) {
    if (factor == null) {
      factor = n;
    }

    if (m == 1) {
      factor = null;
      return n;
    }

    int multiplex = n * factor;

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
