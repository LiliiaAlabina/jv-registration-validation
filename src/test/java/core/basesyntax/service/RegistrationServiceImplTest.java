package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    private final RegistrationServiceImpl service = new RegistrationServiceImpl();
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("newuser", "password", 20);
    }

    @Test
    void registerUserNull_notOk() {
        assertThrows(IncorrectDataForRegistrationException.class, () -> {
            service.register(null);
        });
    }

    @Test
    void registerValidUser_ok() {
        User result = service.register(user);
        assertNotNull(result);
    }

    @Test
    void registerLoginTooShort_notOk() {
        user.setLogin("user");
        assertThrows(IncorrectDataForRegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void registerPasswordTooShort_notOk() {
        user.setPassword("word");
        assertThrows(IncorrectDataForRegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_ageTooYoung_notOk() {
        user.setAge(10);
        assertThrows(IncorrectDataForRegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_existingLogin_notOk() {
        User secondUser = new User("newuser", "anotherPassword", 25);
        assertThrows(IncorrectDataForRegistrationException.class, () -> {
            service.register(secondUser);
        });
    }
}
