package com.munsun.hateoas_spring.hateoasspring.web;

import com.munsun.hateoas_spring.hateoasspring.dto.EmployeeDtoIn;
import com.munsun.hateoas_spring.hateoasspring.dto.EmployeeDtoOut;
import com.munsun.hateoas_spring.hateoasspring.service.EmployeeModelAssembler;
import com.munsun.hateoas_spring.hateoasspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {
    private final EmployeeService service;
    private final EmployeeModelAssembler assembler;

    @Autowired
    public EmployeeRestController(EmployeeService service, EmployeeModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDtoIn employeeDtoIn) {
        var res = assembler.toModel(service.save(employeeDtoIn));
        return ResponseEntity
                .created(res.getRequiredLink("getById").toUri())
                .body(res);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<EntityModel<EmployeeDtoOut>> getById(@PathVariable int id) {
        var res = assembler.toModel(service.getById(id));
        return ResponseEntity
                .ok(res);
//        return EntityModel.of(res,
//                linkTo(methodOn(EmployeeRestController.class).getAll()).withRel("getAll"));
    }

//    @GetMapping("/getAll")
//    public CollectionModel<EntityModel<EmployeeDtoOut>> getAll() {
//        var employees = service.getAll();
//        List<EntityModel<EmployeeDtoOut>> models = employees.stream()
//                .map(emp -> EntityModel.of(emp,
//                        linkTo(methodOn(EmployeeRestController.class).getById(emp.getId()))
//                                .withRel("getById")))
//                .collect(Collectors.toList());
//        return CollectionModel.of(models,
//                linkTo(methodOn(EmployeeRestController.class).getAll()).withRel("all"));
//    }

    @GetMapping("/getAll")
    public CollectionModel<EntityModel<EmployeeDtoOut>> getAll() {
        var employees = service.getAll();
        return assembler.toCollectionModel(employees);
    }
}
