package victornyt;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import victornyt.model.User;
import victornyt.repository.UserRepository;

@Singleton
public class StartUp {
    private final UserRepository userRepository;

    public StartUp(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    @Transactional
    public void init(@Observes StartupEvent event) {
        if (userRepository.count() == 0) {
            createUsers();
        }
    }

    private void createUsers() {
        User user = new User("admin", "admin", "Admin", true);
        userRepository.persist(user);
        user = new User("user", "user", "User", false);
        userRepository.persist(user);
    }
}