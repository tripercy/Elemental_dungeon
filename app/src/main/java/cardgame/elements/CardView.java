package cardgame.elements;

import cardgame.model.Card;
import cardgame.model.Element;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.Queue;

public class CardView extends VBox{
    private final double whRatio = 0.7;
    private final double imageRatio = 0.7;

    private Card card;
    private ImageView imageView;

    protected HBox topBox;
    protected HBox bottomBox;

    protected StackPane manaCircle;
    protected Label nameLabel;

    protected ImageView cardImage;

    protected Text descriptionText;

    public CardView() {
        this.card = new Card("-", -1, Element.NEUTRAL, new LinkedList<String>());

        // Create a circle with a red fill
        Circle circle = new Circle(10);
        circle.setFill(Color.GRAY);

        Label manaLabel = new Label("-");
        manaLabel.setFont(new Font(14));

        manaCircle = new StackPane();
        manaCircle.getChildren().addAll(circle, manaLabel);
        
        // Create a label with the card's name
        nameLabel = new Label("---");
        nameLabel.setFont(new Font(18));
        
        Image image = new WritableImage(100, 100);

        // Fill the image with grey
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
               ((WritableImage) image).getPixelWriter().setColor(x, y, Color.GREY);
            }
        }

        imageView = new ImageView(image);
        
        descriptionText = new Text("---");
        descriptionText.getStyleClass().add("norm-text");
        descriptionText.setLineSpacing(5);
        
        // Create the top HBox for the circle and label
        topBox = new HBox(10);
        topBox.getChildren().addAll(manaCircle, nameLabel);
        
        // Create the bottom HBox for the card description
        bottomBox = new HBox();
        bottomBox.getChildren().add(descriptionText);
        
        // Set alignment for the top and bottom HBox
        topBox.setAlignment(Pos.CENTER_LEFT);
        bottomBox.setAlignment(Pos.CENTER);
        
        // Set spacing for the VBox
        this.setSpacing(10);
        
        // Add all components to the Card VBox
        this.getChildren().addAll(topBox, imageView, bottomBox);
        
        // Set alignment for the Card VBox
        this.setAlignment(Pos.TOP_CENTER);

        String cardStyle = "-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10;";
        String elementStyle = "-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 5;";

        this.setStyle(cardStyle);
        topBox.setStyle(elementStyle);
        bottomBox.setStyle(elementStyle);

        this.setPrefSize(200, 300);
        bottomBox.setPrefHeight(80);

        this.getStyleClass().add("card-labels");
        bottomBox.getStyleClass().add("card-effect");
        this.getStyleClass().add("card");
    }

    public CardView(Card card) {
        // Call default constructor
        this();

        setCard(card);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card){
        setElement(card.getElement());

        setElement(card.getElement());

        setManaCost(card.getCost());

        this.nameLabel.setText(card.getName());

        setDescription(card.getEffects());
    }

    @Override
    public void setPrefSize(double width, double height) {
        if (width < 200) return;
        this.setPrefWidth(width);
        this.setPrefHeight(width / whRatio);

        // Resize the image
        imageView.setFitWidth(width * imageRatio);
        imageView.setFitHeight(width * imageRatio);
    }

    protected void setManaCost(int manaCost) {
        Label manaLabel = (Label) manaCircle.getChildren().get(1);
        manaLabel.setText(Integer.toString(manaCost));

        card.setCost(manaCost);
    }

    protected void setElement(Element element){
        Color color = Color.GREY;
        Color textColor = Color.BLACK;
        switch (element){
            case EARTH:
                color = Color.BROWN;
                textColor = Color.WHITE;
                break;
            case FIRE:
                color = Color.RED;
                break;
            case METAL:
                color = Color.BLACK;
                textColor = Color.WHITE;
                break;
            case NEUTRAL:
                color = Color.GREY;
                break;
            case WATER:
                color = Color.BLUE;
                textColor = Color.WHITE;
                break;
            case WOOD:
                color = Color.GREEN;
                break;
            default:
                break;
        }
        Circle circle = (Circle) manaCircle.getChildren().get(0);
        circle.setFill(color);

        Label manaLabel = (Label) manaCircle.getChildren().get(1);
        manaLabel.setTextFill(textColor);

        card.setElement(element);
    }

    protected void setName(String name) {
        nameLabel.setText(name);
        card.setName(name);
    }

    protected void setDescription(Queue<String> description) {
        descriptionText.setText(String.join("\n", description));
        card.setEffects(description);
    }
}
