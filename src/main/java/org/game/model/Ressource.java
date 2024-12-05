package org.game.model;

public class Ressource {
    protected String name;
    protected int quantity;
    protected int maxQty;

    public Ressource(String _name, int _quantity, int _maxQty){
        name = _name;
        quantity = _quantity;
        maxQty = _maxQty;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public int getMaxQty() {return maxQty;}
    public void setMaxQty(int maxQty) {this.maxQty = maxQty;}
}
