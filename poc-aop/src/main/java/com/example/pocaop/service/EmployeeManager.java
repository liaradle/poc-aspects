package com.example.pocaop.service;

import com.example.pocaop.data.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EmployeeManager {
    static HashMap<Long, Employee> db = new HashMap<Long, Employee>();

    static {
        db.put(1L, new Employee(1L, "Alex", "Gussin", "alex.gussin@gmail.com"));
        db.put(2L, new Employee(2L, "Brian", "Schultz", "brianchultz1@gmail.com"));
    }

    public Employee getEmployeeById(long id) {
        return db.get(id);
    }

    public String getEmailById(long id) { return db.get(id).getEmail(); }
}
