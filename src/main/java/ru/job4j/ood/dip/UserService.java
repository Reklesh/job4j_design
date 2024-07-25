package ru.job4j.ood.dip;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public void createUser(User user) {
        userRepository.save(user);
    }
}