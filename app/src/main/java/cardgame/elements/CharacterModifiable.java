package cardgame.elements;

import cardgame.model.Character;
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

public class CharacterModifiable extends CharacterView {
    public CharacterModifiable() {
        super();

        addModifer();
    }

    public CharacterModifiable(Character character) {
        super(character);

        addModifer();
    }

    private void addModifer() {
        manaCircle.setOnMouseClicked(event -> {
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);

            popupStage.setTitle("Change max mana and element");

            VBox popupRoot = new VBox();
            TextField manaField = new TextField();
            ComboBox<Element> elementComboBox = new ComboBox<>();
            elementComboBox.getItems().addAll(Element.values());
            elementComboBox.setValue(Element.NEUTRAL);
            Button saveButton = new Button("Save");

            popupRoot.getChildren().addAll(
                    new Label("Max mana"),
                    manaField,
                    new Label("Element"),
                    elementComboBox,
                    saveButton);

            saveButton.setOnAction(event1 -> {
                String mana = manaField.getText();
                Element element = elementComboBox.getValue();

                setElement(element);

                try {
                    int maxMana = Integer.parseInt(mana);
                    setMaxMana(maxMana);
                    setMana(maxMana);
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

        hpLabel.setOnMouseClicked(event -> {
            TextInputDialog dialog = new TextInputDialog(hpLabel.getText());
            dialog.setTitle("Change max health");

            dialog.showAndWait().ifPresent(hp -> {
                try {
                    int maxHp = Integer.parseInt(hp);
                    setMaxHp(maxHp);
                    setHp(maxHp);
                } catch (Exception e) {

                }
            });
        });
    }
}
