package it.project.carRental.SI2001.repository;

import java.io.Serializable;
import java.util.List;

public interface CrudRepository<T extends Serializable, ID> {

    String ERROR_MESSAGE = "Method not implemented!";

    void saveOrUpdate(T object);
    T findByPrimaryKey(ID key);
    List<T> findAll();
    void delete(T object);
}
