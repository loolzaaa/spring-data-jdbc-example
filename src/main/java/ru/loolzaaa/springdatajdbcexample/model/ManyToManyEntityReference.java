package ru.loolzaaa.springdatajdbcexample.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter // Lombok генерирует геттеры
@Setter // Lombok генерирует сеттеры
@Table("ENTITY_MTMENTITY") // Указание имение таблицы в случае если имя класса с ним не совпадает
public class ManyToManyEntityReference {

    // Отсутствует поле, являющееся внешним ключом для связи с таблицей ENTITIES!

    @Column("MTMENTITY_ID") // Указание имение колонки в случае если имя поля с ним не совпадает
    private Long mtmEntityId;
}
