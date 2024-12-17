package org.game.model;


import javafx.concurrent.Task;
import org.game.model.batiments.*;
import org.game.model.creators.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


// classe dans laquelle tout le jeu se déroule, elle conserve une trace des ressources, de l'état de la map et des
// batiments du jouer tout au long de la partie
public class GameManager {

    private HashMap<String, Creator> creators;
    private List<Batiment> allBatiments;
    private List<Ressource> ressources;
    private int day;
    private Map map;
    private Task<Void> dayTask;
    private Thread dayThread;  // Thread pour tenir compte des jours


    public GameManager(){
        map = new Map(200);
        creators = new HashMap<>();
        creators.put("WoodenCabin", new CreatorWoodenCabin());
        creators.put("House", new CreatorHouse());
        creators.put("Farm", new CreatorFarm());
        creators.put("ApartmentBuilding", new CreatorApartmentBuilding());
        creators.put("SteelMill", new CreatorSteelMill());
        creators.put("CementPlant", new CreatorCementPlant());
        creators.put("LumberMill", new CreatorLumberMill());
        creators.put("ToolFactory", new CreatorToolFactory());
        creators.put("Quarry", new CreatorQuarry());

        allBatiments = new ArrayList<>();
        ressources = new ArrayList<>();
        ressources.add(new Ressource("Food", 5, 5000));
        ressources.add(new Ressource("Wood", 5, 5000));
        ressources.add(new Ressource("Iron", 0, 5000));
        ressources.add(new Ressource("Stone", 5, 5000));
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
            case ACHETER_STEELMILL -> {
                SteelMill newW = (SteelMill) creators.get("SteelMill").createBatiment();
                newW.construire(this, model);
                this.allBatiments.add(newW);
            }
            case ACHETER_APARTMENTBUILDING -> {
                ApartmentBuilding newW = (ApartmentBuilding) creators.get("ApartmentBuilding").createBatiment();
                newW.construire(this, model);
                this.allBatiments.add(newW);
            }
            case ACHETER_CEMENTPLANT -> {
                CementPlant newW = (CementPlant) creators.get("CementPlant").createBatiment();
                newW.construire(this, model);
                this.allBatiments.add(newW);
            }
            case ACHETER_FARM -> {
                Farm newW = (Farm) creators.get("Farm").createBatiment();
                newW.construire(this, model);
                this.allBatiments.add(newW);
            }
            case ACHETER_HOUSE -> {
                House newW = (House) creators.get("House").createBatiment();
                newW.construire(this, model);
                this.allBatiments.add(newW);
            }
            case ACHETER_LUMBERMILL -> {
                LumberMill newW = (LumberMill) creators.get("LumberMill").createBatiment();
                newW.construire(this, model);
                this.allBatiments.add(newW);
            }
            case ACHETER_QUARRY -> {
                Quarry newW = (Quarry) creators.get("Quarry").createBatiment();
                newW.construire(this, model);
                this.allBatiments.add(newW);
            }
            case ACHETER_TOOLFACTORY -> {
                ToolFactory newW = (ToolFactory) creators.get("ToolFactory").createBatiment();
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


    // fonction qui créer un thread dans lequel la gestion des jours et la production de chaque batiments est appelé.
    private void startDayTask() {
        GameManager gm = this;
        dayTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    Thread.sleep(3000);
                    day += 1;
                    allBatiments.forEach(batiment -> batiment.produire_consommerRessources(gm, day));
                    if(day%30==0){ressources.get(0).setQuantity(ressources.get(0).getQuantity() + 200);}
                }
                return null;
            }
        };

        dayThread = new Thread(dayTask);
        dayThread.setDaemon(true); // Le thread s'arrête lorsque l'application se termine
        dayThread.start();
    }


    public Ressource getRessourceByName(String name) {
        return ressources.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst()
                .orElse(null); // Retourne la première correspondance ou null
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
    public String displayRessources(){
        String allRessources = "";
        for(Ressource r : ressources){
            allRessources += (r.getName() + " : " + r.getQuantity() + " | ");
        }
        return allRessources;
    }

    public Map getMap() {
        return map;
    }

    public List<Batiment> getAllBatiments(){return allBatiments;}
    public int getDay(){return day;}


    // fonction renvoyant une liste des actions ralisable en par rapport aux ressources du joueurs et à l'état de la map
    public List<Event> getAvailableEvents() {
        List<Event> availableEvents = new ArrayList<>();
        int placesRestantes = allBatiments.stream().mapToInt(Batiment::getPlacesRestantes).sum();

        for (Event event : Event.values()){
            AtomicBoolean canBuy = new AtomicBoolean(true);
            event.getEventClass().getPrice().forEach((name, amount) -> {
                Ressource modifiedRessource = this.getRessourceByName(name);
                if(modifiedRessource.getQuantity() < amount){
                    canBuy.set(false);
                }
            });
            if(event == Event.ACHETER_HABITANT && placesRestantes == 0){
                canBuy.set(false);
            }

            if(canBuy.get() && map.getFreeSize() >= event.getEventClass().getSize()){
                availableEvents.add(event);
            }
        }
        return (availableEvents);
    }
}
