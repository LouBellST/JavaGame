package org.game.model.creators;

import org.game.model.Creator;
import org.game.model.batiments.House;

import java.util.HashMap;

public class CreatorHouse implements Creator {
    @Override
    public House createBatiment(){
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Wood", 2);
        price.put("Stone", 2);

        HashMap<String, Integer> rendement = new HashMap<>();

        HashMap<String, Integer> besoins = new HashMap<>();

        return new House(price, 4000, 4, rendement, besoins, 1, 4);
    }
}