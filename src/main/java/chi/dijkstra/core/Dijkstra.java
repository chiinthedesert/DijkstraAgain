package chi.dijkstra.core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {
  private final Graph graph;

  public Dijkstra(Graph graph) {
    this.graph = graph;
  }

  public Result run(String start, String end) {
    Map<Node, Double> distances = new HashMap<>();
    Set<Node> visited = new HashSet<>();
    Map<Node, Node> previous = new HashMap<>();

    graph.getAllNodes().forEach(node -> distances.put(node, Double.POSITIVE_INFINITY));
    distances.put(graph.getNode(start), 0.0);

    PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
    queue.add(graph.getNode(start));

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      if (current.equals(graph.getNode(end))) {
        return new Result(distances.get(graph.getNode(end)), reconstructPath(start, end, previous));
      }

      if (visited.contains(current))
        continue;
      visited.add(current);

      for (Edge edge : current.getEdges()) {
        if (distances.get(current) + edge.getWeight() < distances.get(edge.getTarget())) {
          distances.put(edge.getTarget(), distances.get(current) + edge.getWeight());
          previous.put(edge.getTarget(), current);
          queue.add(edge.getTarget());
        }
      }
    }

    return new Result(distances.get(graph.getNode(end)), reconstructPath(start, end, previous));
  }

  public List<Node> reconstructPath(String start, String end, Map<Node, Node> previous) {
    List<Node> path = new java.util.ArrayList<>();
    Node current = graph.getNode(end);

    while (current != null) {
      path.add(0, current);
      current = previous.get(current);
    }

    if (!path.isEmpty() && path.get(0).equals(graph.getNode(start))) {
      return path;
    } else {
      return java.util.Collections.emptyList();
    }

  }

  // ##########
  // HELPER CLASSES
  // ##########

  public static class Result {
    private final double distance;
    private final List<Node> path;

    public Result(double distance, List<Node> path) {
      this.distance = distance;
      this.path = path;
    }

    public double getDistance() {
      return distance;
    }

    public List<Node> getPath() {
      return path;
    }
  }
}
