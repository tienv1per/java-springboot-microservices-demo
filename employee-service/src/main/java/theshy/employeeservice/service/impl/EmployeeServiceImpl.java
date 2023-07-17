package theshy.employeeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import theshy.employeeservice.dto.EmployeeDTO;
import theshy.employeeservice.entity.Employee;
import theshy.employeeservice.repository.EmployeeRepository;
import theshy.employeeservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail()
        );
        employeeRepository.save(employee);
        EmployeeDTO savedEmployeeDTO = new EmployeeDTO(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employee.getLastName(),
                employeeDTO.getEmail()
        );
        return savedEmployeeDTO;
    }
}
