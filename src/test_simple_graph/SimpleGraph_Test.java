package test_simple_graph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import simple_graph.*;

public class SimpleGraph_Test {

  @Test
  public void test_1() {
    SimpleGraph simple_graph = new SimpleGraph(7);

    simple_graph.AddVertex(0);
    simple_graph.AddVertex(1);
    simple_graph.AddVertex(2);
    simple_graph.AddVertex(3);
    simple_graph.AddVertex(4);
    simple_graph.AddVertex(5);
    simple_graph.AddVertex(6);

    simple_graph.AddEdge(0, 1);
    simple_graph.AddEdge(1, 2);
    simple_graph.AddEdge(2, 4);
    simple_graph.AddEdge(2, 3);

    simple_graph.AddEdge(0, 5);
    simple_graph.AddEdge(5, 6);

    ArrayList<Vertex> list = simple_graph.BreadthFirstSearch(0, 3);

    for (int i = 0; i < list.size(); i++) {
      System.out.println("list i = " + list.get(i).Value);
    }

    assertEquals(4, list.size());
    assertEquals(0, list.get(0).Value);
    assertEquals(1, list.get(1).Value);
    assertEquals(2, list.get(2).Value);
    assertEquals(3, list.get(3).Value);

    ArrayList<Vertex> list2 = simple_graph.BreadthFirstSearch(0, 5);

    assertEquals(2, list2.size());
    assertEquals(0, list2.get(0).Value);
    assertEquals(5, list2.get(1).Value);

    simple_graph.AddEdge(3, 6);

    ArrayList<Vertex> list3 = simple_graph.BreadthFirstSearch(0, 6);

    assertEquals(3, list3.size());
    assertEquals(0, list3.get(0).Value);
    assertEquals(5, list3.get(1).Value);
    assertEquals(6, list3.get(2).Value);
  }

  @Test
  public void test_2() {
    SimpleGraph simple_graph = new SimpleGraph(6);

    simple_graph.AddVertex(0);
    simple_graph.AddVertex(1);
    simple_graph.AddVertex(2);
    simple_graph.AddVertex(3);
    simple_graph.AddVertex(4);
    simple_graph.AddVertex(5);

    simple_graph.AddEdge(0, 1);
    simple_graph.AddEdge(0, 2);
    simple_graph.AddEdge(0, 3);
    simple_graph.AddEdge(0, 5);

    simple_graph.AddEdge(1, 4);
    simple_graph.AddEdge(2, 4);

    simple_graph.AddEdge(3, 2);

    simple_graph.AddEdge(5, 3);

    ArrayList<Vertex> list = simple_graph.BreadthFirstSearch(0, 3);

    assertEquals(2, list.size());
    assertEquals(0, list.get(0).Value);
    assertEquals(3, list.get(1).Value);
  }

  @Test
  public void test_3() {
    SimpleGraph simple_graph = new SimpleGraph(6);

    simple_graph.AddVertex(0); // A
    simple_graph.AddVertex(1); // B
    simple_graph.AddVertex(2); // C
    simple_graph.AddVertex(3); // D
    simple_graph.AddVertex(4); // E
    simple_graph.AddVertex(5); // F

    simple_graph.AddEdge(0, 1);
    simple_graph.AddEdge(0, 2);

    simple_graph.AddEdge(2, 3);
    simple_graph.AddEdge(3, 3);

    simple_graph.AddEdge(3, 1);
    simple_graph.AddEdge(3, 4);

    simple_graph.AddEdge(1, 4);

    simple_graph.AddEdge(4, 5);

    ArrayList<Vertex> list = simple_graph.BreadthFirstSearch(0, 4);
    assertEquals(3, list.size());
    assertEquals(0, list.get(0).Value);
    assertEquals(1, list.get(1).Value);
    assertEquals(4, list.get(2).Value);
  }
}
