package com.codecool;

import java.util.List;

interface StorageCapable {
    List<Product> getAllProducts();
    void storeCDProduct(String name, int price, int tracks);
    void storeBookProduct(String name, int price, int pages);

}
