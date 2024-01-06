package com.example.foodtruck.Repository;

import com.example.foodtruck.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Ticket findTicketById(Integer id);
    @Query("select c from Ticket c where c.user.id=?1 and c.foodTruck.id=?2")
    Ticket findRentalByUserIdAAndCarId(Integer user_id, Integer cra_id);

}
