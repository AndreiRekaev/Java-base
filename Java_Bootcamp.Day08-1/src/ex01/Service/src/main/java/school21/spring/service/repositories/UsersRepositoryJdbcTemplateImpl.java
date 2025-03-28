package school21.spring.service.repositories;

import school21.spring.service.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private NamedParameterJdbcTemplate dataSource;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        User user = dataSource.query("SELECT * FROM email.user WHERE id=:id",
                        new MapSqlParameterSource().addValue("id", id),
                        new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
        return user;
    }

    @Override
    public List findAll() {
        List<User> users = dataSource.query("SELECT * FROM email.user",
                new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? null : users;
    }

    @Override
    public void save(User entity) {
        dataSource.update("INSERT INTO email.user(id, email) VALUES(:id, :email)",
                new MapSqlParameterSource()
                        .addValue("id", entity.getId())
                        .addValue("email", entity.getEmail()));
    }

    @Override
    public void update(User entity) {
        dataSource.update("UPDATE email.user SET email=:email WHERE id=:id",
                new MapSqlParameterSource()
                        .addValue("email", entity.getEmail())
                        .addValue("id", entity.getId()));
    }

    @Override
    public void delete(Long id) {
        dataSource.update("DELETE FROM email.user WHERE id=:id",
                new MapSqlParameterSource()
                        .addValue("id", id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return dataSource.query("SELECT * FROM email.user WHERE email=:email",
                        new MapSqlParameterSource().addValue("email", email),
                        new BeanPropertyRowMapper<>(User.class))
                .stream().findAny();
    }
}
