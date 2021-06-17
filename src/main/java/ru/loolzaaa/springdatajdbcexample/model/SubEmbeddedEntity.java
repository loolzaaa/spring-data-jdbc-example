package ru.loolzaaa.springdatajdbcexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Lombok генерирует геттеры
@Setter // Lombok генерирует сеттеры
@NoArgsConstructor // Lombok генерирует конструктор по умолчанию
@AllArgsConstructor // Lombok генерирует конструктор со всеми полями в качестве аргументов
public class SubEmbeddedEntity {

    // Имя таблицы не указывается, т.к. embedded сущность расыкрывается в колонки по именам своих полей
    // Данная сущность раскрывается в колонку sub_number таблицы ENTITIES

    // Поле ID необязательно для embedded сущностей

    // Объектные типы данных потенциально позволяют держать null,
    // что больше подходит для таблиц, колонки которых могут содержать null даже для простых типов данных.
    private Integer subNumber;
}
