package org.game.model.creators;

import org.game.model.Creator;
import org.game.model.batiments.CementPlant;

import java.util.HashMap;

public class CreatorCementPlant implements Creator {
    @Override
    public CementPlant createBatiment(){
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Wood", 50);
        price.put("Stone", 50);

        HashMap<String, Integer> rendement = new HashMap<>();
        rendement.put("Cement", 4);

        HashMap<String, Integer> besoins = new HashMap<>();
        besoins.put("Stone", 4);
        besoins.put("Coal", 4);

        return new CementPlant(price, 4000, 10, rendement, besoins, 1, 12);
    }
}