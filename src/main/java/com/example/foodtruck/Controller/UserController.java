package com.example.foodtruck.Controller;

import com.example.foodtruck.DTO.UserDTO;
import com.example.foodtruck.Model.User;
import com.example.foodtruck.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity GetAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity AddUser(@RequestBody @Valid UserDTO user){
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("added Client");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity UpdateUser(@PathVariable Integer id,@RequestBody @Valid User user){
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("update Client");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Client");
    }
    @GetMapping("/login/{username}/{password}")
    private ResponseEntity logIn(@PathVariable String username, @PathVariable String password){
        String logIn = userService.logIn(username,password);
        return ResponseEntity.status(200).body(logIn);
    }   @GetMapping("/evaluation/{men}/{mix}")
    private ResponseEntity AllFoodEvaluation(@PathVariable Integer men, @PathVariable Integer mix){
        return ResponseEntity.status(200).body(userService.AllCarsSpecificEvaluation(men, mix));
    }
    @GetMapping("/return/{number}")
    private ResponseEntity logIn(@PathVariable Integer number){
        return ResponseEntity.status(200).body(userService.ret(number));
    }
    @GetMapping("/food/{number}")
    private ResponseEntity food(@PathVariable Integer number){
        return ResponseEntity.status(200).body(userService.currentStatus(number));
    }
    @GetMapping("/check")
    private ResponseEntity check(){

        return ResponseEntity.status(200).body(userService.check());
    }

}
