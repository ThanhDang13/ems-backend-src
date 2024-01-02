package dev.kenzou.ems.service;

import dev.kenzou.ems.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    public EmployeeDTO create(EmployeeDTO payload);
    public List<EmployeeDTO> getAll();
    public EmployeeDTO getById(Long id);
    public EmployeeDTO update(Long id, EmployeeDTO payload);
    public EmployeeDTO delete(Long id);
}
