package org.game.model;


import javafx.concurrent.Task;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameManager {

    private HashMap<String, Creator> creators;
    private List<Batiment> allBatiments;
    private List<Habitant> currentBuildings;
    private List<Ressource> ressources;
    private int day;
    private Task<Void> dayTask;
    private Thread dayThread;  // Thread pour tenir compte des jours


    public GameManager(){
        creators = new HashMap<>();
        creators.put("WoodenCabin", new CreatorWoodenCabin());

        allBatiments = new ArrayList<>();
        ressources = new ArrayList<>();
        ressources.add(new Ressource("Food", 50, 5000));
        ressources.add(new Ressource("Wood", 50, 5000));
        ressources.add(new Ressource("Iron", 0, 5000));
        ressources.add(new Ressource("Stone", 50, 5000));
        ressources.add(new Ressource("Steel", 0, 5000));
        ressources.add(new Ressource("Coal", 0, 5000));
        ressources.add(new Ressource("Cement", 0, 5000));
        ressources.add(new Ressource("Lumber", 0, 5000));
        ressources.add(new Ressource("Tools", 0, 5000));

        day = 1;
        startDayTask();
    }
    public void handle(Model model) {
        switch (model.getNextEvent()) {
            case ACHETER_WOODENCABIN -> {
                WoodenCabin newW = (WoodenCabin) creators.get("WoodenCabin").createBatiment();
                newW.construire(this, model);
                this.allBatiments.add(newW);
            }
            case ACHETER_HABITANT -> {
                Habitant newH = new Habitant();
                newH.construire(this, model);
            }
            default -> {
            }
        }
    }

    private void startDayTask() {
        GameManager gm = this;
        dayTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    Thread.sleep(3500);
                    day += 1;
                    allBatiments.forEach(batiment -> batiment.produire_consommerRessources(gm));
                    if(day%30==0){ressources.get(0).setQuantity(ressources.get(0).getQuantity() + 200);}
                }
                return null;
            }
        };

        dayThread = new Thread(dayTask);
        dayThread.setDaemon(true); // Le thread s'arrête lorsque l'application se termine
        dayThread.start();
    }


    public List<Ressource> getRessources() {
        return ressources;
    }
    public void setRessources(String name, int newAmount) {
        ressources.forEach(r -> {
            if (r.getName().equals(name)) {
                r.setQuantity(newAmount);
            }});
    }

    public List<Batiment> getAllBatiments(){return allBatiments;}
    public int getDay(){return day;}

    public List<Event> getAvailableEvents() {
        List<Event> availableEvents = new ArrayList<>();
        int placesRestantes = 0;
        for (Batiment b : allBatiments){
            placesRestantes += b.getPlacesRestantes();
        }
        for (Event event : Event.values()){
            boolean canBuy = true;
            ressources.forEach(r -> {
                if(r.getName().equals(event.getEventClass().getPrice().getKey()) && r.getQuantity() >= event.getEventClass().getPrice().getValue())
            });
            if(ressources >= event.getEventClass().getPrice()){
                if(event == Event.ACHETER_HABITANT && placesRestantes == 0 ){
                    continue;
                }else {
                    availableEvents.add(event);
                }
            }
        }
        return (availableEvents);
    }
}
