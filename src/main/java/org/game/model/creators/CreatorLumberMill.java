package org.game.model.creators;

import org.game.model.Creator;
import org.game.model.batiments.LumberMill;

import java.util.HashMap;

public class CreatorLumberMill implements Creator {
    @Override
    public LumberMill createBatiment(){
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Wood", 50);
        price.put("Stone", 50);

        HashMap<String, Integer> rendement = new HashMap<>();
        rendement.put("Lumber", 4);

        HashMap<String, Integer> besoins = new HashMap<>();
        besoins.put("Wood", 4);

        return new LumberMill(price, 4000, 10, rendement, besoins, 1, 9);
    }
}