package org.practise.repositories;

import java.io.IOException;

public interface CRUDRepository<T> {
    void save(T model);
}
