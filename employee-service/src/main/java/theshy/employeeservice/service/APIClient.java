package theshy.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import theshy.employeeservice.dto.DepartmentDTO;

// OpenFeign auto create implementation for this interface => no need to rewrite
// @FeignClient(url = "http://localhost:8080/", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    // build get department rest API
    @GetMapping("api/departments/{departmentCode}")
    DepartmentDTO getDepartment(@PathVariable String departmentCode);
}
