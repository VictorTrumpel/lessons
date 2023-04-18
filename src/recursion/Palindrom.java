package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

public class Palindrom {

  public boolean isPalindrom(String str) {
    if (str.length() == 1)
      return true;

    char c1 = str.charAt(0);
    char c2 = str.charAt(str.length() - 1);
    String newString = str.substring(1, str.length() - 1);

    if (c1 != c2)
      return false;

    return isPalindrom(newString);
  }

  @Test
  public void test_1() {
    assertEquals(isPalindrom("aga"), true);

    assertEquals(isPalindrom("agaa"), false);

    assertEquals(isPalindrom("aggaa"), false);

    assertEquals(isPalindrom("ротор"), true);
  }

}
