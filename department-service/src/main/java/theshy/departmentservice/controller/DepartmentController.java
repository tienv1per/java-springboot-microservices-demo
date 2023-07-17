package theshy.departmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import theshy.departmentservice.dto.DepartmentDTO;
import theshy.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    // build save department rest API
    @PostMapping("/")
    // @RequestBody: convert JSON form from request body to Java object
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO savedDepartmentDTO = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartmentDTO, HttpStatus.CREATED);
    }

    // build get department rest API
    @GetMapping("/{departmentCode}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable String departmentCode){
        DepartmentDTO departmentDTO = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }
}
