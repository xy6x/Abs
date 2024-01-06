package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.DTO.EvaluationDTO;
import com.example.foodtruck.Model.Evaluation;
import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Model.Ticket;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Repository.EvaluationRepository;
import com.example.foodtruck.Repository.FoodTruckRepository;
import com.example.foodtruck.Repository.TicketRepository;
import com.example.foodtruck.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EvaluationService {
    private final UserRepository userRepository;
    private final FoodTruckRepository foodTruckRepository;
    private final EvaluationRepository evaluationRepository;
    private final TicketRepository ticketRepository;
    private final FoodTruckService foodTruckService;
    public List<Evaluation> getAllEvaluation(){
        return evaluationRepository.findAll();}
    public void add(EvaluationDTO evaluationDTO){
        FoodTruck foodTruck =foodTruckRepository.findFoodTruckById(evaluationDTO.getFoodTruck_id());
        User user =userRepository.findUserById(evaluationDTO.getUser_id()) ;
        Ticket ticket = ticketRepository.findRentalByUserIdAAndCarId(evaluationDTO.getUser_id(),evaluationDTO.getFoodTruck_id());
        if (ticket == null) {
            throw new ApiException("ticket not found");
        }
        Evaluation review = new Evaluation(null,evaluationDTO.getFeedback(),evaluationDTO.getRating(),foodTruck,user);
        evaluationRepository.save(review);
        foodTruckService.getAverageRatingForFood(evaluationDTO.getFoodTruck_id());
        foodTruckRepository.save(foodTruck);
    }

    }
