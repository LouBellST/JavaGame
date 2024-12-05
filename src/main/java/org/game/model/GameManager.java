package org.game.model;


import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;


public class GameManager {

    private List<Achetable> currentActions;
    private List<Batiment> allBatiments;
    private List<Habitant> currentBuildings;
    private int ressources;
    private int day;
    private Task<Void> dayTask;
    private Thread dayThread;  // Thread pour tenir compte des jours


    public GameManager(){
        currentActions = new ArrayList<>();
        allBatiments = new ArrayList<>();
        ressources = 200;
        day = 1;
        startDayTask();
    }
    public void handle(Model model) {
        switch (model.getNextEvent()) {
            case ACHETER_BATIMENT -> {
                Batiment newB = new Batiment();
                newB.construire(this, model);
                this.allBatiments.add(newB);
                this.currentActions.add(newB);
            }
            case ACHETER_HABITANT -> {
                Habitant newH = new Habitant();
                newH.construire(this, model);
                this.currentActions.add(newH);
            }
            default -> {
            }
        }
    }

    private void startDayTask() {
        dayTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    Thread.sleep(5000);
                    day += 1;
                }
                return null;
            }
        };

        dayThread = new Thread(dayTask);
        dayThread.setDaemon(true); // Le thread s'arrête lorsque l'application se termine
        dayThread.start();
    }

    public List<Achetable> getCurrentActions() {
        return currentActions;
    }
    public int getRessources() {
        return ressources;
    }
    public void setRessources(int newRessource) {
        this.ressources = newRessource;
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
