package org.game.model;

public enum Event {

    ACHETER_WOODENCABIN("Acheter une wooden cabin", "1", (new CreatorWoodenCabin()).createBatiment()),
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
