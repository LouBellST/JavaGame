package org.game.model;

import javafx.util.Pair;

import java.util.HashMap;

// interface Achetable regroupant toutes les unités achetable du jeu (habitant, batiment)
public interface Achetable {
    public HashMap<String, Integer> getPrice();
    public int getConstructionTime();
    public HashMap<String, Integer> getRendement();
    public HashMap<String, Integer> getConsommation();
    public void construire(GameManager gameManager, Model model);
    public int getSize();
}
