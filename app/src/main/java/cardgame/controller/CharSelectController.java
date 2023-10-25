package cardgame.controller;

import cardgame.dao.CharacterDAO;
import cardgame.elements.CharacterView;
import cardgame.model.Character;
import cardgame.model.RunData;
import cardgame.utils.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CharSelectController implements Controller {
    private Stage currentStage;

    @FXML
    private FlowPane characters;

    @Override
    public void setCurrentStage(Stage stage) {
        currentStage = stage;
        CharacterDAO characterDAO = new CharacterDAO();

        for (Character c : characterDAO.getAll()) {
            CharacterView characterView = new CharacterView(c);
            characterView.setOnMouseClicked(event -> {
                System.out.println("Character selected: " + c.getName());
                RunData.player = c;
                toDeckBuild();
            });
            characters.getChildren().add(characterView);
        }
    }

    public void back(){
        try {
            (new SceneLoader()).loadScene("main-menu.fxml", currentStage, 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private void toDeckBuild(){
        try {
            (new SceneLoader()).loadScene("deck-build.fxml", currentStage, 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
