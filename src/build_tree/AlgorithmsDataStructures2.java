package build_tree;

import java.util.*;

public class AlgorithmsDataStructures2 {

  public static int[] GenerateBBSTArray(int[] a) {
    int[] Tree = new int[a.length];

    Arrays.sort(a);

    GenerateBBSTArray(a, null, Tree);

    return Tree;
  }

  static void GenerateBBSTArray(int[] nodes, Integer parentIdx, int[] accum) {
    if (nodes.length == 0)
      return;

    int middleIdx;

    if (nodes.length == 1)
      middleIdx = 0;
    else
      middleIdx = ((Double) Math.floor(nodes.length / 2)).intValue();

    int nodeKey = nodes[middleIdx];

    int nodeIdx;

    if (parentIdx == null)
      nodeIdx = 0;
    else
      nodeIdx = nodeKey < accum[parentIdx]
          ? parentIdx * 2 + 1
          : parentIdx * 2 + 2;

    if (nodeIdx >= accum.length)
      return;

    accum[nodeIdx] = nodeKey;

    int[] leftNodes = Arrays.copyOfRange(nodes, 0, middleIdx);
    int[] rightNodes = Arrays.copyOfRange(nodes, middleIdx, nodes.length);

    GenerateBBSTArray(leftNodes, nodeIdx, accum);
    GenerateBBSTArray(rightNodes, nodeIdx, accum);
  }

}