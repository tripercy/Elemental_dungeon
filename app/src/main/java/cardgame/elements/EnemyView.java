package cardgame.elements;

import cardgame.model.Element;
import cardgame.model.Enemy;
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

public class EnemyView extends VBox {
    private final double whRatio = 0.7;
    private final double imageRatio = 0.7;

    private Enemy enemy;

    protected ImageView imageView;

    protected HBox topBox;
    protected HBox bottomBox;

    protected StackPane atkCircle;
    protected Label nameLabel;

    protected Label hpLabel;

    public EnemyView() {
        this.enemy = new Enemy("-", -1, -1, Element.NEUTRAL);

        // Create a circle
        Circle circle = new Circle(10);
        circle.setFill(Color.GRAY);

        Label atkLabel = new Label("-");
        atkLabel.setFont(new Font(14));

        atkCircle = new StackPane();
        atkCircle.getChildren().addAll(circle, atkLabel);

        // Create a label with the card's name
        nameLabel = new Label(enemy.getName());
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
        topBox.getChildren().addAll(atkCircle, nameLabel);

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

        this.getStyleClass().add("card-labels");
        this.getStyleClass().add("card");
    }

    public EnemyView(Enemy enemy){
        this();

        setEnemy(enemy);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;

        setElement(enemy.getElement());
        setName(enemy.getName());
        setHp(enemy.getHp());
        setMaxHp(enemy.getMaxHp());
        setAttack(enemy.getAttack());
    }

    @Override
    public void setPrefSize(double width, double height) {
        if (width < 200)
            return;
        this.setPrefWidth(width);
        this.setPrefHeight(width / whRatio);

        // Resize the image
        imageView.setFitWidth(width * imageRatio);
        imageView.setFitHeight(width * imageRatio);
    }

    protected void setElement(Element element) {
        Color color = Color.GREY;
        Color textColor = Color.BLACK;
        switch (element) {
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
        Circle circle = (Circle) atkCircle.getChildren().get(0);
        circle.setFill(color);

        Label manaLabel = (Label) atkCircle.getChildren().get(1);
        manaLabel.setTextFill(textColor);

        enemy.setElement(element);
    }

    protected void setName(String name) {
        nameLabel.setText(name);
        enemy.setName(name);
    }

    protected void setHp(int hp) {
        hpLabel.setText(hp + "/" + enemy.getMaxHp());
        enemy.setHp(hp);
    }

    protected void setMaxHp(int maxHp) {
        hpLabel.setText(enemy.getHp() + "/" + maxHp);
        enemy.setMaxHp(maxHp);
        ;
    }

    protected void setAttack(int attack) {
        Label atkLabel = (Label) atkCircle.getChildren().get(1);
        atkLabel.setText(Integer.toString(attack));

        enemy.setAttack(attack);
    }

    public void refresh() {
        setEnemy(enemy);
    }
}
