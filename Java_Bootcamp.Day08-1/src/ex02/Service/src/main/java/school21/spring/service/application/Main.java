package school21.spring.service.application;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
        System.out.println("/* -------------------------------- JDBCTemplate -------------------------------- */");

        AnnotationConfigApplicationContext  context =  new AnnotationConfigApplicationContext(ApplicationConfig.class);
        createSchemaAndTable(context);
        UsersServiceImpl usersService = context.getBean("userService", UsersServiceImpl.class);
        UsersRepository usersRepository = context.getBean("jdbcTemplateRepository", UsersRepository.class);
        usersService.signUp("Updated@Jdbc.ru");
        usersService.signUp("Updated@JdbcTemplate.ru");
        System.out.println("\n-----------------\n");
        System.out.println(usersRepository.findAll());
        System.out.println("\n-----------------\n");
        System.out.println(usersRepository.findByEmail("Updated@JdbcTemplate.ru").get());
    }

    private static void createSchemaAndTable(AnnotationConfigApplicationContext context) {
        DriverManagerDataSource dataSource = context.getBean("driverManagerDataSource", DriverManagerDataSource.class);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("drop schema if exists email cascade;");
            stmt.executeUpdate("create schema if not exists email;");
            stmt.executeUpdate("create table if not exists email.user "
                    + "(id serial PRIMARY KEY, email varchar(50) UNIQUE NOT NULL, password varchar(50) NOT NULL);");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}


