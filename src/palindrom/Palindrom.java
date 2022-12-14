package palindrom;

import deque.*;

public class Palindrom {

  public boolean isPalindrom(String str) {
    if (str.length() % 2 == 0)
      return false;

    Deque<Character> deque = new Deque<Character>();
    
    for (int i = 0; i < (str.length() - 1) / 2; i++) {
      Character symbol = str.charAt(i);

    }
    

    return false;
  }

}
