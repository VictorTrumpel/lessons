class Vertex {
  public int Value;

  public Vertex(int val) {
    Value = val;
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