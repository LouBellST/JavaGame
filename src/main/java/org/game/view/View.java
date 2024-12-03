package org.game.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.game.model.Batiment;
import org.game.model.Event;
import org.game.model.GameManager;
import org.game.model.ModelListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class View implements ModelListener {

    private final Label label;
    private final Label nombreBatimentsLabel;
    private final Label placesLabel;
    private final Map<String, Button> Buttons = new HashMap<>();
    private final VBox vBox;
    private final ToggleGroup group;

    public View(Stage stage) {

        stage.setTitle("Game");
        vBox = new VBox();

        vBox.setAlignment(Pos.BASELINE_LEFT);
        vBox.setSpacing(8);

        label = new Label();
        label.setText("Ressources : 200\n");
        label.setFont(new Font("Arial", 24));
        VBox.setMargin(label, new Insets(0, 0, 16, 32));
        vBox.getChildren().add(label);

        nombreBatimentsLabel = new Label();
        nombreBatimentsLabel.setText("Nombre de batiments : 0");
        nombreBatimentsLabel.setFont(new Font("Arial", 24));
        VBox.setMargin(nombreBatimentsLabel, new Insets(0, 0, 16, 32));
        vBox.getChildren().add(nombreBatimentsLabel);

        placesLabel = new Label();
        placesLabel.setText("Nombre de places total restant : 0");
        placesLabel.setFont(new Font("Arial", 24));
        VBox.setMargin(placesLabel, new Insets(0, 0, 16, 32));
        vBox.getChildren().add(placesLabel);

        group = new ToggleGroup();

        Scene scene = new Scene(vBox, 700, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void addButton(String title, String id, EventHandler<ActionEvent> eventHandler) {
        Button radioButton = new Button();
        radioButton.setText(title);
        radioButton.setId(id);
        Buttons.put(id, radioButton);
        radioButton.setMaxWidth(Double.MAX_VALUE);
        radioButton.setMaxHeight(Double.MAX_VALUE);
        radioButton.setOnAction(eventHandler);
        VBox.setMargin(radioButton, new Insets(0, 0, 0, 32));
        vBox.getChildren().add(radioButton);
    }

    public void setLabel(String text) {
        label.setText(text);
    }


    @Override
    public void update(GameManager gameManager) {
        label.setText("Ressources : " + gameManager.getRessources());
        List<Event> availableEvents = gameManager.getAvailableEvents();
        for (Event event : Event.values()) {
            Buttons.get(event.getId()).setDisable(true);
        }
        for (Event event : availableEvents) {
            Buttons.get(event.getId()).setDisable(false);
        }
        int placesRestantes = 0;
        List<Batiment> allBats = gameManager.getAllBatiments();
        for (Batiment b : allBats){
            placesRestantes += b.getPlacesRestantes();
        }
        nombreBatimentsLabel.setText("Nombre de batiments : " + allBats.size());
        placesLabel.setText("Nombre de places total restant : " + placesRestantes);

    }

}
