# JDBC Repositories
## Почему Spring Data JDBC
Основной persistence API для реляционных баз данных (далее - БД) в мире Java однозначно JPA, который имеет свой собственный Spring Data модуль.

JPA позволяет сделать множество всего чтобы помочь разработчику. Среди прочего, отслеживание изменений в сущностях, ленивая загрузка сущностей. Позволяет организовать соответствие (to map - мапить) широкого набора объектов к столь же широкому набору схем баз данных.

Это позволяет сделать многие вещи намного проще. Одновременно с этим некоторые простые вещи становятся довольно сложными.

Spring Data JDBC намного проще и основывается на следующих решениях:

- Если вы загружаете сущность - SQL-запрос выполняется. Как только он будет завершен, вы получить полностью загруженную сущность. Никакой ленивой загрузки, никакого кэширования;
- Если вы сохраняете сущность - она будет сохранена. Если нет, то нет. Никакого отслеживания (dirty tracking), никаких сессий;
- Все основывается на очень простом механизме мапинга сущностей к таблицам. Если он вас не устраивает, то вам нужно придумать свою стратегию. Spring Data JDBC предлагает очень ограниченные возможности по настройки стратегии мапинга при помощи аннотаций.

## Domain Driven Design и реляционные базы данных
Все модули Spring Data сонованы на концепциях **repository**, **aggregate** и **aggregate root** из предметно-ориентированного программирования (Domain Driven Design = DDD). Это, возможно, даже более важно для Spring Data JDBC, потому как они, в некоторой степени, противоречат классическим практикам взаимодействия с базами данных.

Aggregate - это группа сущностей, гарантирующая соответствие между ними в пределах любого атомарного изменения. Классический пример: заказ в магазине (Order) и позиции этого заказа (OrderItem). Свойства заказа (например, количество позиций заказа (numberOfItems) соответствует актуальному количеству позиций в заказе) остаются в полном соответствии, не смотря на изменения.

Каждая агрегация (aggregate) имеет только один корень агрегации (aggregate root), который является одной из сущностей агрегации. Манипуляция над агрегацией происходит только через корень агрегации. Это и есть те атомарные изменения, указанные выше.

Repository - это абстракция над хранилищем, которое выглядит как коллекция данных определенного типа. Для Spring Data в целом это означает, что вам необходимо иметь по одному репозиторию для каждого типа данных корня агрегации. Кроме того, для Spring Data JDBC это означает, что сущности доступные из данного корня агрегации считаются частью этого корня агрегации.
