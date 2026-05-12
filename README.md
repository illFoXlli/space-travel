# SpaceTravel

Навчальний Java-проєкт для компанії SpaceTravel. Проєкт демонструє роботу з `Hibernate`, `Flyway` та `H2` для моделювання перевезень пасажирів між планетами.

## Реалізовано

- Gradle-проєкт з підключеними залежностями `Hibernate`, `Flyway`, `H2`
- Міграція `V1__create_db.sql` для створення таблиць `client`, `planet`, `ticket`
- Міграція `V2__populate_db.sql` для наповнення БД тестовими даними
- Hibernate-сутності:
  - `Client`
  - `Planet`
- DAO-рівень:
  - `ClientDaoService` / `ClientDaoServiceImpl`
  - `PlanetDaoService` / `PlanetDaoServiceImpl`
- Service-рівень:
  - `ClientService` / `ClientServiceImpl`
  - `PlanetService` / `PlanetServiceImpl`
- Валідація даних у service-рівні
- Обробка помилок транзакцій з `rollback()` у DAO-рівні
- JUnit-тести для перевірки CRUD-операцій і базової валідації
- Тестовий запуск через `Main`, який демонструє create/read/update/delete для `Client` і `Planet`

## Структура проєкту

- `config` - конфігурація Hibernate та ініціалізація Flyway
- `entity` - Hibernate-сутності
- `dao` - робота з БД через Hibernate
- `service` - бізнес-логіка та валідація
- `test` - JUnit-тести

## Структура БД

### `client`
- `id` - `BIGINT`, primary key, auto increment
- `name` - `VARCHAR(200)`, not null

### `planet`
- `id` - `VARCHAR(20)`, primary key
- `name` - `VARCHAR(500)`, not null

Додаткові обмеження:
- `planet.id` може містити тільки великі латинські літери та цифри
- `from_planet_id` і `to_planet_id` у `ticket` не можуть бути однаковими

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

### 1. Запуск тестів

```bash
./gradlew test
```

Тести перевіряють:
- створення, читання, оновлення і видалення `Client`
- створення, читання, оновлення і видалення `Planet`
- валідацію імені `Client`
- валідацію `Planet.id`

Щоб запустити один конкретний тестовий клас:

```bash
./gradlew test --tests "com.fox.service.ClientServiceImplTest"
```

```bash
./gradlew test --tests "com.fox.service.PlanetServiceImplTest"
```

### 2. Запуск застосунку

```bash
./gradlew run
```

Під час запуску:
- Flyway перевіряє та застосовує міграції
- Hibernate підключається до локальної H2-бази
- У `Main` створюються DAO та service-обʼєкти, після чого виконується демонстрація CRUD-операцій

## Файли конфігурації

- Hibernate: [src/main/resources/hibernate.cfg.xml](/Users/denysrud/Документы local/Обучения/GoIt/space-travel/src/main/resources/hibernate.cfg.xml)
- Міграції: [src/main/resources/db/migration](/Users/denysrud/Документы local/Обучения/GoIt/space-travel/src/main/resources/db/migration:1)

## Репозиторій

[GitHub repository](https://github.com/illFoXlli/space-travel)


