package com.example.demo.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Customers;
import com.example.demo.repository.CustomerRepo;

@RestController
@CrossOrigin(value="http://localhost:4200")
public class controller {

	@Autowired
	public CustomerRepo customerRepo;
	
	@PostMapping("/saveCustomer")
	public Customers saveCustomer(@RequestBody Customers customerDetails) {
		return customerRepo.save(customerDetails);
		
	}
	
	@GetMapping("/getAccount")
	public Customers getAccountDetails(@RequestParam("accNo") String accNo) {
		
		return customerRepo.findByaccNo(accNo);
		
	}
	@GetMapping("/findAllDetails")
	public List<Customers> getAllDetails(){
		return customerRepo.findAll();
	}
	
	@PostMapping("/sendAmt")
	public Customers sendAmount(@RequestBody Customers cust) {
		Customers cust1=new Customers();
		cust1=customerRepo.findByaccNo(cust.getAccountNumber());
		int amount=Integer.parseInt(cust1.getTotalBalance());
		amount=Integer.parseInt(cust.getDebit())+amount;
		cust1.setTotalBalance(Integer.toString(amount));
		cust1.setCredit(cust.getDebit());
		customerRepo.save(cust1);
		
		Customers cust2=new Customers();
		cust2=customerRepo.findByaccNo(cust.getCredit());
		int amount2=Integer.parseInt(cust2.getTotalBalance());
		amount2=amount2-Integer.parseInt(cust.getDebit());
		cust2.setTotalBalance(Integer.toString(amount2));
		cust2.setDebit(cust.getDebit());
		return customerRepo.save(cust2);
		
	}
}
