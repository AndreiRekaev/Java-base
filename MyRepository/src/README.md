## My CRUD repository
## Запуск проекта

1. Клонировать репозиторий
2. Собрать проект: `mvn clean install`
3. Запустить тесты: `mvn test`
4. Запустить приложение: `mvn exec:java -Dexec.mainClass="com.example.MainApp"`

## Требования

- Java 17+
- Maven 3.6+

## Структура

- `User` - модель данных
- `UserRepository` - слой доступа к данным (JDBC)
- `UserService` - бизнес-логика
- Тесты:
    - `UserServiceTest` - unit тесты с Mockito
    - `UserRepositoryIT` - интеграционные тесты с H2