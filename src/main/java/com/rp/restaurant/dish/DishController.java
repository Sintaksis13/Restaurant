package com.rp.restaurant.dish;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant/api/dishes")
@Api(value = "/api/dishes", tags = {"Dishes Api"})
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    @ApiOperation(value = "Fetch all dishes from database", response = Dish.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of dishes"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public List<Dish> getAllDishes() {
        return dishService.findAll();
    }
}
