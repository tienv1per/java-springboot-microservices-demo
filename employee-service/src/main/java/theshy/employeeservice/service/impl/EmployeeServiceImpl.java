package theshy.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import theshy.employeeservice.dto.APIResponseDTO;
import theshy.employeeservice.dto.DepartmentDTO;
import theshy.employeeservice.dto.EmployeeDTO;
import theshy.employeeservice.entity.Employee;
import theshy.employeeservice.repository.EmployeeRepository;
import theshy.employeeservice.service.APIClient;
import theshy.employeeservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private APIClient apiClient;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail(),
                employeeDTO.getDepartmentCode()
        );
        employeeRepository.save(employee);
        EmployeeDTO savedEmployeeDTO = new EmployeeDTO(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail(),
                employeeDTO.getDepartmentCode()
        );
        return savedEmployeeDTO;
    }

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();

        // make a REST API call and return DepartmentDTO in response
//        ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDTO.class);
//
//        DepartmentDTO departmentDTO = responseEntity.getBody();

        // use WebClient to make API request to Department Service
        DepartmentDTO departmentDTO = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDTO.class)
                .block();

        // DepartmentDTO departmentDTO = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(employeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);

        return apiResponseDTO;
    }

    public APIResponseDTO getDefaultDepartment(Long id, Exception exception){
        Employee employee = employeeRepository.findById(id).get();

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentName("R&D Department");
        departmentDTO.setDepartmentCode("RD001");
        departmentDTO.setDepartmentDescription("Research and Development Department");

        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(employeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);

        return apiResponseDTO;
    }
}
