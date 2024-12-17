package org.game.model;

import java.util.HashMap;

public class Habitant implements Achetable{
    private HashMap<String, Integer> price = new HashMap<>();
    private final int constructionTime = 5;

    public Habitant(){
        price.put("Food", 5);
    }

    @Override
    public int getConstructionTime(){return constructionTime;}
    @Override
    public HashMap<String, Integer> getPrice(){return price;}
    @Override
    public HashMap<String, Integer> getRendement(){return null;}
    @Override
    public HashMap<String, Integer> getConsommation() {return null;}
    @Override
    public int getSize(){return 1;}

    @Override
    public void construire(GameManager gameManager, Model model){
        Ressource modifiedRessource = gameManager.getRessourceByName("Food");
        modifiedRessource.setQuantity(modifiedRessource.getQuantity() - price.get("Food"));

        for (Batiment b : gameManager.getAllBatiments()){
            if(b.getPlacesRestantes() > 0){
                b.setPlacesRestantes(b.getPlacesRestantes() - 1);
                break;
            }
        }
        model.update();
    }
}
