# SpaceTravel

Навчальний Java-проєкт для компанії SpaceTravel. Проєкт демонструє роботу з `Hibernate`, `Flyway` та `H2` для моделювання перевезень пасажирів між планетами.

## Реалізовано

- Gradle-проєкт з підключеними залежностями `Hibernate`, `Flyway`, `H2`
- Міграція `V1__create_db.sql` для створення таблиць `client`, `planet`, `ticket`
- Міграція `V2__populate_db.sql` для наповнення БД тестовими даними
- Hibernate-сутності:
  - `Client`
  - `Planet`
- CRUD-сервіси:
  - `ClientCrudService`
  - `PlanetCrudService`
- JUnit-тести для перевірки CRUD-операцій
- Тестовий запуск через `Main`, який виконує create/read/update/delete для `Client` і `Planet`

## Структура БД

### `client`
- `id` - `BIGINT`, primary key, auto increment
- `name` - `VARCHAR(200)`, not null

### `planet`
- `id` - `VARCHAR(20)`, primary key
- `name` - `VARCHAR(500)`, not null

### `ticket`
- `id` - `BIGINT`, primary key, auto increment
- `created_at` - `TIMESTAMP`, not null
- `client_id` - foreign key -> `client.id`
- `from_planet_id` - foreign key -> `planet.id`
- `to_planet_id` - foreign key -> `planet.id`

## Технології

- Java
- Gradle
- Hibernate ORM
- Flyway
- H2 Database
- JUnit 5

## Запуск

### 1. Запуск перевірки

```bash
./gradlew test
```

Тести перевіряють:
- створення, читання, оновлення і видалення `Client`
- створення, читання, оновлення і видалення `Planet`

### 2. Запуск застосунку

```bash
./gradlew run
```

Під час запуску:
- Flyway перевіряє та застосовує міграції
- Hibernate підключається до локальної H2-бази
- У `Main` виконується демонстрація CRUD-операцій

## Файли конфігурації

- Hibernate: [src/main/resources/hibernate.cfg.xml](/Users/denysrud/Документы local/Обучения/GoIt/space-travel/src/main/resources/hibernate.cfg.xml)
- Міграції: [src/main/resources/db/migration](/Users/denysrud/Документы local/Обучения/GoIt/space-travel/src/main/resources/db/migration:1)

## Репозиторій

[GitHub repository](https://github.com/illFoXlli/space-travel)

## Що ще можна покращити

- додати окремі автоматичні тести замість перевірки тільки через `Main`
- додати сутність `Ticket`, якщо це знадобиться в наступних завданнях
- посилити SQL-обмеження для `planet.id` відповідно до формату з умови
- прибрати технічні Hibernate warnings, які не ламають запуск, але роблять конфіг охайнішим
