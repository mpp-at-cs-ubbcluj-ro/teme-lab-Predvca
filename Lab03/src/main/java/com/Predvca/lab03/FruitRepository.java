package com.Predvca.lab03;

import java.util.List;

public interface FruitRepository extends Repository<Integer,Fruit> {
    List<Fruit> findByDistributor(String distributor);
    List<Fruit> findByName(String name);
}
