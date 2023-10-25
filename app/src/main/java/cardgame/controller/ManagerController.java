package cardgame.controller;

import cardgame.dao.CardDAO;
import cardgame.dao.CharacterDAO;
import cardgame.dao.EnemyDAO;
import cardgame.elements.CardModifiable;
import cardgame.elements.CharacterModifiable;
import cardgame.elements.EnemyModifiable;
import cardgame.model.Card;
import cardgame.model.Character;
import cardgame.model.Element;
import cardgame.utils.SceneLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class ManagerController implements Controller {
    Stage currentStage;

    @FXML
    private BorderPane root;

    @FXML
    private ListView<String> itemList;

    @FXML
    private AnchorPane itemInfo;

    @FXML
    private Button addBtn;
    @FXML
    private Button modBtn;
    @FXML
    private Button delBtn;

    private ObservableList<String> items;

    CardDAO cardDAO;
    CharacterDAO characterDAO;
    EnemyDAO enemyDAO;

    @Override
    public void setCurrentStage(Stage stage) {
        this.currentStage = stage;

        cardDAO = new CardDAO();
        characterDAO = new CharacterDAO();
        enemyDAO = new EnemyDAO();

        items = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");

        cardModifier();
    }

    public void back() {
        try {
            (new SceneLoader()).loadScene("main-menu.fxml", currentStage, 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshCardList() {
        List<Card> cards = cardDAO.getAll();

        items.clear();
        // Add all the cards' names to the items
        for (Card card : cards) {
            items.add(card.getName());
        }

        itemList.setItems(items);
    }

    public void cardModifier() {
        refreshCardList();

        CardModifiable cm = new CardModifiable();

        itemInfo.getChildren().clear();
        itemInfo.getChildren().add(cm);

        AnchorPane.setTopAnchor(cm, 50.0);
        AnchorPane.setLeftAnchor(cm, 225.0);

        addBtn.setOnAction(event -> {
            Card card = cm.getCard();
            if (card != null && !card.getName().equals("-") && card.getCost() >= 0) {
                cardDAO.save(card);
                refreshCardList();
            } else {
                System.out.println("Invalid card");
            }
        });

        delBtn.setOnAction(event -> {
            String name = itemList.getSelectionModel().getSelectedItem();
            if (name != null) {
                cardDAO.delete(name);
                refreshCardList();
                cm.setCard(new Card("-", -1, Element.NEUTRAL, new LinkedList<String>()));
            }
        });

        modBtn.setOnAction(event -> {
            String name = itemList.getSelectionModel().getSelectedItem();
            if (name != null) {
                Card card = cm.getCard();
                cardDAO.update(name, card);
            }
        });

        itemList.setOnMouseClicked(event -> {
            String name = itemList.getSelectionModel().getSelectedItem();
            if (name != null) {
                Card card = cardDAO.get(name).get();
                cm.setCard(card);
            }
        });
    }

    private void refreshCharacterList() {
        List<Character> characters = characterDAO.getAll();

        items.clear();
        // Add all the characters' names to the items
        for (Character character : characters) {
            items.add(character.getName());
        }

        itemList.setItems(items);
    }

    public void characterModifier() {
        refreshCharacterList();

        CharacterModifiable cm = new CharacterModifiable();

        itemInfo.getChildren().clear();
        itemInfo.getChildren().add(cm);

        AnchorPane.setTopAnchor(cm, 50.0);
        AnchorPane.setLeftAnchor(cm, 225.0);

        addBtn.setOnAction(event -> {
            Character character = cm.getCharacter();
            if (character != null && !character.getName().equals("-") && character.getMaxMana() >= 0) {
                characterDAO.save(character);
                refreshCharacterList();
            } else {
                System.out.println("Invalid character");
            }
        });

        delBtn.setOnAction(event -> {
            String name = itemList.getSelectionModel().getSelectedItem();
            if (name != null) {
                characterDAO.delete(name);
                refreshCharacterList();
                cm.setCharacter(new Character("-", -1, -1, Element.NEUTRAL));
            }
        });

        modBtn.setOnAction(event -> {
            String name = itemList.getSelectionModel().getSelectedItem();
            if (name != null) {
                Character character = cm.getCharacter();
                characterDAO.update(name, character);
            }
        });

        itemList.setOnMouseClicked(event -> {
            String name = itemList.getSelectionModel().getSelectedItem();
            if (name != null) {
                Character character = characterDAO.get(name).get();
                cm.setCharacter(character);
            }
        });
    }

    private void refreshEnemyList() {
        List<cardgame.model.Enemy> enemies = enemyDAO.getAll();

        items.clear();
        // Add all the enemies' names to the items
        for (cardgame.model.Enemy enemy : enemies) {
            items.add(enemy.getName());
        }

        itemList.setItems(items);
    }

    public void enemyModifier() {
        refreshEnemyList();

        EnemyModifiable em = new EnemyModifiable();

        itemInfo.getChildren().clear();
        itemInfo.getChildren().add(em);

        AnchorPane.setTopAnchor(em, 50.0);
        AnchorPane.setLeftAnchor(em, 225.0);

        addBtn.setOnAction(event -> {
            cardgame.model.Enemy enemy = em.getEnemy();
            if (enemy != null && !enemy.getName().equals("-") && enemy.getMaxHp() > 0) {
                enemyDAO.save(enemy);
                refreshEnemyList();
            } else {
                System.out.println("Invalid enemy");
            }
        });

        delBtn.setOnAction(event -> {
            String name = itemList.getSelectionModel().getSelectedItem();
            if (name != null) {
                enemyDAO.delete(name);
                refreshEnemyList();
                em.setEnemy(new cardgame.model.Enemy("-", -1, -1, Element.NEUTRAL));
            }
        });

        modBtn.setOnAction(event -> {
            String name = itemList.getSelectionModel().getSelectedItem();
            if (name != null) {
                cardgame.model.Enemy enemy = em.getEnemy();
                enemyDAO.update(name, enemy);
            }
        });

        itemList.setOnMouseClicked(event -> {
            String name = itemList.getSelectionModel().getSelectedItem();
            if (name != null) {
                cardgame.model.Enemy enemy = enemyDAO.get(name).get();
                em.setEnemy(enemy);
            }
        });
    }

}
