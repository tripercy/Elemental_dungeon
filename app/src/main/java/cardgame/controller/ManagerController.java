package cardgame.controller;

import cardgame.dao.CardDAO;
import cardgame.dao.CharacterDAO;
import cardgame.elements.CardModifiable;
import cardgame.model.Card;
import cardgame.model.Character;
import cardgame.model.Element;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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

    @Override
    public void setCurrentStage(Stage stage) {
        this.currentStage = stage;

        cardDAO = new CardDAO();
        characterDAO = new CharacterDAO();

        items = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");

        cardModifier();
    }

    public void back() {
        System.out.println("Back pressed");
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

        itemList.setItems(items);

        CardModifiable cm = new CardModifiable();

        itemInfo.getChildren().clear();
        itemInfo.getChildren().add(cm);

        AnchorPane.setTopAnchor(cm, 50.0);
        AnchorPane.setLeftAnchor(cm, 225.0);

        addBtn.setOnAction(event -> {
            Card card = cm.getCard();
            if (card != null && !card.getName().equals("-") && card.getCost() >= 0){
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

    public void characterModifier() {
        List<Character> characters = characterDAO.getAll();

        items.clear();
        // Add all the characters' names to the items
        for (Character character : characters) {
            items.add(character.getName());
        }
    }

    public void enemyModifier() {
        System.out.println("Enemy pressed");
    }

}
