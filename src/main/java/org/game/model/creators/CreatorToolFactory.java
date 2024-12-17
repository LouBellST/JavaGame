package org.game.model.creators;

import org.game.model.Creator;
import org.game.model.batiments.ToolFactory;

import java.util.HashMap;

public class CreatorToolFactory implements Creator {
    @Override
    public ToolFactory createBatiment(){
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Wood", 50);
        price.put("Stone", 50);

        HashMap<String, Integer> rendement = new HashMap<>();
        rendement.put("Tool", 4);

        HashMap<String, Integer> besoins = new HashMap<>();
        besoins.put("Steel", 4);
        besoins.put("Coal", 4);

        return new ToolFactory(price, 8000, 12, rendement, besoins, 1, 12);
    }
}