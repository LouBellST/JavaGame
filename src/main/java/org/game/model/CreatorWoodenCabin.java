package org.game.model;

public class CreatorWoodenCabin implements Creator{
    @Override
    public WoodenCabin createBatiment(){
        return new WoodenCabin(100, 3000, 3, 5, 3);
    }
}
