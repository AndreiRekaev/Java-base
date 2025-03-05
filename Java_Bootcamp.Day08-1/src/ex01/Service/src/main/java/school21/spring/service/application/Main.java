package school21.spring.service.application;

import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        createTable(context);
        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

        System.out.println(usersRepositoryJdbc.findAll());
        System.out.println(usersRepositoryJdbcTemplate.findAll());
        Long id = 1L;
        for (int i = 0; i < 5; i++) {
            usersRepositoryJdbc.save(new User(id, "user" + id++ + "@Jdbc.ru"));
            usersRepositoryJdbcTemplate.save(new User(id, "user" + id++ + "@JdbcTemplate.ru"));
        }
        System.out.println("(JDBC)   Show all users: " + usersRepositoryJdbc.findAll() + "\n");
        System.out.println("(SPRING) Show all users: " + usersRepositoryJdbcTemplate.findAll());
        System.out.println("________________________________________________________________");
        usersRepositoryJdbc.delete(1L);
        usersRepositoryJdbcTemplate.delete(2L);
        System.out.println("(JDBC)   After delete User with id = 1: " + usersRepositoryJdbc.findAll() + "\n");
        System.out.println("(Spring) After delete User with id = 2: " + usersRepositoryJdbcTemplate.findAll());
        System.out.println("________________________________________________________________");

        User upDate = usersRepositoryJdbc.findById(3L);
        User upDate2 = usersRepositoryJdbcTemplate.findById(4L);
        upDate.setEmail("Updated@Jdbc.ru");
        upDate2.setEmail("Updated@JdbcTemplate.ru");
        usersRepositoryJdbc.update(upDate);
        usersRepositoryJdbcTemplate.update(upDate2);
        System.out.println("(JDBC)    All users after update user with id = 3: " + usersRepositoryJdbc.findAll() + "\n");
        System.out.println("(Spring)  All users after update user with id = 4: " + usersRepositoryJdbcTemplate.findAll());
        System.out.println("________________________________________________________________");

        System.out.println("(JDBC)   Find user with email = Updated@Jdbc.ru: " + usersRepositoryJdbc.findByEmail("Updated@Jdbc.ru") + "\n");
        System.out.println("(Spring) Find user with email = Updated@JdbcTemplate.ru: " + usersRepositoryJdbcTemplate.findByEmail("Updated@JdbcTemplate.ru"));
        System.out.println("________________________________________________________________");
        System.out.println("(JDBC)   Try to find user by non-existent email " + usersRepositoryJdbc.findByEmail("SOMETHING EMPTY") + "\n");
        System.out.println("(Spring) Try to find user by non-existent email " + usersRepositoryJdbcTemplate.findByEmail("SOMETHING EMPTY"));
        System.out.println("________________________________________________________________");
        User noName = new User(150L, "NoName@NoName.ru");
        User noName2 = new User(1500L, "NoName@NoName.ru");
        usersRepositoryJdbc.update(noName);
        usersRepositoryJdbc.delete(150L);
        usersRepositoryJdbcTemplate.update(noName2);
        usersRepositoryJdbcTemplate.delete(1500L);
    }

    private static void createTable(ApplicationContext context) {
        DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("drop schema if exists Email cascade;");
            st.executeUpdate("create schema if not exists Email;");
            st.executeUpdate("create table if not exists Email.User "
                    + "(id integer not null, email varchar(50) not null);");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}