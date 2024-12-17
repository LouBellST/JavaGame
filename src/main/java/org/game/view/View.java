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
    private final Label dayLabel;
    private final Label specialEventLabel;
    private final Label ressourcesLabel;
    private final Label nombreHabitantsLabel;
    private final Label mapLabel;
    private final Map<String, Button> Buttons = new HashMap<>();
    private final VBox vBox;
    private final ToggleGroup group;


    // classe qui gère toute l'interface graphique du jeu
    public View(Stage stage) {

        stage.setTitle("Game");
        vBox = new VBox();

        vBox.setAlignment(Pos.BASELINE_LEFT);
        vBox.setSpacing(8);

        label = new Label();
        label.setText("Ressources : $200");
        label.setFont(new Font("Arial", 22));
        VBox.setMargin(label, new Insets(0, 0, 16, 32));
        vBox.getChildren().add(label);

        ressourcesLabel = new Label();
        ressourcesLabel.setText("");
        ressourcesLabel.setFont(new Font("Arial", 18));
        VBox.setMargin(ressourcesLabel, new Insets(0, 0, 8, 32));
        vBox.getChildren().add(ressourcesLabel);

        dayLabel = new Label();
        dayLabel.setText("Jour : 1");
        dayLabel.setFont(new Font("Arial", 22));
        VBox.setMargin(dayLabel, new Insets(0, 32, 16, 32));
        vBox.getChildren().add(dayLabel);

        mapLabel = new Label();
        mapLabel.setText("Terrain restant : 200m2");
        mapLabel.setFont(new Font("Arial", 22));
        VBox.setMargin(mapLabel, new Insets(0, 0, 16, 32));
        vBox.getChildren().add(mapLabel);

        specialEventLabel = new Label();
        specialEventLabel.setText("");
        specialEventLabel.setFont(new Font("Arial", 20));
        VBox.setMargin(specialEventLabel, new Insets(0, 16, 16, 32));
        vBox.getChildren().add(specialEventLabel);

        nombreBatimentsLabel = new Label();
        nombreBatimentsLabel.setText("Nombre de batiments : 0");
        nombreBatimentsLabel.setFont(new Font("Arial", 20));
        VBox.setMargin(nombreBatimentsLabel, new Insets(0, 32, 16, 32));
        vBox.getChildren().add(nombreBatimentsLabel);

        placesLabel = new Label();
        placesLabel.setText("Nombre d'hébergement total restantes : 0");
        placesLabel.setFont(new Font("Arial", 20));
        VBox.setMargin(placesLabel, new Insets(0, 32, 16, 32));
        vBox.getChildren().add(placesLabel);

        nombreHabitantsLabel = new Label();
        nombreHabitantsLabel.setText("Ressources : $200");
        nombreHabitantsLabel.setFont(new Font("Arial", 20));
        VBox.setMargin(nombreHabitantsLabel, new Insets(0, 0, 16, 32));
        vBox.getChildren().add(nombreHabitantsLabel);

        group = new ToggleGroup();

        Scene scene = new Scene(vBox, 1000, 800);
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
        VBox.setMargin(radioButton, new Insets(8, 32, 0, 32));
        vBox.getChildren().add(radioButton);
    }

    public void setLabel(String text) {
        label.setText(text);
    }


    @Override
    public void update(GameManager gameManager) {
        label.setText("Ressources : ");
        ressourcesLabel.setText(gameManager.displayRessources());
        dayLabel.setText("Jour : " + gameManager.getDay());
        mapLabel.setText("Terrain restant : "+gameManager.getMap().getFreeSize()+"m2");
        if (gameManager.getDay() % 30 == 0) {
            specialEventLabel.setText("Bonus mensuel : + 200 Food !");
        } else {specialEventLabel.setText("");}
        List<Event> availableEvents = gameManager.getAvailableEvents();
        for (Event event : Event.values()) {
            Buttons.get(event.getId()).setDisable(true);
        }
        for (Event event : availableEvents) {
            Buttons.get(event.getId()).setDisable(false);
        }
        int placesRestantes = 0;
        int placesTotales = 0;
        List<Batiment> allBats = gameManager.getAllBatiments();
        for (Batiment b : allBats){
            placesTotales += b.getPlacesTotales();
            placesRestantes += b.getPlacesRestantes();
        }
        nombreBatimentsLabel.setText("Nombre de batiments : " + allBats.size());
        placesLabel.setText("Nombre d'hébergement total restant : " + placesRestantes);
        nombreHabitantsLabel.setText("Nombre d'habitants : " + (placesTotales - placesRestantes));

    }

}
