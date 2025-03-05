package school21.spring.service.config;

import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class TestApplicationConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .build();
    }
    @Bean
    public UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate() {
        return new UsersRepositoryJdbcTemplateImpl(dataSource());
    }
    @Bean
    public UsersRepositoryJdbcImpl usersRepositoryJdbc() {
        return new UsersRepositoryJdbcImpl(dataSource());
    }
    @Bean
    public UsersService usersServiceImpl() {
        return new UsersServiceImpl(usersRepositoryJdbc());
    }
    @Bean
    public UsersService usersServiceTemplate() {
        return new UsersServiceImpl(usersRepositoryJdbcTemplate());
    }
}

