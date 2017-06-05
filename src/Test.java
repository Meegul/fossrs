import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import java.util.HashMap;
import java.util.Map;


public class Test extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label info = new Label();
		TextField sample = new TextField();
		sample.setPromptText("Enter your username here.");
		sample.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					RunescapeAccount acc = RunescapeAccount.lookupAccount(sample.getText());
					Map<String, Integer> skills = acc.skills;
					for (Map.Entry<String, Integer> entry : skills.entrySet()) {
						String key = entry.getKey();
						Integer value = entry.getValue();
						info.setText(info.getText() + "\n" + key + ":" + value);
					}
				}
			}
		});


		BorderPane bp = new BorderPane();
    	bp.setPadding(new Insets(10, 20, 10, 20));
		Scene scene = new Scene(bp, 300, 200, Color.BLACK);
		
		bp.setCenter(sample);
		bp.setBottom(info);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Asah, dude");
		primaryStage.show();
	}
}