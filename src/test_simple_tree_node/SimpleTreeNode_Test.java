package test_simple_tree_node;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

import simple_tree_node.*;

public class SimpleTreeNode_Test {

  @Test
  public void test_1() {
    // Тестирование добавления узла
    SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);

    SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

    tree.AddChild(root, root);

    assertEquals(tree.Root.Children, null);

    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<Integer>(1, null);

    tree.AddChild(root, node1);

    assertEquals(1, tree.Root.Children.size());
    assertEquals((Integer) 1, tree.Root.Children.get(0).NodeValue);

    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<Integer>(2, null);

    tree.AddChild(root, node2);

    assertEquals(2, tree.Root.Children.size());
    assertEquals((Integer) 2, tree.Root.Children.get(1).NodeValue);

    assertEquals(root, node1.Parent);
    assertEquals(root, node2.Parent);
  }

  @Test
  public void test_2() {
    // Тестирование удаления узла
    SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);

    SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<Integer>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<Integer>(4, null);

    tree.AddChild(root, node1);
    tree.AddChild(root, node2);

    assertEquals(root.Children.size(), 2);

    tree.DeleteNode(node1);

    assertEquals(1, root.Children.size());
    assertEquals(null, node1.Parent);

    tree.DeleteNode(root);

    assertEquals(tree.Root, root);

    tree.AddChild(node2, node4);

    assertEquals(1, node2.Children.size());

    tree.DeleteNode(node2);

    assertEquals(0, root.Children.size());
  }

  @Test
  public void test_3() {
    // Тестирование получения узлов
    SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<Integer>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<Integer>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<Integer>(5, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<Integer>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<Integer>(8, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<Integer>(9, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<Integer>(10, null);
    SimpleTreeNode<Integer> node11 = new SimpleTreeNode<Integer>(11, null);

    SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

    tree.AddChild(root, node1);
    tree.AddChild(root, node2);

    tree.AddChild(node2, node3);
    tree.AddChild(node2, node4);
    tree.AddChild(node2, node5);

    tree.AddChild(node1, node6);

    tree.AddChild(node6, node7);
    tree.AddChild(node6, node8);
    tree.AddChild(node6, node9);

    tree.AddChild(node9, node10);
    tree.AddChild(node9, node11);

    List<SimpleTreeNode<Integer>> listOfAllNodes = tree.GetAllNodes();

    ArrayList<Integer> allValues = new ArrayList<Integer>();
    for (int i = 0; i < listOfAllNodes.size(); i++) {
      allValues.add(listOfAllNodes.get(i).NodeValue);
    }

    assertEquals(12, allValues.size());

    assertEquals(true, allValues.contains(1));
    assertEquals(true, allValues.contains(2));
    assertEquals(true, allValues.contains(3));
    assertEquals(true, allValues.contains(4));
    assertEquals(true, allValues.contains(5));
    assertEquals(true, allValues.contains(6));
    assertEquals(true, allValues.contains(7));
    assertEquals(true, allValues.contains(8));
    assertEquals(true, allValues.contains(9));
    assertEquals(true, allValues.contains(10));
    assertEquals(true, allValues.contains(11));
    assertEquals(true, allValues.contains(0));
  }

  @Test
  public void test_4() {
    // Тестирование получения узлов по заданному значению
    SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<Integer>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<Integer>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node11 = new SimpleTreeNode<Integer>(4, null);
    SimpleTreeNode<Integer> node12 = new SimpleTreeNode<Integer>(7, null);

    SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

    tree.AddChild(root, node1);
    tree.AddChild(root, node2);

    tree.AddChild(node2, node3);

    tree.AddChild(node3, node4);
    tree.AddChild(node3, node5);
    tree.AddChild(node3, node6);

    tree.AddChild(node5, node7);
    tree.AddChild(node5, node8);

    tree.AddChild(node1, node9);

    tree.AddChild(node9, node10);
    tree.AddChild(node9, node11);
    tree.AddChild(node9, node12);

    List<SimpleTreeNode<Integer>> listOfValue_2 = tree.FindNodesByValue(2);

    assertEquals(3, listOfValue_2.size());

    List<SimpleTreeNode<Integer>> listOfValue_6 = tree.FindNodesByValue(6);

    assertEquals(2, listOfValue_6.size());

    List<SimpleTreeNode<Integer>> listOfValue_0 = tree.FindNodesByValue(0);

    assertEquals(1, listOfValue_0.size());

    List<SimpleTreeNode<Integer>> listOfValue_16 = tree.FindNodesByValue(16);

    assertEquals(0, listOfValue_16.size());

    List<SimpleTreeNode<Integer>> listOfValue_3 = tree.FindNodesByValue(3);

    assertEquals(3, listOfValue_3.size());
  }

  @Test
  public void test_5() {
    // Тестирование перемещение узла
    SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<Integer>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<Integer>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node11 = new SimpleTreeNode<Integer>(4, null);
    SimpleTreeNode<Integer> node12 = new SimpleTreeNode<Integer>(7, null);

    SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

    tree.AddChild(root, node1);
    tree.AddChild(root, node2);

    tree.AddChild(node2, node3);

    tree.AddChild(node3, node4);
    tree.AddChild(node3, node5);
    tree.AddChild(node3, node6);

    tree.AddChild(node5, node7);
    tree.AddChild(node5, node8);

    tree.AddChild(node1, node9);

    tree.AddChild(node9, node10);
    tree.AddChild(node9, node11);
    tree.AddChild(node9, node12);

    tree.MoveNode(node9, root);

    assertEquals(3, root.Children.size());
    assertEquals(null, node1.Children);

    List<SimpleTreeNode<Integer>> listOfNodes = tree.GetAllNodes();

    assertEquals(13, listOfNodes.size());
  }

  @Test
  public void test_6() {
    // Тестирование подсчета узлов
    SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<Integer>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<Integer>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node11 = new SimpleTreeNode<Integer>(4, null);
    SimpleTreeNode<Integer> node12 = new SimpleTreeNode<Integer>(7, null);

    SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

    tree.AddChild(root, node1);
    tree.AddChild(root, node2);

    tree.AddChild(node2, node3);

    tree.AddChild(node3, node4);
    tree.AddChild(node3, node5);
    tree.AddChild(node3, node6);

    tree.AddChild(node5, node7);
    tree.AddChild(node5, node8);

    tree.AddChild(node1, node9);

    tree.AddChild(node9, node10);
    tree.AddChild(node9, node11);
    tree.AddChild(node9, node12);

    int countOfNodes = tree.Count();

    assertEquals(13, countOfNodes);
  }

  @Test
  public void test_7() {
    // Тестирование подсчета узлов
    SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<Integer>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<Integer>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node11 = new SimpleTreeNode<Integer>(4, null);
    SimpleTreeNode<Integer> node12 = new SimpleTreeNode<Integer>(7, null);

    SimpleTree<Integer> tree = new SimpleTree<Integer>(root);

    tree.AddChild(root, node1);
    tree.AddChild(root, node2);

    tree.AddChild(node2, node3);

    tree.AddChild(node3, node4);
    tree.AddChild(node3, node5);
    tree.AddChild(node3, node6);

    tree.AddChild(node5, node7);
    tree.AddChild(node5, node8);

    tree.AddChild(node1, node9);

    tree.AddChild(node9, node10);
    tree.AddChild(node9, node11);
    tree.AddChild(node9, node12);

    int countOfNodes = tree.LeafCount();

    assertEquals(7, countOfNodes);
  }

  @Test
  public void test_8() {
    // Тестирование поиска четных деревьев
    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<Integer>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<Integer>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<Integer>(5, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<Integer>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<Integer>(8, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<Integer>(9, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<Integer>(10, null);

    node1.Children = new ArrayList<>();
    node1.Children.add(node2);
    node1.Children.add(node3);
    node1.Children.add(node6);

    node2.Parent = node1;
    node3.Parent = node1;
    node6.Parent = node1;

    node3.Children = new ArrayList<>();
    node3.Children.add(node4);
    node4.Parent = node3;

    node2.Children = new ArrayList<>();
    node2.Children.add(node5);
    node2.Children.add(node7);
    node5.Parent = node2;
    node7.Parent = node2;

    node6.Children = new ArrayList<>();
    node6.Children.add(node8);
    node8.Parent = node6;

    node8.Children = new ArrayList<>();
    node8.Children.add(node9);
    node8.Children.add(node10);
    node9.Parent = node8;
    node10.Parent = node8;

    SimpleTree<Integer> tree = new SimpleTree<Integer>(node1);

    ArrayList<Integer> evenList = tree.EvenTrees(node1);

    for (int i = 0; i < evenList.size(); i++) {
      System.out.println("even i = " + evenList.get(i));
    }

    assertEquals((Integer) 1, evenList.get(0));
    assertEquals((Integer) 3, evenList.get(1));
    assertEquals((Integer) 1, evenList.get(2));
    assertEquals((Integer) 6, evenList.get(3));
  }

  @Test
  public void test_9() {
    // Тестирование поиска четных деревьев
    SimpleTreeNode<Integer> node1 = new SimpleTreeNode<Integer>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<Integer>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<Integer>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<Integer>(4, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<Integer>(6, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<Integer>(8, null);

    node1.Children = new ArrayList<>();
    node1.Children.add(node2);
    node1.Children.add(node3);
    node1.Children.add(node6);

    node2.Parent = node1;
    node3.Parent = node1;
    node6.Parent = node1;

    node3.Children = new ArrayList<>();
    node3.Children.add(node4);
    node4.Parent = node3;

    node6.Children = new ArrayList<>();
    node6.Children.add(node8);
    node8.Parent = node6;

    SimpleTree<Integer> tree = new SimpleTree<Integer>(node1);

    ArrayList<Integer> evenList = tree.EvenTrees(node1);

    assertEquals((Integer) 1, evenList.get(0));
    assertEquals((Integer) 3, evenList.get(1));
    assertEquals((Integer) 1, evenList.get(2));
    assertEquals((Integer) 6, evenList.get(3));
  }
}
