package com.ulas.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RestaurantResponse {
    private List<RestaurantDTO> data=new ArrayList<>();
    private Date responseDate;
    private String message;
    private boolean success;



}
