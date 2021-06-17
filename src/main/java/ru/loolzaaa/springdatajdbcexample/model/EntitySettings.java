package ru.loolzaaa.springdatajdbcexample.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Lombok генерирует геттеры
@Setter // Lombok генерирует сеттеры
@NoArgsConstructor // Lombok генерирует конструктор по умолчанию
public class EntitySettings {

    // Не смотря на то, что тип колонки для хранения данных настроек VARCHAR,
    // объект создается благодаря специальному конвертеру: config.EntityConfig

    private int value1;
    private int value2;
    private String stringValue;

    @Builder
    public EntitySettings(int value1, int value2, String stringValue) {
        this.value1 = value1;
        this.value2 = value2;
        this.stringValue = stringValue;
    }
}
