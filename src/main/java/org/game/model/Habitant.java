package org.game.model;

public class Habitant implements Achetable{
    private final int price = 25;
    private final int constructionTime = 5;

    public int getPrice(){return price;}
    public int getConstructionTime(){return constructionTime;}

    @Override
    public void construire(GameManager gameManager, Model model){
        gameManager.setRessources(gameManager.getRessources() - price);
        for (Batiment b : gameManager.getAllBatiments()){
            if(b.getPlacesRestantes() > 0){
                b.setPlacesRestantes(b.getPlacesRestantes() - 1);
                break;
            }
        }
        model.update();
    }
}
