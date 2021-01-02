package it.project.carRental.SI2001.repository.impl;

import it.project.carRental.SI2001.entity.User;
import it.project.carRental.SI2001.hibernate.HibernateUtil;
import it.project.carRental.SI2001.repository.AbstractUserDao;

import java.util.List;

public class UserDaoImpl extends AbstractUserDao<User, Integer> {
    @Override
    public void saveOrUpdate(final User user) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
             session.close();
        }
    }

    @Override
    public User findByPrimaryKey(final Integer id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final String query = "FROM Utente u WHERE u.user_id = :id";
            return session.createQuery(query, User.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findAll() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final String query = "FROM Utente u WHERE u.tipologia.tipologia != :tipologia";
            return session.createQuery(query, User.class)
                    .setParameter("tipologia", "admin")
                    .getResultList();

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public void delete(final User user) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public User findByUsername(final String username) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final String query = "FROM User u WHERE u.username = :username";
            return session.createQuery(query, User.class)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public boolean isLoggedOn(final String username, final String password) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final String query = "FROM User u WHERE u.username = :username AND u.password = :password";
            return !session.createQuery(query, User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .list() //.getResultList()
                    .isEmpty();

        } catch (final Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            session.close();
        }
    }
}
