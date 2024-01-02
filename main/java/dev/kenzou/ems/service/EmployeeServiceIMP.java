package dev.kenzou.ems.service;

import dev.kenzou.ems.dto.EmployeeDTO;
import dev.kenzou.ems.entity.Employee;
import dev.kenzou.ems.exception.ResourceNotFoundException;
import dev.kenzou.ems.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceIMP implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    private final ModelMapper mapper;

    @Override
    public EmployeeDTO create(EmployeeDTO payload) {
        repository.save(mapper.map(payload, Employee.class));
        return payload;
    }
    @Override
    public List<EmployeeDTO> getAll() {
        return mapper.map(repository.findAll(), new TypeToken<List<EmployeeDTO>>(){}.getType());
    }
    @Override
    public EmployeeDTO getById(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee Not Found!")
        ), EmployeeDTO.class);
    }
    @Override
    public EmployeeDTO update(Long id, EmployeeDTO payload) {
        Employee employee = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee Not Found!")
        );
        employee.setFirstName(payload.getFirstName());
        employee.setLastName(payload.getLastName());
        employee.setEmailId(payload.getEmailId());
        repository.save(employee);
        return mapper.map(employee, EmployeeDTO.class);
    }
    @Override
    public EmployeeDTO delete(Long id) {
        EmployeeDTO employeeDTO = mapper.map(repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee Not Found!")
        ), EmployeeDTO.class);
        repository.deleteById(id);
        return employeeDTO;
    }

    public EmployeeServiceIMP() {
        this.mapper = new ModelMapper();
    }
}
