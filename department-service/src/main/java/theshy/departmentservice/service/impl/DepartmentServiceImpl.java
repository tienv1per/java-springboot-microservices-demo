package theshy.departmentservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import theshy.departmentservice.dto.DepartmentDTO;
import theshy.departmentservice.entity.Department;
import theshy.departmentservice.repository.DepartmentRepository;
import theshy.departmentservice.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository departmentRepository;

//    @Autowired
//    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        // convert department dto to department jpa entity
        Department department = new Department(
                departmentDTO.getId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDescription(),
                departmentDTO.getDepartmentCode()
        );
        departmentRepository.save(department);
        DepartmentDTO savedDepartmentDTO = new DepartmentDTO(
                departmentDTO.getId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return savedDepartmentDTO;
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String code) {
        LOGGER.info("inside getDepartmentByCode() method");

        Department department = departmentRepository.findByDepartmentCode(code);
        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDTO;
    }
}
