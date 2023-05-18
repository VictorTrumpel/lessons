public class Vertex {
  public int Value;
  public boolean Hit;
  public int PrevVertexIdx;
  public Boolean isWeak;

  public Vertex(int val) {
    Value = val;
    Hit = false;
    PrevVertexIdx = -1;
    isWeak = null;
  }
}

class SimpleGraph {
  Vertex[] vertex;
  int[][] m_adjacency;
  int max_vertex;

  public SimpleGraph(int size) {
    max_vertex = size;
    m_adjacency = new int[size][size];
    vertex = new Vertex[size];
  }

  public ArrayList<Vertex> WeakVertices() {
    resetGraph();

    ArrayList<Vertex> weakList = new ArrayList<Vertex>();

    ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

    queue.add(0);

    while (queue.size() > 0) {
      int currVertexIdx = queue.pop();

      vertex[currVertexIdx].Hit = true;

      if (vertex[currVertexIdx].isWeak == null) {
        detectTriangle(currVertexIdx);

        if (vertex[currVertexIdx].isWeak)
          weakList.add(vertex[currVertexIdx]);
      }

      for (int childVIdx = 0; childVIdx < m_adjacency[currVertexIdx].length; childVIdx++) {
        if (vertex[childVIdx].Hit == false)
          queue.add(childVIdx);
      }
    }

    return weakList;
  }

  void detectTriangle(int VIdx) {
    ArrayList<Integer> children = new ArrayList<Integer>();

    for (int i = 0; i < m_adjacency[VIdx].length; i++) {
      if (m_adjacency[VIdx][i] == 1)
        children.add(i);
    }

    for (int i = 0; i < children.size() - 1; i++) {
      int childVIdx1 = children.get(i);

      for (int j = i + 1; j < children.size(); j++) {
        int childVIdx2 = children.get(j);

        if (IsEdge(childVIdx1, childVIdx2)) {
          vertex[VIdx].isWeak = false;
          vertex[childVIdx1].isWeak = false;
          vertex[childVIdx2].isWeak = false;

          return;
        }
      }
    }

    vertex[VIdx].isWeak = true;
  }

  void resetGraph() {
    for (int i = 0; i < vertex.length; i++) {
      vertex[i].Hit = false;
      vertex[i].PrevVertexIdx = -1;
      vertex[i].isWeak = null;
    }
  }

  public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
    resetGraph();

    ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

    queue.add(VFrom);

    Integer needVertexIdx = null;

    while (queue.size() > 0) {
      Integer currVertexIdx = queue.pop();

      vertex[currVertexIdx].Hit = true;

      if (currVertexIdx == VTo) {
        needVertexIdx = currVertexIdx;
        break;
      }

      for (int childVIdx = 0; childVIdx < m_adjacency[currVertexIdx].length; childVIdx++) {
        if (!IsEdge(currVertexIdx, childVIdx))
          continue;

        if (vertex[childVIdx].PrevVertexIdx == -1)
          vertex[childVIdx].PrevVertexIdx = currVertexIdx;

        if (vertex[childVIdx].Hit == false) {
          queue.add(childVIdx);
        }
      }
    }

    ArrayList<Vertex> reversedPathOfNode = new ArrayList<Vertex>();

    if (needVertexIdx == null)
      return reversedPathOfNode;

    int currentVIdx = needVertexIdx;

    while (true) {
      reversedPathOfNode.add(vertex[currentVIdx]);

      if (currentVIdx == -1 || currentVIdx == VFrom)
        break;

      currentVIdx = vertex[currentVIdx].PrevVertexIdx;
    }

    return reverseList(reversedPathOfNode);
  }

  ArrayList<Vertex> reverseList(ArrayList<Vertex> list) {
    ArrayList<Vertex> reversedList = new ArrayList<Vertex>();

    for (int i = list.size() - 1; i >= 0; i--) {
      reversedList.add(list.get(i));
    }

    return reversedList;
  }

  public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
    resetGraph();

    ArrayList<Vertex> stack = new ArrayList<Vertex>();

    DepthFirstSearch(VFrom, VTo, stack);

    return stack;
  }

  boolean DepthFirstSearch(int VFrom, int VTo, ArrayList<Vertex> stack) {

    if (VFrom == VTo) {
      stack.add(vertex[VFrom]);
      return true;
    }

    vertex[VFrom].Hit = true;

    stack.add(vertex[VFrom]);

    for (int i = 0; i < m_adjacency[VFrom].length; i++) {
      if (m_adjacency[VFrom][i] == 1 && vertex[i].Hit == false) {
        boolean reached = DepthFirstSearch(i, VTo, stack);

        if (reached)
          return true;
        else
          stack.remove(stack.size() - 1);
      }
    }

    return false;
  }

  public void AddVertex(int value) {
    Vertex newVertex = new Vertex(value);

    Integer vertexIdx = null;
    for (int i = 0; i < vertex.length; i++) {
      if (vertex[i] == null) {
        vertexIdx = i;
        break;
      }
    }

    if (vertexIdx != null)
      vertex[vertexIdx] = newVertex;
  }

  public void RemoveVertex(int v) {
    vertex[v] = null;

    for (int i = 0; i < m_adjacency[v].length; i++) {
      m_adjacency[v][i] = 0;
    }

    for (int i = 0; i < m_adjacency[v].length; i++) {
      m_adjacency[i][v] = 0;
    }
  }

  public boolean IsEdge(int v1, int v2) {
    return m_adjacency[v1][v2] == 1;
  }

  public void AddEdge(int v1, int v2) {
    m_adjacency[v1][v2] = 1;
    m_adjacency[v2][v1] = 1;
  }

  public void RemoveEdge(int v1, int v2) {
    m_adjacency[v1][v2] = 0;
    m_adjacency[v2][v1] = 0;
  }
}