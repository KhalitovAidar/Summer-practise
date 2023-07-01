package org.practise.repositories;

import org.practise.models.User;

public interface UserRepository extends CRUDRepository<User>{
    User findByEmail(String emailUser);
}
