package tws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tws.dto.EmployeeDto;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

import java.util.List;

@Service
public class EmployeeService {
   @Autowired
    private EmployeeMapper employeeMapper;

   public EmployeeDto getEmployWithDesc(String id){
       Employee employee = employeeMapper.selectOne(id);

       EmployeeDto employeeDto = new EmployeeDto();

       employeeDto.setId(employee.getId());
       employeeDto.setName(employee.getName());
       employeeDto.setAge(employee.getAge()
       );
       String desc = String.format("name:%s,age:%s",
               employee.getName(),
               employee.getAge());

       employeeDto.setDesc(desc);
       return  employeeDto;
   }

    public List<Employee> getEmployees(int page, int pageSize){
        int offset = (page-1)*pageSize;
        List list = employeeMapper.selectAlls(offset,pageSize);
        return list;
    }

}
