package org.practise.repositories;

import org.practise.models.User;

import java.util.Optional;

public interface UserRepository extends CRUDRepository<User>{
    Optional<User> findByEmail(String emailUser);
}
