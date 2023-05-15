package simple_graph;

public class Vertex {
  public int Value;
  public boolean Hit;
  public int PrevVertexIdx;

  public Vertex(int val) {
    Value = val;
    Hit = false;
    PrevVertexIdx = -1;
  }
}