package com.example.foodtruck.Repository;

import com.example.foodtruck.Model.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FoodTruckRepository extends JpaRepository<FoodTruck,Integer> {
    FoodTruck findFoodTruckById(Integer id);
    @Query("select c from FoodTruck c where c.cond='Available'")
    List<FoodTruck> check();
    @Query("select e from FoodTruck e where e.rating between  ?1 and  ?2")
    List<FoodTruck> findCarByDay(Integer min, Integer Max);

}
