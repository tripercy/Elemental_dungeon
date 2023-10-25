package cardgame.controller;

import java.util.List;
import java.util.Optional;

import cardgame.dao.CardDAO;
import cardgame.elements.CardView;
import cardgame.model.Card;
import cardgame.model.RunData;
import cardgame.model.cardContainer.Deck;
import cardgame.utils.SceneLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DeckBuildController implements Controller {
    private Stage currentStage;
    private Deck currentDeck;

    private CardDAO cardDAO = new CardDAO();
    private List<Card> availableCards;

    @FXML
    private ListView<String> cards;

    @FXML
    private ListView<String> deck;

    @FXML
    private AnchorPane card_info;

    @FXML
    private HBox bottomBox;

    private Button goButton;
    private ObservableList<String> cardsList;
    private ObservableList<String> deckList;

    @Override
    public void setCurrentStage(Stage stage) {
        currentStage = stage;

        currentDeck = new Deck();
        cardDAO = new CardDAO();

        availableCards = cardDAO.getAll();

        cardsList = cards.getItems();
        deckList = deck.getItems();

        goButton = new Button("Start");
        goButton.setId("go-button");
        goButton.getStyleClass().add("go-btn");

        goButton.setOnAction(event -> {
            startRun();
        });

        for (Card c : availableCards) {
            cardsList.add(c.getName());
        }

        cards.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showInfo(newValue);
        });

        deck.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showInfo(newValue);
        });
    }

    public void back() {
        try {
            (new SceneLoader()).loadScene("char-select.fxml", currentStage, 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add() {
        String selectedCard = cards.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            Optional<Card> oc = cardDAO.get(selectedCard);
            if (oc.isPresent() && currentDeck.getSize() < currentDeck.getCapacity())
                currentDeck.addCard(oc.get());
            refresh();
        }

        if (currentDeck.getSize() == currentDeck.getCapacity()) {
            bottomBox.getChildren().add(goButton);
        }
    }

    public void refresh() {
        deckList.clear();
        for (Card c : currentDeck.getCards()) {
            deckList.add(c.getName());
        }

        deck.setItems(deckList);
    }

    public void showInfo(String cardName) {
        Optional<Card> oc = cardDAO.get(cardName);
        if (!oc.isPresent())
            return;
        Card card = oc.get();
        CardView cardView = new CardView(card);

        card_info.getChildren().clear();
        card_info.getChildren().add(cardView);

        AnchorPane.setTopAnchor(cardView, 20.0);
        AnchorPane.setLeftAnchor(cardView, 20.0);
    }

    public void remove() {
        String selectedCard = deck.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            Optional<Card> oc = cardDAO.get(selectedCard);
            if (oc.isPresent()) {
                currentDeck.removeCard(oc.get());
            }
            refresh();
        }

        if (currentDeck.getSize() < currentDeck.getCapacity()) {
            bottomBox.getChildren().remove(goButton);
        }
    }

    private void startRun(){
        RunData.deck = currentDeck;

        try {
            (new SceneLoader()).loadScene("run.fxml", currentStage, 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
