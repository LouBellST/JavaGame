package org.game.model.creators;

import org.game.model.Creator;
import org.game.model.batiments.SteelMill;

import java.util.HashMap;

public class CreatorSteelMill implements Creator {
    @Override
    public SteelMill createBatiment(){
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Wood", 100);
        price.put("Stone", 50);

        HashMap<String, Integer> rendement = new HashMap<>();
        rendement.put("Steel", 4);

        HashMap<String, Integer> besoins = new HashMap<>();
        besoins.put("Iron", 4);
        besoins.put("Coal", 2);

        return new SteelMill(price, 6000, 40, rendement, besoins, 1, 12);
    }
}