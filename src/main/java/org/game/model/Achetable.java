package org.game.model;

import javafx.util.Pair;

import java.util.HashMap;

public interface Achetable {
    public HashMap<String, Integer> getPrice();
    public int getConstructionTime();
    public HashMap<String, Integer> getRendement();
    public HashMap<String, Integer> getConsommation();
    public void construire(GameManager gameManager, Model model);
}
