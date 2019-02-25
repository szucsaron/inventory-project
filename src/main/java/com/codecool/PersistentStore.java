package com.codecool;

import java.util.ArrayList;


public class PersistentStore extends Store {

    /*
    The PersistentStore extends the Store class and implements the storeProduct method.
    When called the incoming product is stored in memory (in a list, or array for example).
     */


    public void storeProduct(Product product) {
        storedProducts.add(product);
    }
}
