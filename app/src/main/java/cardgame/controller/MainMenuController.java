package cardgame.controller;

import cardgame.utils.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenuController implements Controller {
    private Stage currentStage;

    @FXML
    private Label playLbl;

    @FXML
    private Label colLbl;

    @FXML
    private Label managerLbl;

    @Override
    public void setCurrentStage(Stage stage) {
        currentStage = stage;

        playLbl.setOnMouseClicked(event -> {
            System.out.println("Play");
        });

        colLbl.setOnMouseClicked(event -> {
            try {
                (new SceneLoader()).loadScene("collection.fxml", currentStage, 800, 600);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        managerLbl.setOnMouseClicked(event -> {
            try {
                (new SceneLoader()).loadScene("manager.fxml", currentStage, 800, 600);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
