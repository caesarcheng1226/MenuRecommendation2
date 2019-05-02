
package com.example.menurecommendation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RecipeData {
    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> Meat = new ArrayList<>();
        Meat.add("Beef");
        Meat.add("Chicken");
        Meat.add("Pork");
        Meat.add("Other meats");

        List<String> Vegetable = new ArrayList<>();
        Vegetable.add("Flowers");
        Vegetable.add("Leafy");
        Vegetable.add("Pod");
        Vegetable.add("Root");
        Vegetable.add("Other vegetables");

        List<String> Fruit = new ArrayList<String>();
        Fruit.add("Apple");
        Fruit.add("Orange");
        Fruit.add("Pear");
        Fruit.add("Other fruits");

        List<String> Seafood = new ArrayList<String>();
        Seafood.add("Shrimp");
        Seafood.add("Crab");
        Seafood.add("Clam");
        Seafood.add("Fish");
        Seafood.add("Other seafoods");

        List<String> Soup = new ArrayList<String>();
        Soup.add("Chinese");
        Soup.add("Western");
        Soup.add("Other soups");

        expandableListDetail.put("Meat", Meat);
        expandableListDetail.put("Vegetable", Vegetable);
        expandableListDetail.put("Soup", Soup);
        expandableListDetail.put("Seafood", Seafood);
        expandableListDetail.put("Fruit", Fruit);

        return expandableListDetail;
    }
}
