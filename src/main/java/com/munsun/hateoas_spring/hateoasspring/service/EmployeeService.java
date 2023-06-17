package com.munsun.hateoas_spring.hateoasspring.service;

import com.munsun.hateoas_spring.hateoasspring.dto.EmployeeDtoIn;
import com.munsun.hateoas_spring.hateoasspring.dto.EmployeeDtoOut;
import com.munsun.hateoas_spring.hateoasspring.mapping.EmployeeMapper;
import com.munsun.hateoas_spring.hateoasspring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public EmployeeDtoOut save(EmployeeDtoIn employeeDtoIn) {
        var res = employeeRepository.save(mapper.toEntity(employeeDtoIn));
        return mapper.toDto(res);
    }

    public EmployeeDtoOut getById(int id) {
        var res = employeeRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return mapper.toDto(res);
    }

    public List<EmployeeDtoOut> getAll() {
        var res = employeeRepository.findAll();
        return res.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
