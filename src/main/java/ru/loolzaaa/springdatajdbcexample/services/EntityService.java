package ru.loolzaaa.springdatajdbcexample.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.loolzaaa.springdatajdbcexample.model.EmbeddedEntity;
import ru.loolzaaa.springdatajdbcexample.model.Entity;
import ru.loolzaaa.springdatajdbcexample.model.EntitySettings;
import ru.loolzaaa.springdatajdbcexample.model.SubEmbeddedEntity;
import ru.loolzaaa.springdatajdbcexample.repositories.EntityRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EntityService {

    private final EntityRepository entityRepository;

    public List<Entity> showAll() {
        Iterable<Entity> workplaces = entityRepository.findAll();
        List<Entity> entityList = new ArrayList<>();
        workplaces.forEach(entityList::add);
        return entityList;
    }

    public Entity showById(Long id) {
        return entityRepository.findById(id).orElse(null);
    }

    public Entity saveNewEntityWithDefaultsAndSubNumber(int number) {
        EntitySettings settings = EntitySettings.builder().value1(11).value2(22).stringValue("some_new_text").build();

        EmbeddedEntity embeddedEntity = EmbeddedEntity.builder()
                .name("NEW_EMBEDDED" + number)
                .count(number)
                .subEmbeddedEntity(new SubEmbeddedEntity(number))
                .build();

        Entity entity = Entity.builder()
                .number(String.valueOf(number))
                .settings(settings)
                .embeddedEntity(embeddedEntity)
                .build();

        return entityRepository.save(entity);
    }
}
