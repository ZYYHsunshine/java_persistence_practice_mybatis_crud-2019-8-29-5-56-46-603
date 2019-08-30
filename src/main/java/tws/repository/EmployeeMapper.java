package tws.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tws.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> selectAll();

    Employee selectOne(@Param("id") String id);

    void insert(@Param("employee") Employee employee);

    void updateOne(
            @Param("id") String id,
            @Param("employee") Employee employee
    );

    void deleteOne(@Param("id")String id);

    List<Employee> selectAlls(@Param("offset") int offset,@Param("pageSize") int pageSize);

}