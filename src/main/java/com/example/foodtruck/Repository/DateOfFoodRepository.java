package com.example.foodtruck.Repository;

import com.example.foodtruck.Model.DateOfFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateOfFoodRepository extends JpaRepository<DateOfFood,Integer> {
    DateOfFood findDateOfFoodById(Integer id);
}
