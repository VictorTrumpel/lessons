package bracket_balance;

import stack_head.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class BracketBalance {

  public boolean is_balance(String str) {
    HeadStack<Character> headStack = new HeadStack<Character>();

    Character open_bracket = "(".charAt(0);
    Character close_bracket = ")".charAt(0);

    for (int i = 0; i < str.length(); i++) {
      Character symbol = str.charAt(i);
      Character head = headStack.peek();

      Boolean is_anti_bracket = symbol == open_bracket && head == close_bracket ||
          symbol == close_bracket && head == open_bracket;

      if (is_anti_bracket) {
        headStack.pop();
      } else {
        headStack.push(symbol);
      }
    }

    return headStack.size() == 0;
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
  }

}
