package com.weddly.bestmatchedrestaurantswr.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import com.weddly.bestmatchedrestaurantswr.model.Restaurant;
import com.weddly.bestmatchedrestaurantswr.repository.RestaurantRepository;
import com.weddly.bestmatchedrestaurantswr.exception.InvalidParameterException;
import com.weddly.bestmatchedrestaurantswr.exception.RequiredParameterException;



@Service
public class RestaurantService {
    private List<Restaurant> allRestaurants = new ArrayList<>();    

    public List<Restaurant> getAllRestaurantsList(){

        if(this.allRestaurants.isEmpty()) {
            this.allRestaurants = RestaurantRepository.getAllRestaurants();
        }
        
        return allRestaurants;
    }

    public List<Restaurant> getRestaurantsList(String name, Integer customerRating, Integer distance, Integer price, String cuisine) throws RequiredParameterException {

        validateParameters(name, customerRating, distance, price);
        
        List<Restaurant> rawList = getAllRestaurantsList();
        List<Restaurant> filteredList = new ArrayList<Restaurant>();

        
        if(rawList != null && name != null) {
            filteredList = filterByName(rawList, name);
        }

        if(filteredList != null) {
            if(customerRating != null) {
                filteredList = filterByCustomerRating(filteredList, customerRating);
            }

            if(distance != null) {
                filteredList = filterByDistance(filteredList, distance);
            }

            if(price != null) {
                filteredList = fitlerByPrice(filteredList, price);
            }

            if(cuisine != null) {
                filteredList = filterByCuisine(filteredList, cuisine);
            }
        }

        filteredList = sortList(filteredList);
        return filteredList.stream().limit(5).toList();
    }

    private void validateParameters(String name, Integer customerRating, Integer distance, Integer price)  throws InvalidParameterException {

        if(name == null || name.isBlank()) {
            throw new RequiredParameterException("name");
        }
        
        if(customerRating != null && (customerRating < 1 || customerRating > 5)) {
            throw new InvalidParameterException("customerRating must be an Integer between 1 and 5");
        }           

        if(distance != null && (distance < 1 || distance > 10)) {
            throw new InvalidParameterException("distance must be an Integer between 1 and 10");
        }

        if(price != null && (price < 10 || price > 50)) {
            throw new InvalidParameterException("price must be an Integer between 10 and 50");
        }
    }

    private List<Restaurant> filterByName(List<Restaurant> entryList, String param) {

        List<Restaurant> result = new ArrayList<Restaurant>();
        
        entryList.forEach(elemet -> {
            if(elemet.getName().toLowerCase().contains(param.toLowerCase()))
                result.add(elemet);
        });
        return result;
    }

    private List<Restaurant> filterByCustomerRating(List<Restaurant> entryList, Integer param) {

        List<Restaurant> result = new ArrayList<Restaurant>();
        
        entryList.forEach(elemet -> {
            if(elemet.getCustomerRating() >= param)
                result.add(elemet);
        });
        return result;
    }

    private List<Restaurant> filterByDistance(List<Restaurant> entryList, Integer param) {

        List<Restaurant> result = new ArrayList<Restaurant>();
        
        entryList.forEach(elemet -> {
            if(elemet.getDistance() <= param)
                result.add(elemet);
        });
        return result;
    }

    private List<Restaurant> fitlerByPrice(List<Restaurant> entryList, Integer param) {

        List<Restaurant> result = new ArrayList<Restaurant>();
        
        entryList.forEach(elemet -> {
            if(elemet.getPrice() <= param)
                result.add(elemet);
        });
        return result;
    }

    private List<Restaurant> filterByCuisine(List<Restaurant> entryList, String cuisineSelected) {
        
        List<Restaurant> result = new ArrayList<Restaurant>();

        entryList.forEach(elemet -> {
            if(elemet.getCuisine().toLowerCase().contains(cuisineSelected.toString().toLowerCase()))
                result.add(elemet);
        });
        return result;
    }

    private List<Restaurant> sortList(List<Restaurant> entryList) {
        Comparator<Restaurant> comparebyDistance = Comparator.comparing( Restaurant::getDistance );
        Comparator<Restaurant> compareByCustomerRating = Comparator.comparing( Restaurant::getCustomerRating).reversed();
        Comparator<Restaurant> compareByPrice = Comparator.comparing( Restaurant::getPrice );
        Comparator<Restaurant> compareRestaurants = comparebyDistance.thenComparing(compareByCustomerRating).thenComparing(compareByPrice);
        
        return entryList.stream().sorted( compareRestaurants ).toList();
    }
}
