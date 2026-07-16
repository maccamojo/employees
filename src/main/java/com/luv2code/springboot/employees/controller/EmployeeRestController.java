package com.luv2code.springboot.employees.controller;

import com.luv2code.springboot.employees.entity.Employee;
import com.luv2code.springboot.employees.request.EmployeeRequest;
import com.luv2code.springboot.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(
        name = "Employee Rest API Endpoints",
        description = "Operations related to employees"
)
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }


    @Operation(                                                      // adds Swagger/OpenAPI documentation for the endpoint
            summary = "Get all employees",
            description = "Retrieve a list of all employees"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }


    @Operation(
            summary = "Fetch single employee",
            description = "Get a single employee from database"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable @Min(value=1) long employeeId) {
        //return employeeService.findById(employeeId);   // IntelliJ code completion. Looks more concise!
        Employee theEmployee = employeeService.findById(employeeId);
        return theEmployee;
    }


    @Operation(
            summary = "Create a new employee",
            description = "Add a new employee to the database"
    )
    @ResponseStatus(HttpStatus.CREATED)   // otherwise, returns a 200 OK
    @PostMapping()
    // @Valid creates the validation on the RequestBody
    // "@Valid @RequestBody" tells Spring to deserialise the JSON body into EmployeeRequest and validate it before the method body runs
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest theEmployee) {
        // return employeeService.save(theEmployee);   // Perplexity suggested this enhancement. More concise!
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }


    @Operation(
            summary = "Update an employee",
            description = "Update the details of a current employee in the database"
    )
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable @Min(value=1) long employeeId,
                                   @Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee dbEmployee = employeeService.update(employeeId, employeeRequest);
        return dbEmployee;
    }


    @Operation(
            summary = "Delete an employee",
            description = "Remove an employee from the database"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable @Min(value=1) long employeeId) {
        employeeService.deleteById(employeeId);
    }

}
