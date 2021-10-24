package utils;

import com.github.javafaker.Faker;
import entity.User;

import java.util.Random;

public class UserData {
    public static User get() {
        Random random = new Random();
        User user = new User();
        Faker faker = new Faker();
        String username = faker.name().username() + random.nextInt(9999999);
        String password = "12345678";
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(firstName);
        user.setLastname(lastName);
        return user;
    }
}
