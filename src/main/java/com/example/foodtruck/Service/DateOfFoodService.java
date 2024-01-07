package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.Model.DateOfFood;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Repository.DateOfFoodRepository;
import com.example.foodtruck.Repository.FoodTruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DateOfFoodService {
    private final DateOfFoodRepository dateOfFoodRepository;
    private final FoodTruckRepository foodTruckRepository;
    public List<DateOfFood> getAll(){
        return dateOfFoodRepository.findAll();
    }
    public void addDateOfFood(DateOfFood date){
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(date.getFoodTruck().getId());
        if (foodTruck == null) {
            throw new ApiException("the food truck not found");
        }
        date.setSaturday("available");
        date.setSunday("available");
        date.setMonday("available");
        date.setTuesday("available");
        date.setWednesday("available");
        date.setThursday("available");
        date.setFriday("available");
        dateOfFoodRepository.save(date);
    }
    public void updateDate(Integer id,DateOfFood dateOfFood){
        DateOfFood date=dateOfFoodRepository.findDateOfFoodById(id);
        if (date == null) {
            throw new ApiException("the date not found");
        }
        dateOfFood.setId(date.getId());
        dateOfFoodRepository.save(date);
    }

}
