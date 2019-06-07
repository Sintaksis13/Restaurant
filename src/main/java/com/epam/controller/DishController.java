package com.epam.controller;

import com.epam.dao.result.DaoResult;
import com.epam.entity.Dish;
import com.epam.entity.response.ResponseEntity;
import com.epam.service.HibernateService;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DishController {
    private final HibernateService<Dish> dishService;

    public DishController(HibernateService<Dish> dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/dish")
    public ResponseEntity createDish(@RequestBody Dish dish) {
        Pair<DaoResult, Dish> creatingResult = dishService.save(dish);
        return new ResponseEntity(creatingResult.getKey(), creatingResult.getValue());
    }
    
    @GetMapping("/dish")
    public ResponseEntity findAllDishes() {
        Pair<DaoResult, List<Dish>> fetchingResult = dishService.findAll();
        return new ResponseEntity(fetchingResult.getKey(), fetchingResult.getValue());
    }

    @GetMapping("/dish/{name}")
    public ResponseEntity findDishByName(@PathVariable String name) {
        Pair<DaoResult, Dish> fetchingResult = dishService.findByName(name);
        return new ResponseEntity(fetchingResult.getKey(), fetchingResult.getValue());
    }

    @DeleteMapping("/dish/{name}")
    public ResponseEntity deleteDishByName(@PathVariable String name) {
        Pair<DaoResult, Dish> deleteResult = dishService.deleteByName(name);
        return new ResponseEntity(deleteResult.getKey(), deleteResult.getValue());
    }

    @PutMapping("/dish/{name}")
    public ResponseEntity updateDish(@PathVariable String name, @RequestBody Dish dish) {
        Pair<DaoResult, Dish> updatedResult = dishService.update(name, dish);
        return new ResponseEntity(updatedResult.getKey(), updatedResult.getValue());
    }
}
