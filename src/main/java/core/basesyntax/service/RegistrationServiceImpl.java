package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new IncorrectDataForRegistrationException("User cannot be null");
        }
        User user1 = storageDao.get(user.getLogin());
        if (user1 == null && user.getLogin().length() >= 6
                && user.getPassword().length() >= 6 && user.getAge() >= 18) {
            storageDao.add(user);
            return user;
        }
        throw new IncorrectDataForRegistrationException("Incorrect data for registration!");
    }
}
