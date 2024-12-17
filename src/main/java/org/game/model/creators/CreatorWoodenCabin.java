package org.game.model.creators;

import org.game.model.Creator;
import org.game.model.batiments.WoodenCabin;

import java.util.HashMap;

public class CreatorWoodenCabin implements Creator {
    @Override
    public WoodenCabin createBatiment(){
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Wood", 1);

        HashMap<String, Integer> rendement = new HashMap<>();
        rendement.put("Wood", 2);
        rendement.put("Food", 2);

        HashMap<String, Integer> besoins = new HashMap<>();

        return new WoodenCabin(price, 2000, 2, rendement, besoins, 1, 1);
    }
}
