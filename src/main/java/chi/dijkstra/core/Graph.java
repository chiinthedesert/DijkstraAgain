package chi.dijkstra.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Đồ thị (Graph) quản lý tập hợp các đỉnh và cạnh.
 * <p>
 * Lớp này cung cấp các phương thức để thêm đỉnh, tạo cạnh (có hướng hoặc vô
 * hướng)
 * <p>
 * Đồ thị được sử dụng làm đầu vào cho thuật toán {@link Dijkstra}
 */
public class Graph {
  // ##########
  // QUẢN LÝ ĐỈNH
  // ##########

  private final Map<String, Node> nodes;

  public Graph() {
    this.nodes = new HashMap<>();
  }

  public void addNode(String id) {
    nodes.computeIfAbsent(id, Node::new);
  }

  public Node getNode(String id) {
    return nodes.get(id);
  }

  public Collection<Node> getAllNodes() {
    return nodes.values();
  }

  // ##########
  // QUẢN LÝ CẠNH
  // ##########

  /**
   * Thêm cạnh từ đỉnh {@code from} đến đỉnh {@code to} với độ cạnh
   * {@code weight}, xóa cạnh đó trước khi thêm nếu tồn tại.
   * 
   * @param from
   * @param to
   * @param weight
   */
  public void connectDirected(String from, String to, int weight) {
    Node source = nodes.get(from);
    Node target = nodes.get(to);
    if (source == null || target == null) {
      throw new IllegalArgumentException("Nodes not found");
    }
    source.getEdges().removeIf(edge -> edge.getTarget().equals(target));

    source.getEdges().add(new Edge(source, target, weight));
  }

  public void connectUndirected(String from, String to, int weight) {
    connectDirected(from, to, weight);
    connectDirected(to, from, weight);
  }

  // ##########
  // HELPER METHODS
  // ##########

}
