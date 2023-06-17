package com.munsun.hateoas_spring.hateoasspring.service;

import com.munsun.hateoas_spring.hateoasspring.dto.EmployeeDtoOut;
import com.munsun.hateoas_spring.hateoasspring.entity.Employee;
import com.munsun.hateoas_spring.hateoasspring.web.EmployeeRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<EmployeeDtoOut, EntityModel<EmployeeDtoOut>> {
    @Override
    public EntityModel<EmployeeDtoOut> toModel(EmployeeDtoOut entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(EmployeeRestController.class).getById(entity.getId())).withRel("getById"),
                linkTo(methodOn(EmployeeRestController.class).getAll()).withRel("getAll")
        );
    }

    @Override
    public CollectionModel<EntityModel<EmployeeDtoOut>> toCollectionModel(Iterable<? extends EmployeeDtoOut> entities) {
        List<EntityModel<EmployeeDtoOut>> models = new ArrayList<>();
        for(var obj: entities) {
            models.add(EntityModel.of(obj,
                linkTo(methodOn(EmployeeRestController.class).getById(obj.getId())).withRel("getById")));
        }
        return CollectionModel.of(models,
                linkTo(methodOn(EmployeeRestController.class).getAll()).withRel("all"));
    }
}
