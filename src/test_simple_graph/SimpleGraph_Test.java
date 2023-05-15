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

    // simple_graph.AddEdge(3, 6);

    ArrayList<Vertex> list = simple_graph.DepthFirstSearch(0, 3);

    // for (int i = 0; i < list.size(); i++) {
    // System.out.println("list i = " + list.get(i).Value);
    // }

    ArrayList<Vertex> list2 = simple_graph.BreadthFirstSearch(0, 7);

    for (int i = 0; i < list2.size(); i++) {
      System.out.println("list i = " + list2.get(i).Value);
    }
  }
}
