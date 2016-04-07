package com.example;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = JpaProjectApplication.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")   // 4
@SpringBootApplication
public class JpaProjectApplication implements CommandLineRunner{

	
	@Autowired
	CustomerRepository customerrepository;
	public static void main(String[] args) {
		SpringApplication.run(JpaProjectApplication.class, args);
	}

	@Test
	public void run(String... arg0) throws Exception {
		
		Customer[] customerArray = {
			new Customer("customer1FirstName", "customer1LastName", 80),
			new Customer("customer2FirstName", "customer2LastName", 50),
			new Customer("customer3FirstName", "customer3LastName", 50)
		};
		
		for(Customer customer:customerArray){
			customerrepository.save(customer);
		}
		
		List<Customer> listOfCustomers =customerrepository.findAll();
		
		for(Customer customer:listOfCustomers){
			System.out.println("Customers: "+customer.toString() );
		}
		
		
	}
}
