# SpaceTravel

Навчальний Java-проєкт для компанії SpaceTravel. Проєкт демонструє роботу з `Hibernate`, `Flyway` та `H2` для моделювання перевезень пасажирів між планетами.

## Реалізовано

- Gradle-проєкт з підключеними залежностями `Hibernate`, `Flyway`, `H2`
- Перевірка якості коду через `Checkstyle` і `Spotless`
- Міграція `V1__create_db.sql` для створення таблиць `client`, `planet`, `ticket`
- Міграція `V2__populate_db.sql` для наповнення БД тестовими даними
- Hibernate-сутності:
  - `Client`
  - `Planet`
  - `Ticket`
- DAO-рівень:
  - `ClientDaoService` / `ClientDaoServiceImpl`
  - `PlanetDaoService` / `PlanetDaoServiceImpl`
  - `TicketDaoService` / `TicketDaoServiceImpl`
- Service-рівень:
  - `ClientService` / `ClientServiceImpl`
  - `PlanetService` / `PlanetServiceImpl`
  - `TicketService` / `TicketServiceImpl`
- Валідація даних у service-рівні
- Обробка помилок транзакцій з `rollback()` у DAO-рівні
- Конфігурація БД через `.env`
- GitHub Actions для запуску перевірок при `push` і `pull_request` у `main`
- JUnit-тести для перевірки CRUD-операцій і базової валідації
- Тестовий запуск через `Main`, який демонструє CRUD для `Client`, `Planet` і `Ticket`

## Структура проєкту

- `config` - конфігурація Hibernate, ініціалізація Flyway, читання env-перемінних
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

### `ticket`
- `id` - `BIGINT`, primary key, auto increment
- `created_at` - `TIMESTAMP`, not null
- `client_id` - foreign key -> `client.id`
- `from_planet_id` - foreign key -> `planet.id`
- `to_planet_id` - foreign key -> `planet.id`

Додаткові обмеження:
- `planet.id` може містити тільки великі латинські літери та цифри
- `from_planet_id` і `to_planet_id` у `ticket` не можуть бути однаковими

У Java-сутності `Ticket` ці звʼязки описані через Hibernate mappings:
- `Client client`
- `Planet fromPlanet`
- `Planet toPlanet`

## Технології

- Java
- Gradle
- Hibernate ORM
- Flyway
- H2 Database
- JUnit 5
- Checkstyle
- Spotless
- GitHub Actions

## Налаштування `.env`

Створи локальний `.env` на основі [.env.example](/Users/denysrud/Документы local/Обучения/GoIt/space-travel/.env.example:1).

У шаблоні зберігаються тільки назви змінних:

```env
DB_DRIVER=
DB_URL=
DB_USER=
DB_PASSWORD=pass
HIBERNATE_SHOW_SQL=
HIBERNATE_FORMAT_SQL=
HIBERNATE_HBM2DDL_AUTO=
```

Для локальної H2-бази можна використати такі значення:

```env
DB_DRIVER=org.h2.Driver
DB_URL=jdbc:h2:./db/space_travel
DB_USER=fox
DB_PASSWORD=pass
HIBERNATE_SHOW_SQL=true
HIBERNATE_FORMAT_SQL=true
HIBERNATE_HBM2DDL_AUTO=validate
```

## Запуск

### 1. Запуск тестів

```bash
./gradlew test
```

Тести перевіряють:
- створення, читання, оновлення і видалення `Client`
- створення, читання, оновлення і видалення `Planet`
- створення, читання, оновлення і видалення `Ticket`
- валідацію імені `Client`
- валідацію `Planet.id`
- заборону створення `Ticket` для `null` або неіснуючого клієнта
- заборону створення `Ticket` для `null` або неіснуючої планети

Щоб запустити один конкретний тестовий клас:

```bash
./gradlew test --tests "com.fox.service.ClientServiceImplTest"
./gradlew test --tests "com.fox.service.PlanetServiceImplTest"
./gradlew test --tests "com.fox.service.TicketServiceImplTest"
```

### 2. Перевірка стилю коду і тестів

```bash
./gradlew check
```

Команда запускає:
- `Spotless`
- `Checkstyle`
- юніт-тести

### 3. Запуск застосунку

```bash
./gradlew run
```

Під час запуску:
- Flyway перевіряє та застосовує міграції
- Hibernate підключається до локальної H2-бази
- у `Main` створюються DAO та service-обʼєкти, після чого виконується демонстрація CRUD-операцій

## CI / GitHub Actions

Workflow знаходиться в:

- [.github/workflows/gradle-tests.yml](/Users/denysrud/Документы local/Обучения/GoIt/space-travel/.github/workflows/gradle-tests.yml:1)

На GitHub запускаються:
- юніт-тести
- `Checkstyle` для `main`
- `Checkstyle` для `test`

## Файли конфігурації

- Env-шаблон: [.env.example](/Users/denysrud/Документы local/Обучения/GoIt/space-travel/.env.example:1)
- Міграції: [src/main/resources/db/migration](/Users/denysrud/Документы local/Обучения/GoIt/space-travel/src/main/resources/db/migration:1)
- GitHub Actions workflow: [.github/workflows/gradle-tests.yml](/Users/denysrud/Документы local/Обучения/GoIt/space-travel/.github/workflows/gradle-tests.yml:1)

## Репозиторій

[GitHub repository](https://github.com/illFoXlli/space-travel)
