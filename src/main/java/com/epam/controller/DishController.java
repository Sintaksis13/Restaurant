package com.epam.controller;

import com.epam.dao.DaoResult;
import com.epam.entity.Dish;
import com.epam.entity.response.ResponseEntity;
import com.epam.service.HibernateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DishController {
    private final HibernateService<Dish> dishService;

    public DishController(HibernateService<Dish> dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/dish")
    public ResponseEntity createDish(@RequestBody Dish dish) {
        DaoResult result = dishService.save(dish);
        return new ResponseEntity(result, dish);
    }
    
    @GetMapping("/dish")
    public ResponseEntity findAllDishes() {
        List<Dish> dishes = dishService.findAll();
        return new ResponseEntity(checkResult(dishes), dishes);
    }
    
    private DaoResult checkResult(List<Dish> dishes) {
        DaoResult result;
        if (dishes == null) {
            result = DaoResult.FAILED.setMessage("Dish list is null");
        } else {
            result = DaoResult.SUCCESSFUL;
        }
        
        return result;
    }
}
