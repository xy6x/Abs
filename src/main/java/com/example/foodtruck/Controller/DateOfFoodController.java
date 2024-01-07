package com.example.foodtruck.Controller;

import com.example.foodtruck.Model.DateOfFood;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Service.DateOfFoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/date")
public class DateOfFoodController {
    private final DateOfFoodService dateOfFoodService;
    @GetMapping("/get")
    public ResponseEntity GetAllDateOfFood(){
        return ResponseEntity.status(HttpStatus.OK).body(dateOfFoodService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity AddDateOfFood(@RequestBody @Valid DateOfFood date){
        dateOfFoodService.addDateOfFood(date);
        return ResponseEntity.status(HttpStatus.OK).body("added DateOfFood");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity UpdateDateOfFood(@PathVariable Integer id,@RequestBody @Valid DateOfFood date){
        dateOfFoodService.updateDate(id, date);
        return ResponseEntity.status(HttpStatus.OK).body("update DateOfFood");
    }
}
