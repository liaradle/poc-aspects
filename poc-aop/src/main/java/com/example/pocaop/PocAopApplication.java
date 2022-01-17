package com.example.pocaop;

import com.example.pocaop.data.Employee;
import com.example.pocaop.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PocAopApplication {

	@Autowired
	EmployeeManager employeeManager;

	public static void main(String[] args) {
		SpringApplication.run(PocAopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			Employee employee1 = employeeManager.getEmployeeById(1L);
			Employee employee2 = employeeManager.getEmployeeById(2L);
			String email = employeeManager.getEmailById(1L);
		};
	}
}
