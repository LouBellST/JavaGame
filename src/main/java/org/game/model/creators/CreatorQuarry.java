package org.game.model.creators;

import org.game.model.Creator;
import org.game.model.batiments.Quarry;

import java.util.HashMap;

public class CreatorQuarry implements Creator {
    @Override
    public Quarry createBatiment(){
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Wood", 50);

        HashMap<String, Integer> rendement = new HashMap<>();
        rendement.put("Stone", 4);
        rendement.put("Iron", 4);
        rendement.put("Coal", 4);

        HashMap<String, Integer> besoins = new HashMap<>();

        return new Quarry(price, 2000, 32, rendement, besoins, 1, 4);
    }
}