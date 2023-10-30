package com.weddly.bestmatchedrestaurantswr.repository;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.weddly.bestmatchedrestaurantswr.model.Cuisine;
import com.weddly.bestmatchedrestaurantswr.model.Restaurant;

public class RestaurantRepository {

    public static List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<Restaurant>();
        String file = RestaurantRepository.class.getClassLoader().getResource("restaurants.csv").getPath();

        try { 
        FileReader filereader = new FileReader(file); 
        CSVReader csvReader = new CSVReaderBuilder(filereader) 
                                  .withSkipLines(1) 
                                  .build(); 
        List<String[]> allData = csvReader.readAll(); 

        for (String[] row : allData) {
            Restaurant r = new Restaurant();
            r.setName(row[0]);
            r.setCustomerRating(Integer.parseInt(row[1]));
            r.setDistance(Integer.parseInt(row[2]));
            r.setPrice(Integer.parseInt(row[3]));            
            r.setCuisine(convertCuisineIdToName(row[4]));

            list.add(r);
        } 
    } 
    catch (Exception e) { 
        e.printStackTrace(); 
    }
        return list;
    }

    private static String convertCuisineIdToName (String id) {
        List<Cuisine> allCuisine = CuisineRepository.getAllCuisines();

        if(allCuisine.size() > 0) {
            for(Cuisine c: allCuisine) {
                if(c.getCuisineId().toString().equals(id)){
                    return c.getCuisineName();
                }
            }
        }
        return "";
    }
}