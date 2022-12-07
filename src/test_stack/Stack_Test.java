package test_stack;

import static org.junit.Assert.*;

import org.junit.Test;

import stack.*;

public class Stack_Test {

  @Test
  public void test_1() {
    Stack<Integer> stack = new Stack<Integer>();

    assertEquals(stack.size(), 0);
    assertEquals(stack.pop(), null);
    assertEquals(stack.peek(), null);

    stack.push(0);
    stack.push(1);
    stack.push(2);

    assertEquals(stack.size(), 3);
    assertEquals(stack.peek(), (Integer) 2);

    assertEquals(stack.pop(), (Integer) 2);

    assertEquals(stack.size(), 2);
    assertEquals(stack.peek(), (Integer) 1);

    assertEquals(stack.pop(), (Integer) 1);

    assertEquals(stack.size(), 1);
    assertEquals(stack.peek(), (Integer) 0);

    assertEquals(stack.pop(), (Integer) 0);

    assertEquals(stack.size(), 0);
    assertEquals(stack.peek(), null);

    assertEquals(stack.pop(), null);
    assertEquals(stack.pop(), null);

    assertEquals(stack.peek(), null);
    assertEquals(stack.peek(), null);
  }
}
