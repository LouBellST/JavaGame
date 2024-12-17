package org.game.model.creators;

import org.game.model.Creator;
import org.game.model.batiments.ApartmentBuilding;

import java.util.HashMap;

public class CreatorApartmentBuilding implements Creator {
    @Override
    public ApartmentBuilding createBatiment(){
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Wood", 50);
        price.put("Stone", 50);

        HashMap<String, Integer> rendement = new HashMap<>();

        HashMap<String, Integer> besoins = new HashMap<>();

        return new ApartmentBuilding(price, 6000, 60, rendement, besoins, 1, 6);
    }
}