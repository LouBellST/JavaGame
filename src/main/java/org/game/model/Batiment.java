package org.game.model;

import javafx.concurrent.Task;
import javafx.util.Pair;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Batiment implements Achetable {
    protected HashMap<String, Integer> price;
    protected int constructionTime;
    protected int placesRestantes;
    protected int placesTotales;
    protected HashMap<String, Integer> rendement;
    protected HashMap<String, Integer> besoins;
    protected int intervalleProduction;
    protected int size;

    public Batiment(HashMap<String, Integer> _price, int _constructionTime, int _placesTotales, HashMap<String, Integer> _rendement, HashMap<String, Integer> _besoins, int _intervalleProduction, int _size){
        price = _price;
        constructionTime = _constructionTime;
        placesRestantes = _placesTotales;
        placesTotales = _placesTotales;
        rendement = _rendement;
        besoins = _besoins;
        intervalleProduction = _intervalleProduction;
        size = _size;
    }


    @Override
    public HashMap<String, Integer> getPrice(){return price;}
    @Override
    public HashMap<String, Integer> getRendement(){return rendement;}
    @Override
    public HashMap<String, Integer> getConsommation() {return besoins;}
    @Override
    public int getConstructionTime(){return constructionTime;}
    public int getPlacesTotales(){return placesTotales;}
    public int getPlacesRestantes(){return placesRestantes;}
    public void setPlacesRestantes(int newPlacesRestantes){placesRestantes = newPlacesRestantes;}
    @Override
    public int getSize(){return size;}

    // fonction qui construit le batiment en question, actualisant les ressources du gameManager et la map
    @Override
    public void construire(GameManager gameManager, Model model){
        price.forEach((name, amount) -> {
            Ressource modifiedRessource = gameManager.getRessourceByName(name);
            modifiedRessource.setQuantity(modifiedRessource.getQuantity() - amount);
            Map m = gameManager.getMap();
            m.setFreeSize(m.getFreeSize() - size);
        });
        model.update();
    }

    // fonction qui actualise les ressources du gameManager en fonction du couple rendement/besoins du batiment
    // et du nombre d'habitant qu'il contient
    public void produire_consommerRessources(GameManager gameManager, int day){
        if(day %intervalleProduction == 0) {
            rendement.forEach((name, amount) -> {
                Ressource modifiedRessource = gameManager.getRessourceByName(name);
                modifiedRessource.setQuantity(modifiedRessource.getQuantity() + (amount*(placesTotales-placesRestantes)));
            });
            besoins.forEach((name, amount) -> {
                Ressource modifiedRessource = gameManager.getRessourceByName(name);
                modifiedRessource.setQuantity(modifiedRessource.getQuantity() - (amount*(placesTotales-placesRestantes)));
            });
        }
    }

}
