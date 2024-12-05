package org.game.model;

public interface Achetable {
    public int getPrice();
    public int getConstructionTime();
    public int getRendement();
    public void construire(GameManager gameManager, Model model);
}
