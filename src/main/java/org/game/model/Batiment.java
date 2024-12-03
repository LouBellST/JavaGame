package org.game.model;

import javafx.concurrent.Task;

public class Batiment implements Achetable {
    private final int price = 100;
    private final int constructionTime = 30;
    private int placesRestantes;
    private int placesTotales;
    private int rendement;

    private Task<Void> batimentTask;
    private Thread batimentThread;  // Thread pour exécuter la Task

    public Batiment(){
        placesRestantes = 3;
        placesTotales = 3;
        rendement = 5;
    }

    public int getPrice(){return price;}
    public int getRendement(){return rendement;}
    public int getConstructionTime(){return constructionTime;}
    public int getPlacesRestantes(){return placesRestantes;}
    public void setPlacesRestantes(int newPlacesRestantes){placesRestantes = newPlacesRestantes;}
    @Override
    public void construire(GameManager gameManager, Model model){
        gameManager.setRessources(gameManager.getRessources() - price);
        startBatimentTask(gameManager);
        model.update();
    }

    private void startBatimentTask(GameManager gameManager) {
        batimentTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    gameManager.setRessources(gameManager.getRessources() + (placesTotales - placesRestantes) * rendement);
                    Thread.sleep(1000);
                }
                return null;
            }
        };

        batimentThread = new Thread(batimentTask);
        batimentThread.setDaemon(true); // Le thread s'arrête lorsque l'application se termine
        batimentThread.start();
    }
}
