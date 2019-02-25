package com.codecool;

public class BookProduct extends Product {
    private int numOfTracks;

    public BookProduct(String name, int price, int size) {
        super(name, price);
        this.numOfTracks = size;
        super.type = "book";
    }

    @Override
    public int getSize() {
        return numOfTracks;
    }
}
