package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Customers;

@Repository
public interface CustomerRepo extends JpaRepository<Customers,String> {

	
	@Query("SELECT c from Customers c WHERE c.accountNumber=:accNo")
	public Customers findByaccNo(@RequestParam("accNo")String accNo);
}
