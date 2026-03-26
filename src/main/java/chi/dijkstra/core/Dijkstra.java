package chi.dijkstra.core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {
  private final Graph graph;

  public Dijkstra(Graph graph) {
    this.graph = graph;
  }

  public double run(String start, String end) {
    Map<Node, Double> distances = new HashMap<>();
    Set<Node> visited = new HashSet<>();

    graph.getAllNodes().forEach(node -> distances.put(node, Double.POSITIVE_INFINITY));
    distances.put(graph.getNode(start), 0.0);

    PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
    queue.add(graph.getNode(start));

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      if (current.equals(graph.getNode(end))) {
        return distances.get(current);
      }

      if (visited.contains(current))
        continue;
      visited.add(current);

      for (Edge edge : current.getEdges()) {
        if (distances.get(current) + edge.getWeight() < distances.get(edge.getTarget())) {
          distances.put(edge.getTarget(), distances.get(current) + edge.getWeight());
          queue.add(edge.getTarget());
        }
      }
    }

    return distances.get(graph.getNode(end));
  }
}
