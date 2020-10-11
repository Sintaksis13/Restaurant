package com.rp.restaurant.dish;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DishService {
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public List<Dish> findAll() {
        Iterable<Dish> dishes = repository.findAll();
        return StreamSupport.stream(dishes.spliterator(), true)
                .collect(Collectors.toList());
    }
}
