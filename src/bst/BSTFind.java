package bst;

// промежуточный результат поиска
public class BSTFind<T> {
  // null если в дереве вообще нету узлов
  public BSTNode<T> Node;

  // true если узел найден
  public boolean NodeHasKey;

  // true, если родительскому узлу надо добавить новый левым
  public boolean ToLeft;

  public BSTFind() {
    Node = null;
  }
}
