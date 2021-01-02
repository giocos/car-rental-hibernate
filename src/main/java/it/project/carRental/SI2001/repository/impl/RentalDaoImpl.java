package it.project.carRental.SI2001.repository.impl;

import it.project.carRental.SI2001.entity.Rental;
import it.project.carRental.SI2001.hibernate.HibernateUtil;
import it.project.carRental.SI2001.repository.AbstractDao;

import java.util.List;

public class RentalDaoImpl extends AbstractDao<Rental, Integer> {

    @Override
    public void saveOrUpdate(Rental rental) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(rental);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public Rental findByPrimaryKey(Integer id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final Rental rental = session.createQuery("FROM Rental r, WHERE r.rental_id = :id", Rental.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return rental;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public List<Rental> findAll() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final List<Rental> rentals = session.createQuery("FROM Rental", Rental.class)
                    .getResultList();

            return rentals;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Rental rental) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(rental);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public List<Rental> findAllByPrimaryKey(final Integer id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final List<Rental> rentals = session.createQuery("FROM Rental r WHERE r.user.user_id = :id", Rental.class)
                    .setParameter("id", id)
                    .getResultList();

            return rentals;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }

    }
}
