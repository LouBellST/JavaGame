package org.game.model;

import javafx.concurrent.Task;

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

    public Batiment(HashMap<String, Integer> _price, int _constructionTime, int _placesTotales, HashMap<String, Integer> _rendement, HashMap<String, Integer> _besoins){
        price = _price;
        constructionTime = _constructionTime;
        placesRestantes = _placesTotales;
        placesTotales = _placesTotales;
        rendement = _rendement;
        besoins = _besoins;
    }


    @Override
    public HashMap<String, Integer> getPrice(){return price;}
    @Override
    public HashMap<String, Integer> getRendement(){return rendement;}
    @Override
    public HashMap<String, Integer> getConsommation() {return besoins;}
    @Override
    public int getConstructionTime(){return constructionTime;}
    public int getPlacesRestantes(){return placesRestantes;}
    public void setPlacesRestantes(int newPlacesRestantes){placesRestantes = newPlacesRestantes;}
    @Override
    public void construire(GameManager gameManager, Model model){
        List<String> keys = new ArrayList<>(price.keySet());
        gameManager.getRessources().forEach(r -> {keys.forEach(k -> {
            if(r.getName().equals(k)){
                r.setQuantity(r.getQuantity() - price.get(k));
            }
        });});
        model.update();
    }

    public void produire_consommerRessources(GameManager gameManager){
        int production = (placesTotales - placesRestantes) * rendement;
        int consommation = (placesTotales - placesRestantes) * besoins;
        int gainFinal = production - consommation;

        gameManager.setRessources(gameManager.getRessources() + gainFinal);
    }

}
