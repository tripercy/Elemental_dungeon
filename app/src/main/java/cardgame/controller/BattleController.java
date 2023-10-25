package cardgame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cardgame.dao.EnemyDAO;
import cardgame.elements.CardView;
import cardgame.elements.CharacterView;
import cardgame.elements.EnemyView;
import cardgame.model.Battle;
import cardgame.model.Card;
import cardgame.model.Character;
import cardgame.model.Element;
import cardgame.model.Enemy;
import cardgame.model.RunData;
import cardgame.model.battleHandler.BattleHandler;
import cardgame.model.cardContainer.Deck;
import cardgame.utils.RandomSelector;
import cardgame.utils.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BattleController implements Controller {
    private Stage currentStage;
    private Battle currentBattle;
    private BattleHandler battleHandler;

    @FXML
    private HBox enemyBox;

    @FXML
    private HBox playerBox;

    private CharacterView playerView;
    private List<EnemyView> enemyViews;

    @Override
    public void setCurrentStage(Stage stage) {
        currentStage = stage;

        currentBattle = new Battle();
        currentBattle.setPlayerCharacter(RunData.player);
        currentBattle.setPlayerDeck(new Deck(RunData.deck));

        playerView = new CharacterView(RunData.player);
        playerBox.getChildren().add(playerView);

        enemyViews = new ArrayList<>();
    }

    public void setEnemy(List<Element> elements) {
        EnemyDAO enemyDAO = new EnemyDAO();
        List<Enemy> enemies = enemyDAO.getAll();

        Map<Element, List<Enemy>> enemyMap = new HashMap<>();
        for (Enemy enemy : enemies) {
            if (!enemyMap.containsKey(enemy.getElement())) {
                enemyMap.put(enemy.getElement(), new ArrayList<>());
            }
            enemyMap.get(enemy.getElement()).add(enemy);
        }

        List<Enemy> fighting = new ArrayList<>();
        for (Element element : elements) {
            List<Enemy> enemyList = enemyMap.get(element);
            Enemy enemy = enemyList.get((int) (Math.random() * enemyList.size()));
            fighting.add(enemy);
            EnemyView enemyView = new EnemyView(enemy);
            enemyView.setRotate(180.0);
            enemyBox.getChildren().add(enemyView);
            enemyViews.add(enemyView);
        }

        currentBattle.setEnemies(fighting);
        startBattle();
    }

    private void startBattle() {
        battleHandler = new BattleHandler(currentBattle);
        battleHandler.setSelector(new RandomSelector());
        currentBattle.getPlayerDeck().shuffle();
        currentBattle.drawCard(3);
    }

    public void showHand() {
        Stage handStage = new Stage();
        handStage.initModality(Modality.APPLICATION_MODAL);

        HBox hBox = new HBox(10);
        for (Card c : currentBattle.getPlayerHand().getCards()) {
            CardView cv = new CardView(c);
            cv.setOnMouseClicked(event -> {
                if (canPlay(c)) {
                    // Close the hand stage
                    handStage.close();
                    // Play the card
                    play(c);
                }
            });

            hBox.getChildren().add(cv);
        }

        Scene scene = new Scene(hBox);
        handStage.setScene(scene);
        handStage.showAndWait();
    }

    private boolean canPlay(Card card) {
        if (currentBattle.getPlayerCharacter().getMana() < card.getCost()) {
            return false;
        }
        return true;
    }

    private void play(Card card) {
        currentBattle.getPlayerHand().removeCard(card);

        battleHandler.setCardElement(card.getElement());
        for (String eff : card.getEffects()) {
            battleHandler.setCommand(eff);
            battleHandler.execute();
            refresh();
        }
    }

    private void refresh() {
        playerView.refresh();
        for (EnemyView enemyView : enemyViews) {
            enemyView.refresh();
        }
    }

    public void endTurn() {
        for (Enemy enemy : currentBattle.getEnemies()) {
            currentBattle.attackPlayer(enemy.getAttack(), enemy.getElement());
            refresh();
        }
        if (currentBattle.ended()) {
            if (RunData.player.getHealth() > 0) {
                RunData.currentFloor += 1;
                try {
                    (new SceneLoader()).loadScene("room-picking.fxml", currentStage, 800, 600);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    (new SceneLoader()).loadScene("main-menu.fxml", currentStage, 800, 600);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }

        currentBattle.drawCard(1);
        Character player = currentBattle.getPlayerCharacter();
        player.setMana(player.getMaxMana());

        refresh();
    }

}
