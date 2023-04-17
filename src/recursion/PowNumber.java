package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

public class PowNumber {
  private int pow(int n, int m, int factor) {
    if (m == 1) {
      return n;
    }

    int multiplex = n * factor;

    return pow(multiplex, m - 1, factor);
  }

  public int pow(int n, int m) {
    if (m == 0) {
      return 1;
    }

    return pow(n, m, n);
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
