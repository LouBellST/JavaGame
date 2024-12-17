package org.game.model;

import org.game.model.creators.*;

// ENUM permettant de regrouper toutes les options qui s'offre au joueur en terme d'achats
public enum Event {

    ACHETER_WOODENCABIN("Acheter une wooden cabin", "1", (new CreatorWoodenCabin()).createBatiment()),
    ACHETER_APARTMENTBUILDING("Acheter un appartement", "3", (new CreatorApartmentBuilding()).createBatiment()),
    ACHETER_HOUSE("Acheter une maison", "4", (new CreatorHouse()).createBatiment()),
    ACHETER_FARM("Acheter une ferme", "5", (new CreatorFarm()).createBatiment()),
    ACHETER_LUMBERMILL("Acheter une maison de bucheron", "6", (new CreatorLumberMill()).createBatiment()),
    ACHETER_STEELMILL("Acheter une usine à fer", "7", (new CreatorSteelMill()).createBatiment()),
    ACHETER_QUARRY("Acheter une carrière", "8", (new CreatorQuarry()).createBatiment()),
    ACHETER_CEMENTPLANT("Acheter une usine à ciment", "9", (new CreatorCementPlant()).createBatiment()),
    ACHETER_TOOLFACTORY("Acheter une production d'outils", "10", (new CreatorToolFactory()).createBatiment()),
    ACHETER_HABITANT("Acheter un habitant", "2", new Habitant());


    private final String text;
    private final String id;
    private final Achetable eventClass;

    Event(String text, String id, Achetable eventClass) {
        this.text = text;
        this.id = id;
        this.eventClass = eventClass;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }
    public Achetable getEventClass() {
        return eventClass;
    }

    public static Event eventById(String id) {
        for (Event event : Event.values()) {
            if (event.id.equals(id)) {
                return event;
            }
        }
        throw new IllegalArgumentException("No event found with ID: " + id);
    }

}
