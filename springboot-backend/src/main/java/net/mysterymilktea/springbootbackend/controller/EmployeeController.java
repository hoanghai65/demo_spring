package net.mysterymilktea.springbootbackend.controller;

import net.mysterymilktea.springbootbackend.exception.ResourceNotFoundException;
import net.mysterymilktea.springbootbackend.model.Employee;
import net.mysterymilktea.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    //build get all list employee
    @GetMapping("/all")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll(Sort.by("id").descending());
    }

    //built create employee
    @PostMapping("/create")
    public void createEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
    }

    //build get employee by id
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exit with id = " + id));
        return ResponseEntity.ok(employee);
    }
    //build update employee

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee otherEmployee) {
//        return ResponseEntity.ok(employeeRepository.findById(id)
//                .map(employee -> {
//                    employee.setFirstName(otherEmployee.getFirstName());
//                    employee.setLastName(otherEmployee.getLastName());
//                    return employeeRepository.save(employee);
//                })
//                .orElseGet(() -> {
//                    otherEmployee.setId(id);
//                    return employeeRepository.save(otherEmployee);
//                }));
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exit with id = " + id));
        updateEmployee.setFirstName(otherEmployee.getFirstName());
        updateEmployee.setLastName(otherEmployee.getLastName());
        updateEmployee.setEmailId(otherEmployee.getEmailId());
        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

    //built delete employee
    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exit with id = " + id));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
