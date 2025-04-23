## Spring Boot + Hibernate CRUD Project
### Описание проекта
Проект реализует простое CRUD-приложение с использованием Spring Boot и Hibernate. Включает:

Модель данных Product

Репозиторий с ручной реализацией Hibernate API

Сервисный слой

Интеграционные и unit-тесты

Запуск проекта
Требования
Java 17+

Maven 3.8+

(Опционально) PostgreSQL для production

Запуск приложения
bash
mvn spring-boot:run
Приложение будет доступно на http://localhost:8080

Запуск тестов
bash
mvn test
Зависимости проекта
Основные зависимости (указаны в pom.xml):

Spring Boot Starter Web

Spring Boot Starter Data JPA

Hibernate Core

H2 Database (для разработки)

PostgreSQL Driver (для production)

Lombok

JUnit 5 + Mockito для тестирования

Настройки Hibernate
Конфигурация Hibernate задается в src/main/resources/hibernate.cfg.xml:

xml
<property name="hibernate.connection.driver_class">org.h2.Driver</property>
<property name="hibernate.connection.url">jdbc:h2:mem:testdb</property>
<property name="hibernate.connection.username">sa</property>
<property name="hibernate.connection.password"></property>
<property name="hibernate.dialect">org.hibernate.dialect.H2Dialect</property>
<property name="hibernate.show_sql">true</property>
<property name="hibernate.format_sql">true</property>
<property name="hibernate.hbm2ddl.auto">update</property>
Дополнительные настройки в application.properties:

properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
Переключение на PostgreSQL
Добавьте зависимость в pom.xml:

xml
<dependency>
<groupId>org.postgresql</groupId>
<artifactId>postgresql</artifactId>
<scope>runtime</scope>
</dependency>
Измените настройки в hibernate.cfg.xml:

xml
<property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
<property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/your_database</property>
<property name="hibernate.connection.username">your_username</property>
<property name="hibernate.connection.password">your_password</property>
<property name="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</property>
(Опционально) Создайте файл application-prod.properties:

properties
spring.profiles.active=prod
spring.jpa.hibernate.ddl-auto=validate
Структура проекта
src/
├── main/
│   ├── java/
│   │   └── org/example/
│   │       ├── config/       # Конфигурационные классы
│   │       ├── model/        # Сущности
│   │       ├── repository/   # Репозитории
│   │       └── service/      # Сервисы
│   └── resources/
│       ├── hibernate.cfg.xml # Конфиг Hibernate
│       └── application.properties
└── test/                     # Тесты

Доступ к H2 Console
В режиме разработки доступна консоль H2:

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (оставить пустым)