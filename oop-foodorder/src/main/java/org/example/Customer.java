package org.example;

public class Customer {

    public void order(String manuName, Menu menu, Cooking cooking) {
        MenuItem menuItem = menu.choose(manuName);
        Cook cook = cooking.makeCook(menuItem);
    }
}
