package com.weddly.bestmatchedrestaurantswr.repository;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.weddly.bestmatchedrestaurantswr.model.Cuisine;

public class CuisineRepository  {
    public static List<Cuisine> getAllCuisines() {
        List<Cuisine> list = new ArrayList<Cuisine>();
        String file = RestaurantRepository.class.getClassLoader().getResource("cuisines.csv").getPath();
        

        try { 
        FileReader filereader = new FileReader(file); 
        CSVReader csvReader = new CSVReaderBuilder(filereader) 
                                  .withSkipLines(1) 
                                  .build(); 
        List<String[]> allData = csvReader.readAll(); 

        for (String[] row : allData) {
            Cuisine r = new Cuisine();
            r.setCuisineId(Integer.parseInt(row[0]));
            r.setCuisineName(row[1]);

            list.add(r);
        } 
    } 
    catch (Exception e) { 
        e.printStackTrace(); 
    }
        return list;
    }
}