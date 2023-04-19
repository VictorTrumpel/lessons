package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

public class Palindrom {

  public boolean isPalindrom(String str) {
    if (str.length() == 1)
      return true;

    if (str.charAt(0) != str.charAt(str.length() - 1))
      return false;

    return isPalindrom(str.substring(1, str.length() - 1));
  }

  @Test
  public void test_1() {
    assertEquals(isPalindrom("aga"), true);

    assertEquals(isPalindrom("agaa"), false);

    assertEquals(isPalindrom("aggaa"), false);

    assertEquals(isPalindrom("ротор"), true);
  }

}
