package cardgame.controller;

import cardgame.dao.CardDAO;
import cardgame.dao.CharacterDAO;
import cardgame.dao.EnemyDAO;
import cardgame.elements.CardView;
import cardgame.elements.CharacterView;
import cardgame.elements.EnemyView;
import cardgame.model.Card;
import cardgame.model.Character;
import cardgame.model.Enemy;
import cardgame.utils.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CollectionController implements Controller {

    private Stage currentStage;
    @FXML 
    private FlowPane cards;

    @FXML 
    private FlowPane characters;

    @FXML 
    private FlowPane enemies;

    @Override
    public void setCurrentStage(Stage stage) {
        this.currentStage = stage;
        CardDAO cardDAO = new CardDAO();
        CharacterDAO characterDAO = new CharacterDAO();
        EnemyDAO enemyDAO = new EnemyDAO();

        for (Card c : cardDAO.getAll()) {
            CardView cardView = new CardView(c);
            cards.getChildren().add(cardView);
        }

        for (Character c : characterDAO.getAll()) {
            CharacterView characterView = new CharacterView(c);
            characters.getChildren().add(characterView);
        }

        for (Enemy e : enemyDAO.getAll()){
            EnemyView enemyView = new EnemyView(e);
            enemies.getChildren().add(enemyView);
        }
    }

    @FXML
    public void back(){
        try {
            (new SceneLoader()).loadScene("main-menu.fxml", currentStage, 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
