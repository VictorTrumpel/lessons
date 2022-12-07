package postfix_sum;

import stack_head.*;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class PostfixSum {

  public Integer sum(String exp) {
    HeadStack<Integer> stackValues = new HeadStack<Integer>();

    for (int i = 0; i < exp.length(); i++) {
      Character symbol = exp.charAt(i);
      Boolean isEmpty = symbol == toChar(" ");

      if (isOperator(symbol)) {
        Integer x2 = stackValues.pop();
        Integer x1 = stackValues.pop();

        stackValues.push(calc(x1, x2, symbol));
      } else if (!isEmpty) {
        stackValues.push(Integer.valueOf(String.valueOf(symbol)));
      }
    }

    return stackValues.peek();
  }

  private Integer calc(Integer x1, Integer x2, Character operator) throws Error {
    if (toChar("+") == operator)
      return (Integer) (x1 + x2);
    if (toChar("-") == operator)
      return (Integer) (x1 - x2);
    if (toChar("/") == operator)
      return (Integer) (x1 / x2);
    if (toChar("*") == operator)
      return (Integer) (x1 * x2);

    throw new Error("unfamiliar operator");
  }

  private Character toChar(String str) {
    return str.charAt(0);
  }

  private boolean isOperator(Character c) {
    Character plus = toChar("+");
    Character minus = toChar("-");
    Character div = toChar("/");
    Character mult = toChar("*");

    return c == plus || c == minus || c == div || c == mult;
  }

  @Test
  public void test_1() {
    assertEquals(sum("8 2 + 5 * 9 +"), (Integer) 59);
    assertEquals(sum("8 2 +"), (Integer) 10);
    assertEquals(sum("8 2 /"), (Integer) 4);
    assertEquals(sum("8 2 / 5 +"), (Integer) 9);
    assertEquals(sum("8 2 / 5 -"), (Integer) (1 * -1));
  }
}
