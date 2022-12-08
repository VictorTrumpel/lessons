package bracket_balance;

import stack_head.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class BracketBalance {

  public boolean is_balance(String str) {
    HeadStack<Character> openBracketsStack = new HeadStack<Character>();

    Character close_bracket = ")".charAt(0);

    for (int i = 0; i < str.length(); i++) {
      Character symbol = str.charAt(i);

      if (symbol == close_bracket)
        break;

      openBracketsStack.push(symbol);
    }

    return openBracketsStack.size() * 2 == str.length();
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
