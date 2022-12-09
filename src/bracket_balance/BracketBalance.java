package bracket_balance;

import static org.junit.Assert.*;

import org.junit.Test;

import stack_head.*;

public class BracketBalance {

  public boolean is_balance(String str) {
    HeadStack<Character> headStack = new HeadStack<Character>();

    Character open_bracket = "(".charAt(0);
    Character close_bracket = ")".charAt(0);

    for (int i = 0; i < str.length(); i++) {
      Character symbol = str.charAt(i);
      Character head = headStack.peek();

      Boolean is_closed_bracket = head == open_bracket && symbol == close_bracket;

      System.out.print("is_closed_bracket: ");
      System.out.println(is_closed_bracket);

      if (is_closed_bracket) {
        headStack.pop();
      } else {
        headStack.push(symbol);
      }
    }

    return headStack.size() == 0;
  }

  @Test
  public void test_1() {
    assertEquals(this.is_balance("()()"), true);
    assertEquals(this.is_balance("()"), true);
    assertEquals(this.is_balance("((()))"), true);
    assertEquals(this.is_balance("(((()))"), false);
    assertEquals(this.is_balance("((())))"), false);
    assertEquals(this.is_balance("((())"), false);
    assertEquals(this.is_balance("((("), false);
    assertEquals(this.is_balance("("), false);
    assertEquals(this.is_balance(")"), false);
    assertEquals(this.is_balance("))"), false);
    assertEquals(this.is_balance("()"), true);

    assertEquals(this.is_balance(")))))((((("), false);

    assertEquals(this.is_balance(")("), false);
    assertEquals(this.is_balance("()())"), false);

    assertEquals(this.is_balance("())())"), false);

    assertEquals(this.is_balance("()()()"), true);

    assertEquals(this.is_balance(")()("), false);

    assertEquals(this.is_balance("(((((("), false);

    assertEquals(this.is_balance(")(((((("), false);
    assertEquals(this.is_balance("()())(((((("), false);

    assertEquals(this.is_balance(")"), false);

    assertEquals(this.is_balance("())"), false);
    assertEquals(this.is_balance("((((((())))))))"), false);
  }

}
