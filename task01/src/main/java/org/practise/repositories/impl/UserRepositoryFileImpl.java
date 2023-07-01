package org.practise.repositories.impl;

import org.practise.models.User;
import org.practise.repositories.UserRepository;

import java.io.*;
import java.util.List;

public class UserRepositoryFileImpl implements UserRepository {
    private final String fileName;
    public UserRepositoryFileImpl(String filename) {
        this.fileName = filename;
    }
    @Override
    public void save(User model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(model.getId() + "|" + model.getEmail() + "|" + model.getPassword());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @Override
    public User findByEmail(String emailUser) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String> userData;

            while((line = reader.readLine()) != null) {
                userData = List.of(line.split("\\|"));

                if (userData.get(1).equals(emailUser)) {

                    return User.builder()
                            .id(userData.get(0))
                            .email(userData.get(1))
                            .password(userData.get(2))
                            .build();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Пользователь не найден");
    }
}
