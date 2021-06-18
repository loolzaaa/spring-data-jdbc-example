package ru.loolzaaa.springdatajdbcexample.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Set;

// Domain Driven Design and Relational Databases
// https://docs.spring.io/spring-data/jdbc/docs/2.2.1/reference/html/#jdbc.domain-driven-design

// Entity = сущность
// Repository = репозиторий
// Aggregate = агрегация
// Aggregate root = корень агрегации

@Getter // Lombok генерирует геттеры
@Setter // Lombok генерирует сеттеры
@NoArgsConstructor // Lombok генерирует конструктор по умолчанию
@Table("ENTITIES") // Указание имение таблицы в случае если имя класса с ним не совпадает
public class Entity {

    /*
        Типы поддерживаемых свойств:
        - Все примитивные типы и их объектные варианты (int, float, Integer, Float и т.д.)
        - Enum мапиться по имени
        - String
        - java.util.Date, java.time.LocalDate, java.time.LocalDateTime и java.time.LocalTime
        - Массивы или коллекции свойств указанных выше могут быть замаплены к колонкам массивного типа
            если база данных поддерживает их
        - Все, которые поддерживает драйвер базы данных
        - Ссылки на другие сущности. Раасматриваются как one-to-one отношения, либо как встроенный (embedded) тип.
            Для one-to-one или embedded идентификатор опционален.
            От таблицы ссылающейся сущности ожидается наличие доп. колонки с именем таблицы ссылаемой сущности.
            Данное поведение может быть изменено: NamingStrategy или @MappedCollection.
        - Set<сущность> рассматривается как one-to-many отношение.
            От таблицы ссылающейся сущности ожидается наличие доп. колонки с именем таблицы ссылаемой сущности.
            Данное поведение может быть изменено: NamingStrategy или @MappedCollection.
        - Map<просто тип, сущность> рассматривается как уточненное one-to-many отношение.
            От таблицы ссылающейся сущности ожидается наличие двух дополнительных колонок:
                * с именем таблицы ссылаемой сущности для внешнего ключа;
                * с тем же именем и дополнительный суффиксом _key для маппинга.
            Данное поведение может быть изменено: NamingStrategy или @MappedCollection.
        - List<сущность> мапится также как Map<просто тип, сущность>

        Обработка сущностей ограничена и основана на идее aggregate roots. Если вы ссылаетесь на какую-либо
            сущность, то данная сущность (ссылающаяся) является, по определению частью ссылаемой сущности.
            Так, если вы удалие ссылаемую сущность, то удаляться все ссылающиеся на нее сущности.
            Так работают отношения one-to-one и one-to-many.

        Если у вас имеются many-to-one или many-to-many отношения, то вы, по определению, имеете дело
            с двумя отдельными aggregate. Ссылки между ними должны быть закодированы как простые ID.
     */

    // Spring Data JDBC используется ID для идентификации сущностей. Поле почти всегда обязательно.
    @Id
    private Long id;

    @Column("ENTITY_NUMBER") // Указание имение колонки в случае если имя поля с ним не совпадает
    private String number;

    // Данный тип поля должен рассматриваться как ссылка на другую сущность (one-to-tone).
    // Однако данное поведение изменяется при наличии специального конвертера: config.EntityConfig
    private EntitySettings settings;

    // Встроенные (embedded) сущности подменяются при маппинге полями этой сущности для той же таблицы.
    // У встроенных сущностей два типа поведения:
    //      - onEmpty = USE_NULL >>> Nullable - данное поле целиком = null в случае
    //          если все поля встроенной сущности = null
    //      - onEmpty = USE_EMPTY >>> Empty - экземпляр встроенной сущности создается из
    //          конструктора по умолчанию. Имеющиеся поля назначаются через сеттеры.
    @Embedded.Nullable
    private EmbeddedEntity embeddedEntity;

    // Данный тип поля должен рассматриваться как ссылка на OneToOneEntity сущность (one-to-tone).
    // При это ожидается, что таблица, содержащая OneToOneEntity сущность будет иметь
    //  дополнительную колонку с названием сущности, на которую ссылается: ENTITY.
    private OneToOneEntity oneToOneEntity;

    // Данный тип поля рассматривается как уточненное one-to-many.
    // Одна Entity сущность содержит много OneToManyEntity сущностей.
    // При этом колонка для внешнего ключа на ссылаемую сущность должна иметь имя ENTITY_ID,
    //  а колонка для порядка элементов должна иметь имя OTM_ENTITY_KEY,
    //  т.к. имеется соответствуюющая аннотация.
    @MappedCollection(idColumn = "ENTITY_ID", keyColumn = "OTM_ENTITY_KEY") // Default keyColumn = ENTITIES_KEY !? NEED HELP!
    private List<OneToManyEntity> oneToManyEntities;

    /* Данный тип поля рассматривается как one-to-many отношение.
       Одна Entity сущность содержит много ManyToManyEntityReference сущностей.
       При этом колонка для внешнего ключа на ссылаемую сущность должна иметь имя ENTITY_ID,
        т.к. имеется соответствуюющая аннотация.

       Однако в данном примере мы хотим показать возможность many-to-many отношения ENTITY и ManyToManyEntity
       через простые идентификаторы сущностей как нам рекомендует документацию, а также DDD.

       Так, класс ManyToManyEntityReference по сути является контейнером для хранения
       одного из вариантов many-to-many отношения. Если взглянуть в файл schema.sql, то можно заметить,
       что таблица (ENTITY_MTMENTITY) используемая для маппинга ManyToManyEntityReference содержит два поля:
            - ENTITY_ID;
            - MTMENTITY_ID.
       При этом в самом классе ENTITY_ID отсутствует, т.к. является необязательным.
       По сути каждая строка таблицы ENTITY_MTMENTITY обозначает соответствие
        идентификатора ENTITY идентификатору ManyToManyEntity.

       Таким образом, при получении какой-либо конкретной сущности ENTITY,
        мы получим множество идентификаторов ManyToManyEntity, которые ссылаются на данный ENTITY.
       Верно и обратное (см. model.ManyToManyEntity), при получении какой-либо конкретной сущности ManyToManyEntity,
        мы получим множество идентификаторов ENTITY, которые ссылаются на данный ManyToManyEntity.

       Подобный подход реализуется по причине идеи aggregate root.
       В данном случае ENTITY и ManyToManyEntity - два разных aggregate.
     */
    @MappedCollection(idColumn = "ENTITY_ID")
    private Set<ManyToManyEntityReference> manyToManyEntityReferences;

    @Builder
    public Entity(Long id, String number, EntitySettings settings, EmbeddedEntity embeddedEntity) {
        this.id = id;
        this.number = number;
        this.settings = settings;
        this.embeddedEntity = embeddedEntity;
    }
}
