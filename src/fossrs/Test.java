package fossrs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import java.util.HashMap;
import java.util.Map;
import java.lang.StringBuilder;

import fossrs.account.*;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label info = new Label();
		TextField usernameField = new TextField();
		GridPane skillGP = new GridPane();
		BorderPane gameBP = new BorderPane();
		gameBP.setPadding(new Insets(10, 20, 10, 20));
		gameBP.setRight(skillGP);
		gameBP.setCenter(usernameField);
		skillGP.setMinSize(200, 400);
		skillGP.setVgap(5);
		skillGP.setHgap(5);
		usernameField.setPromptText("Enter your username here.");
		usernameField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					skillGP.getChildren().clear();
					String username = usernameField.getText();
					OSRSAccount acc = new OSRSAccount(username);
					try {
						acc.downloadSkills(true);
						skillGP.add(new Text(acc.toString()), 0, 0);
					} catch (Exception e) {
						skillGP.add(new Text("Could not lookup " + username), 0, 0);
					}
				}
			}
		});

		Scene scene = new Scene(gameBP, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.setTitle("FOSSrs");
		primaryStage.show();
	}
}