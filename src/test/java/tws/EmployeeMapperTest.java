package tws;

import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.test.context.junit4.SpringRunner;

//package java_persistence_practice_mybatis;

//import org.junit.Assert;
import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@MybatisTest
public class EmployeeMapperTest {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testSql() {
        //given
        jdbcTemplate.execute("insert into employee values('001','cgg',20)");
        //when
        List<Employee> list = employeeMapper.selectAll();
        //then
        assertEquals("001",list.get(0).getId());
    }
}
