package it.project.carRental.SI2001.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractUserDao<T extends Serializable, ID> implements CrudRepository<T, ID> {

    protected Session session;
    protected Transaction transaction;

    @Override
    public void saveOrUpdate(T object) {
        throw new RuntimeException(AbstractUserDao.ERROR_MESSAGE);
    }

    @Override
    public T findByPrimaryKey(ID key) {
        throw new RuntimeException(AbstractUserDao.ERROR_MESSAGE);
    }

    @Override
    public List<T> findAll() {
        throw new RuntimeException(AbstractUserDao.ERROR_MESSAGE);
    }

    @Override
    public void delete(T object) {
        throw new RuntimeException(AbstractUserDao.ERROR_MESSAGE);
    }

    public abstract T findByUsername(String var1);

    public abstract boolean isLoggedOn(String var1, String var2);
}
