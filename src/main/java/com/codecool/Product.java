package com.codecool;

abstract public class Product {
    private String name;
    protected String type;



    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return 0;
    }

    public String getType() {
        return type;
    }
}
