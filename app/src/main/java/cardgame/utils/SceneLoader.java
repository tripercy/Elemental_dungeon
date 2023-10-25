package cardgame.utils;

import cardgame.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {
    public Controller loadScene(String sceneName, Stage stage, int width, int height) throws IOException{
        sceneName = "/views/" + sceneName;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load(), width, height);

        Controller controller = fxmlLoader.getController();
        controller.setCurrentStage(stage);
        stage.setScene(scene);

        return controller;
    }
}
