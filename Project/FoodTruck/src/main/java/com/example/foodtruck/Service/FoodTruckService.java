package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.Model.Category;
import com.example.foodtruck.Model.Evaluation;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Repository.CategoryRepository;
import com.example.foodtruck.Repository.EvaluationRepository;
import com.example.foodtruck.Repository.FoodTruckRepository;
import com.example.foodtruck.Repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class FoodTruckService {
    private final FoodTruckRepository foodTruckRepository;
    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private  final EvaluationRepository evaluationRepository;
    public List<FoodTruck> getAll(){
        return foodTruckRepository.findAll();
    }
    public void addFood(FoodTruck foodTruck){

        foodTruckRepository.save(foodTruck);
    }
    public void updateFoodTruck(Integer auth ,FoodTruck foodTruck) {
        FoodTruck OldFoodTruck = foodTruckRepository.findFoodTruckById(auth);
        if (OldFoodTruck == null) {
            throw new ApiException("the id nt found");
        }
        foodTruck.setId(OldFoodTruck.getId());
        foodTruckRepository.save(foodTruck);
    }
    public void deleteFoodTruck(Integer auth){
        FoodTruck food = foodTruckRepository.findFoodTruckById(auth);
        if (food == null) {
            throw new ApiException("the id nt found");
        }
        foodTruckRepository.delete(food);
    }
    public Set<FoodTruck> findOrderBycate(String category ){
        Category categor=categoryRepository.findCategoriesByName(category);
        if(categor==null){
            throw new ApiException("Category not found");
        }
        return categor.getFoodTrucks();
    }
    public  void orde(Integer id){
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(id);
        if (foodTruck == null) {
            throw new ApiException("the id nt found");
        }
        if (foodTruck.getCond().equals("ava")) {
            foodTruck.setCond("zz");
            foodTruckRepository.save(foodTruck);
        }else throw new ApiException("the food  zz ");
    }

    public  void endOrder(Integer id){
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(id);
        if (foodTruck == null) {
            throw new ApiException("the id nt found");
        }
        if (foodTruck.getCond().equals("zz")) {
            foodTruck.setCond("ava");
            foodTruckRepository.save(foodTruck);
        }else throw new ApiException("the food not zz ");
    }
    public void getAverageRatingForFood(Integer food_id) {
        FoodTruck foodTruck = foodTruckRepository.findFoodTruckById(food_id);
        List<Evaluation> reviews = evaluationRepository.findAllById(food_id);
        if(foodTruck == null){
            throw new ApiException("food Truck id incorrect");
        }
        double totalRating = reviews.stream().mapToInt(Evaluation::getRating).sum();
        foodTruck.setRating(totalRating/reviews.size());
        foodTruckRepository.save(foodTruck);
    }
    public List<FoodTruck> AllCarsSpecificPrice(Integer min , Integer max){
      List<FoodTruck> foodTruck=foodTruckRepository.findCarByDay(min,max);
        if (foodTruck == null) {
            throw new ApiException("food Truck id incorrect");
        }
        return foodTruck;
    }

    }
