package school21.spring.service.services;

import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("userService")
@Getter
public class UsersServiceImpl implements UsersService {
    private static int id;
    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("jdbcTemplateRepository") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        UUID password = UUID.randomUUID();
        if(email == null || email.isEmpty()) {
            System.err.println("Error: email not specified");
            return null;
        }
        usersRepository.save(new User((long) ++id, email, password.toString()));
        return password.toString();
    }
}