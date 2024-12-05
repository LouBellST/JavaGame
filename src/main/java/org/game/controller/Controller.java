package org.game.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.game.model.AcheterBatimentCommand;
import org.game.model.AcheterHabitantCommand;
import org.game.model.Event;
import org.game.model.Model;
import org.game.view.View;
import org.game.view.ViewListener;

public class Controller implements ViewListener {

    private final Model model;
    private final View view;
    private final BagOfCommands bagOfCommands;

    public Controller(Stage stage) {
        model = new Model();
        view = new View(stage);
        bagOfCommands = new BagOfCommands(model);
        for (Event event : Event.values()) {
            view.addButton(event.getText() + " : $" + event.getEventClass().getPrice() + (event.getId().equals("2") ? "" : "   ( Rendement =  $" + event.getEventClass().getRendement() + "/jour * habitants )"),
                    event.getId(), geteventHandlerByEvent(event));
        }
        model.setListener(view);
        model.update();
    }

    @Override
    public void acheterBatimentClicked() {
        System.out.println("Le bouton 'Acheter Batiment' a été cliqué.");
        bagOfCommands.add(new AcheterBatimentCommand());
    }

    @Override
    public void acheterHabitantClicked() {
        System.out.println("Le bouton 'Acheter Habitant' a été cliqué.");
        bagOfCommands.add(new AcheterHabitantCommand());
    }


    /**
     */
    private EventHandler<ActionEvent> geteventHandlerByEvent(Event event) {
        return switch (event) {
            case ACHETER_BATIMENT ->
                    a -> acheterBatimentClicked();
            case ACHETER_HABITANT ->
                    a -> acheterHabitantClicked();
        };
    }

}
