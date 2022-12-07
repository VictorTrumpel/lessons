package stack;

import java.util.*;

public class Stack<T> {
  public ArrayList<T> array;

  public Stack() {
    this.array = new ArrayList<T>();
  }

  public int size() {
    return this.array.size();
  }

  public T pop() {
    if (this.array.size() == 0)
      return null;

    int lastIdx = this.array.size() - 1;
    T lastItem = this.peek();
    this.array.remove(lastIdx);
    return lastItem;
  }

  public void push(T val) {
    this.array.add(val);
  }

  public T peek() {
    if (this.array.size() == 0)
      return null;
    int lastIdx = this.array.size() - 1;
    T lastItem = this.array.get(lastIdx);
    return lastItem;
  }
}
