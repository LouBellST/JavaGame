package org.game.model;

public class Map {
    private int size;
    private int freeSize;
    public Map(int _size){
        size = _size;
        freeSize = _size;
    }

    public int getSize() {
        return size;
    }

    public int getFreeSize() {
        return freeSize;
    }

    public void setFreeSize(int _size) {
        this.freeSize = _size;
    }
}
