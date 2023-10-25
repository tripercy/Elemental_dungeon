package cardgame.elements;

import cardgame.model.Card;
import cardgame.model.Element;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedList;

public class CardModifiable extends CardView {

    public CardModifiable() {
        super();

        addModifer();
    }

    public CardModifiable(Card card) {
        super(card);

        addModifer();
    }

    private void addModifer() {
        manaCircle.setOnMouseClicked(event -> {
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);

            popupStage.setTitle("Change mana cost and element");

            VBox popupRoot = new VBox();
            TextField manaField = new TextField();
            ComboBox<Element> elementComboBox = new ComboBox<>();
            elementComboBox.getItems().addAll(Element.values());
            elementComboBox.setValue(Element.NEUTRAL);
            Button saveButton = new Button("Save");

            popupRoot.getChildren().addAll(
                    new Label("Mana cost"),
                    manaField,
                    new Label("Element"),
                    elementComboBox,
                    saveButton);

            saveButton.setOnAction(event1 -> {
                String mana = manaField.getText();
                Element element = elementComboBox.getValue();

                setElement(element);

                try {
                    int manaCost = Integer.parseInt(mana);
                    setManaCost(manaCost);
                } catch (Exception e) {

                }

                popupStage.close();
            });

            Scene popupScene = new Scene(popupRoot, 300, 200);
            popupStage.setScene(popupScene);

            popupStage.showAndWait();
        });

        nameLabel.setOnMouseClicked(event -> {
            TextInputDialog dialog = new TextInputDialog(nameLabel.getText());
            dialog.setTitle("Change name");

            dialog.showAndWait().ifPresent(name -> {
                setName(name);
            });
        });

        bottomBox.setOnMouseClicked(event -> {
            // Input dialog with 3 text fields
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);

            popupStage.setTitle("Change effects");

            VBox popupRoot = new VBox();
            TextField eff1 = new TextField();
            TextField eff2 = new TextField();
            TextField eff3 = new TextField();

            // Set the text fields to the current effects
            String[] effects = descriptionText.getText().split("\n");
            if (effects.length >= 1)
                eff1.setText(effects[0]);
            if (effects.length >= 2)
                eff2.setText(effects[1]);
            if (effects.length >= 3)
                eff3.setText(effects[2]);

            Button saveButton = new Button("Save");

            popupRoot.getChildren().addAll(
                    new Label("Effect 1"),
                    eff1,
                    new Label("Effect 2"),
                    eff2,
                    new Label("Effect 3"),
                    eff3,
                    saveButton);

            saveButton.setOnAction(event1 -> {
                LinkedList<String> effectsList = new LinkedList<>();
                if (!eff1.getText().isEmpty())
                    effectsList.add(eff1.getText());
                if (!eff2.getText().isEmpty())
                    effectsList.add(eff2.getText());
                if (!eff3.getText().isEmpty())
                    effectsList.add(eff3.getText());

                setDescription(effectsList);

                popupStage.close();
            });

            Scene popupScene = new Scene(popupRoot, 300, 200);
            popupStage.setScene(popupScene);

            popupStage.showAndWait();
        });
    }
}
