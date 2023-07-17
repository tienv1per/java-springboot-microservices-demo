package theshy.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import theshy.departmentservice.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentCode(String departmentCode);
}
