package ru.loolzaaa.springdatajdbcexample.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter // Lombok генерирует геттеры
@Setter // Lombok генерирует сеттеры
@Table("MTM_ENTITIES") // Указание имение таблицы в случае если имя класса с ним не совпадает
public class ManyToManyEntity {
    // Spring Data JDBC используется ID для идентификации сущностей. Поле почти всегда обязательно.
    @Id
    private Long id;
    private String value;

    // Отсутствует реализация контейнера, а также поле, являющееся ссылкой на множество соответствий
    // идентификаторов ManyToManyEntity и Entity
}
