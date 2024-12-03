package org.game.model;

public enum Event {

    ACHETER_BATIMENT("Acheter un batiment", "1"),
    ACHETER_HABITANT("Acheter un habitant", "2");


    private final String text;
    private final String id;

    Event(String text, String id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
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
