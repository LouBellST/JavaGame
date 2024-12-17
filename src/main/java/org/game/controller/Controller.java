package org.game.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.game.model.commands.*;
import org.game.model.Event;
import org.game.model.Model;
import org.game.view.View;
import org.game.view.ViewListener;

public class Controller implements ViewListener {

    private final Model model;
    private final View view;
    private final BagOfCommands bagOfCommands;

    // class qui implémente tous les éléments intéractifs du jeu (les boutons)
    public Controller(Stage stage) {
        model = new Model();
        view = new View(stage);
        bagOfCommands = new BagOfCommands(model);
        for (Event event : Event.values()) {
            view.addButton(event.getText() + " : " + event.getEventClass().getPrice() + (event.getId().equals("2") ? "" : "   ( Rendement =  " + event.getEventClass().getRendement() + "/habitant  |  Consommation =  " + event.getEventClass().getConsommation() + "/habitant )"),
                    event.getId(), geteventHandlerByEvent(event));
        }
        model.setListener(view);
        model.update();
    }

    @Override
    public void acheterWoodenCabinClicked() {
        bagOfCommands.add(new AcheterWoodenCabinCommand());
    }
    @Override
    public void acheterApartmentBuildingClicked() {
        bagOfCommands.add(new AcheterApartmentBuildingCommand());
    }
    @Override
    public void acheterCementPlantClicked() {
        bagOfCommands.add(new AcheterCementPlantCommand());
    }
    @Override
    public void acheterFarmClicked() {
        bagOfCommands.add(new AcheterFarmCommand());
    }
    @Override
    public void acheterQuarryClicked() {
        bagOfCommands.add(new AcheterQuarryCommand());
    }
    @Override
    public void acheterSteelMillClicked() {
        bagOfCommands.add(new AcheterSteelMillCommand());
    }
    @Override
    public void acheterHouseClicked() {
        bagOfCommands.add(new AcheterHouseCommand());
    }
    @Override
    public void acheterToolFactoryClicked() {
        bagOfCommands.add(new AcheterToolFactoryCommand());
    }
    @Override
    public void acheterLumberMillClicked() {
        bagOfCommands.add(new AcheterLumberMillCommand());
    }

    @Override
    public void acheterHabitantClicked() {
        bagOfCommands.add(new AcheterHabitantCommand());
    }


    /**
     */
    private EventHandler<ActionEvent> geteventHandlerByEvent(Event event) {
        return switch (event) {
            case ACHETER_WOODENCABIN ->
                    a -> acheterWoodenCabinClicked();
            case ACHETER_APARTMENTBUILDING ->
                    a -> acheterApartmentBuildingClicked();
            case ACHETER_CEMENTPLANT ->
                    a -> acheterCementPlantClicked();
            case ACHETER_FARM ->
                    a -> acheterFarmClicked();
            case ACHETER_HOUSE ->
                    a -> acheterHouseClicked();
            case ACHETER_LUMBERMILL ->
                    a -> acheterLumberMillClicked();
            case ACHETER_QUARRY ->
                    a -> acheterQuarryClicked();
            case ACHETER_STEELMILL ->
                    a -> acheterSteelMillClicked();
            case ACHETER_TOOLFACTORY ->
                    a -> acheterToolFactoryClicked();
            case ACHETER_HABITANT ->
                    a -> acheterHabitantClicked();
        };
    }

}
