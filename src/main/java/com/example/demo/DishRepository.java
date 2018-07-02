package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish,Long>{
    Iterable<Dish> findAllByTastyVotesInAndNastyVotesIn(Iterable<Tasty>tasties, Iterable<Nasty> nasties);
}