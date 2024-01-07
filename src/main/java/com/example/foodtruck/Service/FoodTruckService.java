package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.Model.Category;
import com.example.foodtruck.Model.Evaluation;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Model.Ticket;
import com.example.foodtruck.Repository.*;
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
    private final DateOfFoodRepository dateOfFoodRepository;
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
    public Set<FoodTruck> findOrderBycate(String category){
        Category categor=categoryRepository.findCategoriesByName(category);
        if(categor==null){
            throw new ApiException("Category not found");
        }
        return categor.getFoodTrucks();
    }
    public  void acceptOrder(Integer id){
        Ticket ticket=ticketRepository.findTicketById(id);

        if (ticket == null) {
            throw new ApiException("the id nt found");
        }

        if (ticket.getFoodTruck().getCond().equals("available")&& ticket.getStatus().equals("null")==ticket.getStatus().equals("Accept")){
            ticket.getFoodTruck().setCond("tenant");
            ticket.getFoodTruck().setRequests(ticket.getFoodTruck().getRequests()+1);
            foodTruckRepository.save(ticket.getFoodTruck());
            ticket.setStatus("Accept");
            ticketRepository.save(ticket);
        }else throw new ApiException("the food  tenant");
    }    public  void rejectOrder(Integer id) {
        Ticket ticket = ticketRepository.findTicketById(id);

        if (ticket == null) {
            throw new ApiException("the id nt found");
        }
        if (ticket.getFoodTruck().getCond().equals("available") && ticket.getStatus().equals("null") == ticket.getStatus().equals("Reject")) {
            ticket.setStatus("Reject");
            ticketRepository.save(ticket);
        }else throw new ApiException("the food  tenant");
    }

    public  void endOrder(Integer id){
        FoodTruck foodTruck=foodTruckRepository.findFoodTruckById(id);
        if (foodTruck == null) {
            throw new ApiException("the id nt found");
        }
        if (foodTruck.getCond().equals("Reject")) {
            foodTruck.setCond("available");
            foodTruckRepository.save(foodTruck);
        }else throw new ApiException("the food not Reject ");
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


    }
