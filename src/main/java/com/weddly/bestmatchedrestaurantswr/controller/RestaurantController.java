package com.weddly.bestmatchedrestaurantswr.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weddly.bestmatchedrestaurantswr.exception.InvalidParameterException;
import com.weddly.bestmatchedrestaurantswr.exception.RequiredParameterException;
import com.weddly.bestmatchedrestaurantswr.model.Restaurant;
import com.weddly.bestmatchedrestaurantswr.service.RestaurantService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class RestaurantController {

    @Autowired
	private RestaurantService restaurantService;


    @GetMapping("/listAll")
    public List<Restaurant> searchAll() {
        
        List<Restaurant> results = restaurantService.getAllRestaurantsList();
        return results;
    };

    @GetMapping("/list")
    public List<Restaurant> search (
            @Parameter(description = "Restaurant Name")
                String name, 
            @Parameter(description = "Customer Rating: 1 star ~ 5 stars") 
            @RequestParam(required = false) 
                Integer customerRating,
            @Parameter(description = "Distance: 1 mile ~ 10 miles")
            @RequestParam(required = false) 
                Integer distance,
            @Parameter(description = "Price: $10 ~ $50 averange spend")
            @RequestParam(required = false) 
                Integer price,
            @Parameter(description = "Cuisine") 
            @RequestParam(required = false) 
                String cuisine
    ) throws InvalidParameterException, RequiredParameterException {
        return restaurantService.getRestaurantsList(name, customerRating, distance, price, cuisine);
    };

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<String> InvalidParameterException(InvalidParameterException invalidParameterException) {
        return new ResponseEntity<String>(invalidParameterException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequiredParameterException.class)
    public ResponseEntity<String> RequiredParameterException(RequiredParameterException requiredParameterException) {
        return new ResponseEntity<String>(requiredParameterException.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
}
