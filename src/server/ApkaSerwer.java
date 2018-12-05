package server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import server.Serwer;
import server.JDBC;

public class ApkaSerwer extends Application {
	Group sc = new Group();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Serwer - ZmudaTest");
		Serwer serwer = new Serwer(753);
		Serwer serwer2 = new Serwer(754);
		Serwer serwer3 = new Serwer(755);

		Button p1 = new Button();

		p1.setText("Start Serwera");
		p1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				p1.setVisible(false);
				Platform.runLater(new Runnable() {
					public void run() {
						JDBC.init();
						JDBC.initPytania();

					}
				});
				serwer.start();
				serwer2.start();
				serwer3.start();
			}
		});
		p1.setLayoutX(10);
		p1.setLayoutY(10);

		Button p2 = new Button();
		p2.setText("Wyjœcie");
		p2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Serwer Skoñczy³ Pracê");
				if (serwer.isAlive())
					serwer.close();
				if (serwer2.isAlive())
					serwer2.close();
				primaryStage.close();
			}
		});
		p2.setLayoutX(10);
		p2.setLayoutY(50);

		sc.getChildren().add(p1);
		sc.getChildren().add(p2);

		Group root = new Group();
		root.getChildren().add(sc);
		primaryStage.setResizable(false);
		Scene scene = new Scene(root, 100, 100);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
