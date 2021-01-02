package it.project.carRental.SI2001.repository.impl;

import it.project.carRental.SI2001.entity.Role;
import it.project.carRental.SI2001.hibernate.HibernateUtil;
import it.project.carRental.SI2001.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoleDaoImpl implements CrudRepository<Role, String> {

    private Session session;
    private Transaction transaction;

    @Override
    public void saveOrUpdate(Role role) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public Role findByPrimaryKey(final String name) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final Role role = session.createQuery("FROM Role r WHERE r.role = :role", Role.class)
                    .setParameter("role", name)
                    .getSingleResult();

            return role;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public List<Role> findAll() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final List<Role> roles = session.createQuery("FROM Role", Role.class)
                    .getResultList();

            return roles;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Role role) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(role);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }
}
