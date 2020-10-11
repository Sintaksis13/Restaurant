package com.rp.restaurant.dish;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/api/dishes")
@Api(value = "/api/dishes", tags = {"Dishes Api"})
@CrossOrigin(
        methods = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE},
        maxAge = 3600,
        origins = "*"
)
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch all dishes from database", response = Dish.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "List of dishes"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public List<Dish> getAllDishes() {
        return dishService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new dish to database", response = Dish.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Dish was added successfully"),
            @ApiResponse(code = 400, message = "Malformed request"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public ResponseEntity<Dish> createDish(@RequestBody Dish dish) {
        Dish createdDish = dishService.createDish(dish);
        return new ResponseEntity<>(createdDish, HttpStatus.CREATED);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete all dishes from database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All dishes were deleted"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    public void deleteAllDishes() {
        dishService.deleteAll();
    }
}
