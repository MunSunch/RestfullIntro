package com.munsun.hateoas_spring.hateoasspring.mapping;

import com.munsun.hateoas_spring.hateoasspring.dto.EmployeeDtoIn;
import com.munsun.hateoas_spring.hateoasspring.dto.EmployeeDtoOut;
import com.munsun.hateoas_spring.hateoasspring.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    private AccountMapper accountMapper;

    @Autowired
    public EmployeeMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public EmployeeDtoOut toDto(Employee employee) {
        EmployeeDtoOut employeeDtoOut = new EmployeeDtoOut();
            employeeDtoOut.setId(employee.getId());
            employeeDtoOut.setName(employee.getName());
            employeeDtoOut.setAccount(accountMapper.toDto(employee.getAccount()));
        return employeeDtoOut;
    }

    public Employee toEntity(EmployeeDtoIn employeeDtoIn) {
        Employee employee = new Employee();
            employee.setName(employeeDtoIn.getName());
            employee.setAccount(accountMapper.toEntity(employeeDtoIn.getAccount()));
        return employee;
    }
}
