package cardgame;

import cardgame.controller.Controller;
import cardgame.controller.ManagerController;
import cardgame.utils.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneLoader sceneLoader = new SceneLoader();

        Controller controller = sceneLoader.loadScene("manager.fxml", primaryStage, 800, 600);

        primaryStage.setTitle("Manager");
        primaryStage.show();
    }
}