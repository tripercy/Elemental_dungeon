package cardgame;

import cardgame.utils.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneLoader sceneLoader = new SceneLoader();

        sceneLoader.loadScene("main-menu.fxml", primaryStage, 800, 600);

        primaryStage.setTitle("Card Game");
        primaryStage.show();
    }
}