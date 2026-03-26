package chi.dijkstra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

  @Override
  public void start(Stage stage) {
    // A simple label to show on the screen
    Label label = new Label("Dijkstra Project: Engine Started!");

    // A layout pane to hold the label
    StackPane root = new StackPane(label);

    // The scene (the content inside the window)
    Scene scene = new Scene(root, 400, 300);

    stage.setTitle("Chi's Dijkstra Visualizer");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
