package com.example.demo.repository;

import com.example.demo.model.Rent;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long>{

    @Query("SELECT r FROM RENTS r" +
            " WHERE r.car.id=?3 and r.rentDate>=?1 AND r.returnDate<=?2")
    List<Rent> getRentForCarsInData(Date rentDate, Date returnDate,Long id);
}
