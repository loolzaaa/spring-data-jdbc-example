package ru.loolzaaa.springdatajdbcexample.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Embedded;

@Getter // Lombok генерирует геттеры
@Setter // Lombok генерирует сеттеры
@NoArgsConstructor // Lombok генерирует конструктор по умолчанию
public class EmbeddedEntity {

    // Имя таблицы не указывается, т.к. embedded сущность расыкрывается в колонки по именам своих полей
    // Данная сущность раскрывается в колонки name и count таблицы ENTITIES

    // Поле ID необязательно для embedded сущностей

    private String name;

    // Объектные типы данных потенциально позволяют держать null,
    // что больше подходит для таблиц, колонки которых могут содержать null даже для простых типов данных.
    private Integer count;

    // Встроенные (embedded) сущности подменяются при маппинге полями этой сущности для той же таблицы.
    // У встроенных сущностей два типа поведения:
    //      - onEmpty = USE_NULL >>> Nullable - данное поле целиком = null в случае
    //          если все поля встроенной сущности = null
    //      - onEmpty = USE_EMPTY >>> Empty - экземпляр встроенной сущности создается из
    //          конструктора по умолчанию. Имеющиеся поля назначаются через сеттеры.
    //
    // Данная сущность является встроенный внутри другой встроенной сущности.
    // При этом она раскрывается в колонки по именам своих полей до ближайшей таблицы: ENTITIES.
    @Embedded.Nullable
    private SubEmbeddedEntity subEmbeddedEntity;

    @Builder
    public EmbeddedEntity(String name, int count, SubEmbeddedEntity subEmbeddedEntity) {
        this.name = name;
        this.count = count;
        this.subEmbeddedEntity = subEmbeddedEntity;
    }
}
