package cardgame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cardgame.model.Element;

import cardgame.model.RunData;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RoomPickController implements Controller {
    private Stage currentStage;

    @FXML
    private HBox room1;

    @FXML
    private HBox room2;

    @FXML
    private HBox room3;

    @Override
    public void setCurrentStage(Stage stage) {
        currentStage = stage;
        List<Element> room1Elements = generateElements();
        List<Element> room2Elements = generateElements();
        List<Element> room3Elements = generateElements();

        addElementsToRoom(room1, room1Elements);
        addElementsToRoom(room2, room2Elements);
        addElementsToRoom(room3, room3Elements);
    }

    private List<Element> generateElements() {
        List<Element> elements = new ArrayList<>();
        int numElements = 0;
        if (RunData.currentFloor % 5 == 0) {
            numElements = 3;
        } else if (RunData.currentFloor % 5 <= 2) {
            numElements = 1;
        } else {
            numElements = 2;
        }

        // Randomly select numElements elements from the list of elements
        // and add them to the list of elements to be displayed
        Random rand = new Random();
        for (int i = 0; i < numElements; i++) {
            // Choose a random element from the Element enum
            Element element = Element.values()[rand.nextInt(Element.values().length)];
            elements.add(element);
        }

        return elements;
    }

    private void addElementsToRoom(HBox room, List<Element> elements) {
        for (Element element : elements) {
            addElementToRoom(room, element);
        }
    }

    private void addElementToRoom(HBox room, Element element) {
        Circle elementCircle = new Circle();
        elementCircle.setRadius(20);

        Color elementColor = null;
        switch (element) {
            case FIRE:
                elementColor = Color.RED;
                break;
            case WATER:
                elementColor = Color.BLUE;
                break;
            case EARTH:
                elementColor = Color.GREEN;
                break;
            case METAL:
                elementColor = Color.BLACK;
                break;
            case WOOD:
                elementColor = Color.GREEN;
                break;
            case NEUTRAL:
                elementColor = Color.GREY;
                break;
        }

        elementCircle.setFill(elementColor);
        room.getChildren().add(elementCircle);
    }

}
