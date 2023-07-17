package theshy.departmentservice.service;

import theshy.departmentservice.dto.DepartmentDTO;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentByCode(String code);
}
