package com.weddly.bestmatchedrestaurantswr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	private String name;
    private Integer customerRating;
    private Integer distance;
    private Integer price;
    private String cuisine;
}