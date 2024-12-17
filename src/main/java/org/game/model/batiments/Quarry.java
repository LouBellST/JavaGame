package org.game.model.batiments;

import org.game.model.Batiment;

import java.util.HashMap;

public class Quarry extends Batiment {
    public Quarry(HashMap<String, Integer> _price, int _constructionTime, int _placesTotales, HashMap<String, Integer> _rendement, HashMap<String, Integer> _besoins, int _intervalleProduction, int _size){
        super(_price, _constructionTime, _placesTotales, _rendement, _besoins, _intervalleProduction, _size);
    }
}
