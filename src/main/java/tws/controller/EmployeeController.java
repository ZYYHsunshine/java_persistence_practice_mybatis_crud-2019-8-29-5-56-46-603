package tws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tws.dto.EmployeeDto;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;
import tws.service.EmployeeService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeMapper.selectAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> selectOne(@PathVariable String id) {
        Employee employee = employeeMapper.selectOne(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateOne(
            @PathVariable String id,
            @RequestBody Employee employee
    ) {
        employeeMapper.updateOne(id, employee);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        String id = UUID.randomUUID().toString();
        employee.setId(id);
        employeeMapper.insert(employee);
        return ResponseEntity.created(URI.create("/employees" + id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> DeleteOne(@PathVariable String id) {
        employeeMapper.deleteOne(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable String id) {
        EmployeeDto employeeDto = employeeService.getEmployWithDesc(id);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAll(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int pageSize) {

        return ResponseEntity.ok(employeeService.getEmployees(page,pageSize));
    }
}
