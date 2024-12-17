package org.game.model.creators;

import org.game.model.Creator;
import org.game.model.batiments.Farm;

import java.util.HashMap;

public class CreatorFarm implements Creator {
    @Override
    public Farm createBatiment(){
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Wood", 5);
        price.put("Stone", 5);

        HashMap<String, Integer> rendement = new HashMap<>();
        rendement.put("Food", 10);

        HashMap<String, Integer> besoins = new HashMap<>();

        return new Farm(price, 2000, 8, rendement, besoins, 1, 9);
    }
}