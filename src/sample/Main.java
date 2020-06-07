package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class Main extends Application {
    Media move = new Media(getClass().getResource("/ground.mpeg").toURI().toString());
    MediaPlayer movePlayer = new MediaPlayer(move);

    public Main() throws URISyntaxException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene gameScene = new Scene(root, 900, 900);
        primaryStage.setTitle("Pro Chess");
        primaryStage.setScene(gameScene);
        primaryStage.show();
        movePlayer.setAutoPlay(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
