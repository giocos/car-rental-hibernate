package it.project.carRental.SI2001.repository.impl;

import it.project.carRental.SI2001.entity.Fine;
import it.project.carRental.SI2001.hibernate.HibernateUtil;
import it.project.carRental.SI2001.repository.AbstractDao;

import java.util.List;

public class FineDaoImpl extends AbstractDao<Fine, Integer> {

    @Override
    public void saveOrUpdate(final Fine fine) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(fine);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public Fine findByPrimaryKey(final Integer id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final Fine fine = session.createQuery("FROM Fine f WHERE f.fine_id = :id", Fine.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return fine;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public List<Fine> findAll() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final List<Fine> fines = session.createQuery("FROM Fine", Fine.class)
                    .getResultList();

            return fines;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public void delete(final Fine fine) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(fine);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public List<Fine> findAllByPrimaryKey(final Integer id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final List<Fine> fines = session.createQuery("FROM Fine f WHERE f.rental.user.user_id = :id", Fine.class)
                    .setParameter("id", id)
                    .getResultList();

            return fines;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }
}
