package com.codecool;

public class CDProduct extends Product {
    private int numOfPages;
    public CDProduct(String name, int price, int size) {
        super(name, price);
        this.numOfPages = size;
        super.type = "cd";
    }

    @Override
    public int getSize() {
        return numOfPages;
    }
}
