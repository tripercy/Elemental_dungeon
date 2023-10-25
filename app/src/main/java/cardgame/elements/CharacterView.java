package cardgame.elements;

import cardgame.model.Character;
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

public class CharacterView extends VBox {
    private final double whRatio = 0.7;
    private final double imageRatio = 0.7;

    private Character character;

    ImageView imageView;

    HBox topBox;
    HBox bottomBox;

    StackPane manaCircle;
    Label nameLabel;

    Label hpLabel;

    public CharacterView() {
        this.character = new Character("-", -1, -1, Element.NEUTRAL);

        // Create a circle 
        Circle circle = new Circle(10);
        circle.setFill(Color.GRAY);

        Label manaLabel = new Label("-");
        manaLabel.setFont(new Font(14));

        manaCircle = new StackPane();
        manaCircle.getChildren().addAll(circle, manaLabel);

        // Create a label with the card's name
        nameLabel = new Label(character.getName());
        nameLabel.setFont(new Font(18));

        // Create HP Label
        hpLabel = new Label("0/0");
        hpLabel.setFont(new Font(14));
        hpLabel.setTextFill(Color.RED);

        Image image = new WritableImage(100, 100);

        // Fill the image with grey
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
               ((WritableImage) image).getPixelWriter().setColor(x, y, Color.GREY);
            }
        }

        imageView = new ImageView(image);

        // Create a box for the top
        topBox = new HBox(10);
        topBox.getChildren().addAll(manaCircle, nameLabel);

        topBox.setAlignment(Pos.CENTER_LEFT);

        // Create a box for the bottom
        bottomBox = new HBox();
        bottomBox.getChildren().addAll(hpLabel);

        bottomBox.setAlignment(Pos.CENTER);

        this.getChildren().addAll(topBox, imageView, bottomBox);

        // Set spacing for the VBox
        this.setSpacing(10);

        this.setAlignment(Pos.TOP_CENTER);

        String cardStyle = "-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10;";
        String elementStyle = "-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 5;";

        this.setStyle(cardStyle);
        topBox.setStyle(elementStyle);
        bottomBox.setStyle(elementStyle);

        this.setPrefSize(200, 300);
        bottomBox.setPrefHeight(80);
    }

    public CharacterView(Character character){
        this();
        setCharacter(character);
    }

    public Character getCharacter(){
        return character;
    }

    public void setCharacter(Character character){
        this.character = character;

        setName(character.getName());
        setHp(character.getHealth());
        setMana(character.getMana());
        setElement(character.getElement());
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

    protected void setMana(int mana) {
        Label manaLabel = (Label) manaCircle.getChildren().get(1);
        manaLabel.setText(Integer.toString(mana));

        character.setMana(mana);
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

        character.setElement(element);
    }

    protected void setName(String name) {
        nameLabel.setText(name);
        character.setName(name);
    }

    protected void setHp(int hp) {
        hpLabel.setText(hp + "/" + character.getMaxHealth());
        character.setHealth(hp);;
    }

    protected void setMaxHp(int maxHp) {
        hpLabel.setText(character.getHealth() + "/" + maxHp);
        character.setMaxHealth(maxHp);
    }

    protected void setMaxMana(int maxMana) {
        character.setMaxMana(maxMana);
    }
}
