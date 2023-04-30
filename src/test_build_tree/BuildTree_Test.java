package test_build_tree;

import static org.junit.Assert.*;

import org.junit.Test;

import build_tree.*;

public class BuildTree_Test {

  @Test
  public void test_1() {
    int[] nodes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };

    int[] tree = AlgorithmsDataStructures2.GenerateBBSTArray(nodes);

    int[] rightTreee = { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15 };

    assertArrayEquals(rightTreee, tree);
  }

  @Test
  public void test_2() {
    int[] nodes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

    int[] tree = AlgorithmsDataStructures2.GenerateBBSTArray(nodes);

    int[] rightTreee = { 8, 4, 11, 2, 6, 9, 13, 1, 3, 5, 7, 8, 10, 12 };

    assertArrayEquals(rightTreee, tree);
  }

  @Test
  public void test_3() {
    int[] nodes = { 1, 10, 14, 4, 11, 7, 6, 8, 9, 2, 5, 12, 13, 3 };

    int[] tree = AlgorithmsDataStructures2.GenerateBBSTArray(nodes);

    int[] rightTreee = { 8, 4, 11, 2, 6, 9, 13, 1, 3, 5, 7, 8, 10, 12 };

    assertArrayEquals(rightTreee, tree);
  }

  @Test
  public void test_4() {
    int[] nodes = { 1, 10, 14 };

    int[] tree = AlgorithmsDataStructures2.GenerateBBSTArray(nodes);

    int[] rightTreee = { 10, 1, 14 };

    assertArrayEquals(rightTreee, tree);
  }

  @Test
  public void test_5() {
    int[] nodes = { 1 };

    int[] tree = AlgorithmsDataStructures2.GenerateBBSTArray(nodes);

    int[] rightTreee = { 1 };

    assertArrayEquals(rightTreee, tree);
  }

  @Test
  public void test_6() {
    int[] nodes = {};

    int[] tree = AlgorithmsDataStructures2.GenerateBBSTArray(nodes);

    int[] rightTreee = {};

    assertArrayEquals(rightTreee, tree);
  }
}
