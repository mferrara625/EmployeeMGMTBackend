package com.emplify.ema.controllers;

import com.emplify.ema.models.Employee;
import com.emplify.ema.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
//@Secured({
//        "ROLE_USER",
//}
//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee newEmployee){
       return repository.save(newEmployee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Employee getOneById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee update){
        Employee current = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(update.getFirstName() != null){
            current.setFirstName(update.getFirstName());
        }
        if(update.getLastName() != null){
            current.setLastName(update.getLastName());
        }
        if(update.getDepartment() != null){
            current.setDepartment(update.getDepartment());
        }
        if(update.getSalary() != null){
            current.setSalary(update.getSalary());
        }

        return repository.save(current);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
        Employee toDelete = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.deleteById(id);

        return toDelete.getFirstName() + " " + toDelete.getLastName() + " was removed from the system";
    }
}
