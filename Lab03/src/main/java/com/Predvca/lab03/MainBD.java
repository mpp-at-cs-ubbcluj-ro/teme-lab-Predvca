package com.Predvca.lab03;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainBD {
    public static void main(String[] args) {

        Properties props = new Properties();
        try {
            props.load(MainBD.class.getClassLoader().getResourceAsStream("bd.config"));
        } catch (IOException | NullPointerException e) {
            System.out.println("Cannot find bd.config: " + e);
        }

        FruitRepository fruitRepo = new FruitDBRepository(props);

        Fruit newFruit = new Fruit("apple", "Golden", "FreshFruits", 500, "kg");
        fruitRepo.add(newFruit);
        System.out.println("Added: " + newFruit);

        int idToUpdate = 1;
        Fruit updatedFruit = new Fruit("banana", "Cavendish", "Chiquita", 500, "kg");
        fruitRepo.update(idToUpdate, updatedFruit);
        System.out.println("Modified fruit with id " + idToUpdate + ": " + updatedFruit);

        String distributor = "Chiquita";
        System.out.println("\nFructele distribuite de " + distributor + ":");
        for (Fruit fruit : fruitRepo.findByDistributor(distributor)) {
            System.out.println(fruit);
        }

        System.out.println("\nToate fructele din baza de date:");
        for (Fruit fruit : fruitRepo.findAll()) {
            System.out.println(fruit);
        }
    }
}
