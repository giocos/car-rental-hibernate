package it.project.carRental.SI2001.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractCarDao<T extends Serializable, ID> implements CrudRepository<T, ID> {

    protected Session session;
    protected Transaction transaction;

    @Override
    public void saveOrUpdate(T object) {
        throw new RuntimeException(AbstractCarDao.ERROR_MESSAGE);
    }

    @Override
    public T findByPrimaryKey(ID key) {
        throw new RuntimeException(AbstractCarDao.ERROR_MESSAGE);
    }

    @Override
    public List<T> findAll() {
        throw new RuntimeException(AbstractCarDao.ERROR_MESSAGE);
    }

    @Override
    public void delete(T object) {
        throw new RuntimeException(AbstractCarDao.ERROR_MESSAGE);
    }

    public abstract T findByModel(ID key);
}
