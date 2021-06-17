package ru.loolzaaa.springdatajdbcexample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Getter // Lombok генерирует геттеры
@Setter // Lombok генерирует сеттеры
@NoArgsConstructor // Lombok генерирует конструктор по умолчанию
@Table("OTO_ENTITIES") // Указание имение таблицы в случае если имя класса с ним не совпадает
public class OneToOneEntity {

    // Spring Data JDBC используется ID для идентификации сущностей. Поле почти всегда обязательно.
    @Id
    private Long id;
    private String text;

    // Отсутствует поле, являющееся внешним ключом для связи с таблицей ENTITIES!

    // Будет использован данный конструктор, не смотря на наличие конструктора по умолчанию
    // Стандартный приоритет использования конструкторов:
    //      1. Конструктор по умолчанию
    //      2. ЕДИНСТВЕННЫЙ конструктор с аргументами
    //      3. Если конструкторов больше одного, то используется тот, у которого имеется аннотация ниже
    @PersistenceConstructor
    public OneToOneEntity(Long id, String text) {
        this.id = id;
        this.text = text;
    }
}
