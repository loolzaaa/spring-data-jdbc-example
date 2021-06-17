package ru.loolzaaa.springdatajdbcexample.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter // Lombok генерирует геттеры
@Setter // Lombok генерирует сеттеры
@Table("OTM_ENTITIES") // Указание имение таблицы в случае если имя класса с ним не совпадает
public class OneToManyEntity {

    // Spring Data JDBC используется ID для идентификации сущностей. Поле почти всегда обязательно.
    @Id
    private Long id;

    // Объектные типы данных потенциально позволяют держать null,
    // что больше подходит для таблиц, колонки которых могут содержать null даже для простых типов данных.
    private Integer value;

    // Внешний ключ для связи с таблицей ENTITIES
    // БУДЕТ ПРИСУТСТВОВАТЬ в объекте, что не является обязательным!
    private Long entityId;

    // Конструктор по умолчанию отсутствует

    // @PersistenceConstructor - необязательно, т.к. в отсутствии конструктора по умолчанию
    //      используется единственный конструктор с аргументами.
    // Стандартный приоритет использования конструкторов:
    //      1. Конструктор по умолчанию
    //      2. ЕДИНСТВЕННЫЙ конструктор с аргументами
    //      3. Если конструкторов больше одного, то используется тот, у которого имеется аннотация ниже
    public OneToManyEntity(Long id, Integer value, Long entityId) {
        this.id = id;
        this.value = value;
        this.entityId = entityId;
    }
}
