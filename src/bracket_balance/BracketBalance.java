package bracket_balance;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BracketBalance {

  public boolean is_balance(String str) {
    ArrayList<Character> stack = new ArrayList<Character>();

    Character close_bracket = ")".charAt(0);

    for (int i = 0; i < str.length(); i++) {
      Character symbol = str.charAt(i);

      if (symbol == close_bracket)
        break;

      stack.add(symbol);
    }

    return stack.size() * 2 == str.length();
  }

  @Test
  public void test_1() {
    assertEquals(this.is_balance("((()))"), true);
    assertEquals(this.is_balance("(((()))"), false);
    assertEquals(this.is_balance("((())))"), false);
    assertEquals(this.is_balance("((())"), false);
    assertEquals(this.is_balance("((("), false);
    assertEquals(this.is_balance("("), false);
    assertEquals(this.is_balance(")"), false);
    assertEquals(this.is_balance("))"), false);
    assertEquals(this.is_balance("()"), true);

    assertEquals(this.is_balance(")("), false);
    assertEquals(this.is_balance("()())"), false);

    assertEquals(this.is_balance("())())"), false);

    assertEquals(this.is_balance("()()()"), false);

    assertEquals(this.is_balance(")()("), false);

    assertEquals(this.is_balance("()()"), false);

    assertEquals(this.is_balance("(((((("), false);

    assertEquals(this.is_balance(")(((((("), false);
    assertEquals(this.is_balance("()())(((((("), false);
  }

}
