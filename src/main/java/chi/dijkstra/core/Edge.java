package chi.dijkstra.core;

public class Edge {
  private final Node source;
  private final Node target;
  private final int weight;

  public Edge(Node source, Node target, int weight) {
    if (weight < 0) {
      throw new IllegalArgumentException("Weight cannot be negative");
    }
    this.source = source;
    this.target = target;
    this.weight = weight;
  }

  public Node getSource() {
    return source;
  }

  public Node getTarget() {
    return target;
  }

  public double getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    return String.format("%s -> %s (%d)", source.getId(), target.getId(), weight);
  }
}
