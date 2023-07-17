package theshy.employeeservice.service;

import theshy.employeeservice.dto.APIResponseDTO;
import theshy.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    APIResponseDTO getEmployeeById(Long id);
}
