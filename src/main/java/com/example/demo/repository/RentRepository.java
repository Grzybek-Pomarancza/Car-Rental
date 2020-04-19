package com.example.demo.repository;

import com.example.demo.model.Rent;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long>, JpaSpecificationExecutor {

}
