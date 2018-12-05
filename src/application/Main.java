package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 800, 600);

			// -fx-background-color: linear-gradient(#003333, Black);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			root.setStyle("-fx-background-color: linear-gradient(#003333, Black)");
			primaryStage.show();
			primaryStage.setTitle("Test Pi³karski - ¯muda");
			primaryStage.setResizable(false);
			Menu.Startm(primaryStage, root);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
