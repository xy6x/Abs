package com.example.foodtruck.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DateOfFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate year =LocalDate.now();
    private Integer month;
    private Integer week;
    @Pattern(regexp = "^(available|bookedUp)$",message = "status must be available or bookedUp")
    @Column(columnDefinition = "varchar(10) not null Check (saturday='bookedUp' or saturday='available') ")
    private String saturday;
    @Pattern(regexp = "^(available|bookedUp)$",message = "status must be available or bookedUp")
    @Column(columnDefinition = "varchar(10) not null Check (sunday='bookedUp' or sunday='available') ")
    private String sunday;
    @Pattern(regexp = "^(available|bookedUp)$",message = "status must be available or bookedUp")
    @Column(columnDefinition = "varchar(10) not null Check (monday='bookedUp' or monday='available') ")
    private String monday;
    @Pattern(regexp = "^(available|bookedUp)$",message = "status must be available or bookedUp")
    @Column(columnDefinition = "varchar(10) not null Check (tuesday='bookedUp' or tuesday='available') ")
    private String tuesday;
    @Pattern(regexp = "^(available|bookedUp)$",message = "status must be available or bookedUp")
    @Column(columnDefinition = "varchar(10) not null Check (wednesday='bookedUp' or wednesday='available') ")
    private String wednesday;
    @Pattern(regexp = "^(available|bookedUp)$",message = "status must be available or bookedUp")
    @Column(columnDefinition = "varchar(10) not null Check (thursday='bookedUp' or thursday='available') ")
    private String thursday;
    @Pattern(regexp = "^(available|bookedUp)$",message = "Friday must be available or bookedUp")
    @Column(columnDefinition = "varchar(10) not null Check (friday='bookedUp' or friday='available') ")
    private String friday;
    @ManyToOne
    @JoinColumn(name = "foodTruck_id",referencedColumnName = "id")
    @JsonIgnore
    private FoodTruck foodTruck;
}
