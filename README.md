# [RESTful.Intro](https://spring.io/guides/tutorials/rest/)

1) Эндпойинт помимо основной информации должен возвращать и метаинформацию, представленную
Link'ами, которые возвращает ссылки на URI. Для реализации этой цели в spring сущность дополнительно 
оборачивается EntityModel, в нем задаются ссылки:

````
@RestController
public EmployeeRestController {
...
    @PostMapping("/save")
    public EntityModel<EmployeeDtoOut> saveEmployee(@RequestBody EmployeeDtoIn employeeDtoIn) {
        var res = service.save(employeeDtoIn);
        return EntityModel.of(res,
            linkTo(methodOn(EmployeeRestController.class).getById(res.getId())).withRel("getById")
        );
    }
    
    @GetMapping("/getById/{id}")
    public EmployeeDtoOut getById(@PathVariable int id) {
        var res = service.getById(id);
        return res;
    }
...   
}
````

Такой подход приводит к копипасту, поэтому пишется маппер, в котором инкапсулирована операция обертывания.
Маппер должен реализовать интерфейс RepresentationModelAssembler<T,K>: методы toModel() и toCollectionModel()(необязательно).

````
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
````
После написания->заинжектить его в контроллер и использовать.

2) Правильные ответы. Подразумевается использование ResponseEntity(), которая инкапсулирует
подробное создание ответа с заголовками, телом и статусом.

Прошлый пример(переделан)
````
...
    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDtoIn employeeDtoIn) {
        var res = assembler.toModel(service.save(employeeDtoIn));
        return ResponseEntity
                .created(res.getRequiredLink("getById").toUri())
                .body(res);
    }
...
````
created(res.getRequiredLink("getById").toUri()) - вернуть код 201 и ссылку на созданный ресурс.
По протоколу HTTP запрос 201 должен возвращать ссылку на созданный ресурс, обьявленную в заголовке Location.

3) Состояния.