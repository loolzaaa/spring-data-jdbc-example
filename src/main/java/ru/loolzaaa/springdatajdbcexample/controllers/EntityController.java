package ru.loolzaaa.springdatajdbcexample.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.loolzaaa.springdatajdbcexample.model.Entity;
import ru.loolzaaa.springdatajdbcexample.services.EntityService;

import java.util.List;

@AllArgsConstructor
@Controller
public class EntityController {

    private final EntityService entityService;

    @GetMapping("/all")
    @ResponseBody
    public List<Entity> showAllEntities() {
        return entityService.showAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Entity showEntityById(@PathVariable("id") Long id) {
        return entityService.showById(id);
    }

    @GetMapping("/save/{subNumber}")
    public String saveNewEntity(@PathVariable("subNumber") int number) {
        Entity savedEntity = entityService.saveNewEntityWithDefaultsAndSubNumber(number);
        return "redirect:/" + savedEntity.getId();
    }
}
