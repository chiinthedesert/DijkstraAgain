package chi.dijkstra;

import chi.dijkstra.core.*;
import java.util.List;

public class MainApp {
  public static void main(String[] args) {
    Graph graph = new Graph();

    // 1. Create Nodes
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");
    graph.addNode("D");
    graph.addNode("E"); // The "Lonely Island" node

    // 2. Create Edges (The "Trap" Graph)
    // Direct path is expensive (10)
    graph.connectDirected("A", "B", 10);

    // Path through C is cheaper (2 + 3 = 5)
    graph.connectDirected("A", "C", 2);
    graph.connectDirected("C", "B", 3);

    // Path to D
    graph.connectDirected("B", "D", 4);

    // 3. Initialize Dijkstra
    Dijkstra dijkstra = new Dijkstra(graph);

    System.out.println("=== DIJKSTRA TERMINAL TEST ===");

    // --- TEST 1: Normal Shortcut ---
    runTest(dijkstra, "A", "B");

    // --- TEST 2: Multi-hop Path ---
    runTest(dijkstra, "A", "D");

    // --- TEST 3: Unreachable Node ---
    runTest(dijkstra, "A", "E");

    System.out.println("==============================");
  }

  private static void runTest(Dijkstra dijkstra, String start, String end) {
    System.out.println("\nRunning: " + start + " -> " + end);

    Dijkstra.Result result = dijkstra.run(start, end);

    System.out.println("Distance: " + result.getDistance());

    List<Node> path = result.getPath();
    if (path.isEmpty()) {
      System.out.println("Path: [NO PATH FOUND]");
    } else {
      System.out.print("Path: ");
      for (int i = 0; i < path.size(); i++) {
        System.out.print(path.get(i).getId() + (i < path.size() - 1 ? " -> " : ""));
      }
      System.out.println();
    }
  }
}
