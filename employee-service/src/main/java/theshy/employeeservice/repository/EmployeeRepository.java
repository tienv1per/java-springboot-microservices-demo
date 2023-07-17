package theshy.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import theshy.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
