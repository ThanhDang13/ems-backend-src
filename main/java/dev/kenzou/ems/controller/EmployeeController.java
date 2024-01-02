package dev.kenzou.ems.controller;

import dev.kenzou.ems.dto.EmployeeDTO;
import dev.kenzou.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService service;
    @PostMapping()
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO payload) {
        return new ResponseEntity<EmployeeDTO>(service.create(payload), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<List<EmployeeDTO>>(service.getAll(), HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
        return new ResponseEntity<EmployeeDTO>(service.getById(id), HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long id ,@RequestBody EmployeeDTO payload) {
        return new ResponseEntity<EmployeeDTO>(service.update(id, payload), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long id) {
        return new ResponseEntity<EmployeeDTO>(service.delete(id), HttpStatus.OK);
    }
}
