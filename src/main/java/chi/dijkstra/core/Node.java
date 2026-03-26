package chi.dijkstra.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
  private final String id;
  private final List<Edge> edges;

  /**
   * Tạo đỉnh với id cho trước, không thể rỗng (dấu cách) hoặc {@code null}
   * 
   * @param id id của đỉnh
   */
  public Node(String id) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Node id cannot be null or empty");
    }
    this.id = id;
    this.edges = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public List<Edge> getEdges() {
    return edges;
  }

  /**
   * Node bằng nhau nếu có cùng id
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Node node))
      return false;
    return Objects.equals(id, node.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return id;
  }

}
