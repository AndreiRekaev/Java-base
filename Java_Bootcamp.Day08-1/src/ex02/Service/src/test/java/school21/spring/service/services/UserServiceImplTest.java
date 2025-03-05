package school21.spring.service.services;

import org.junit.jupiter.api.BeforeAll;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserServiceImplTest {
    private static UsersService usersService;
    private static UsersService usersServiceTemplate;
    private static UsersRepositoryJdbcImpl usersRepositoryJdbc;
    private static UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate;

    @BeforeAll
    public static void before() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        usersService = context.getBean("usersServiceImpl", UsersService.class);
        usersServiceTemplate = context.getBean("usersServiceTemplate", UsersService.class);
        usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepositoryJdbcImpl.class);
        usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepositoryJdbcTemplateImpl.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Updated@Jdbc.ru", "user5@Jdbc.ru", "user9@Jdbc.ru", "user8@JdbcTemplate.ru"})
    public void isSignedUp(String email) {
        usersService.signUp(email);
        System.out.println(usersRepositoryJdbc.findAll());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Updated1@Jdbc.ru", "user6@Jdbc.ru", "user7@Jdbc.ru", "user0@JdbcTemplate.ru"})
    public void isSignedUpTemplate(String email) {
        usersServiceTemplate.signUp(email);
        System.out.println(usersRepositoryJdbcTemplate.findAll());
    }
}