package it.project.carRental.SI2001.repository.impl;

import it.project.carRental.SI2001.entity.Car;
import it.project.carRental.SI2001.hibernate.HibernateUtil;
import it.project.carRental.SI2001.repository.AbstractCarDao;

import java.util.List;

public class CarDaoImpl extends AbstractCarDao<Car, String> {

    public void saveOrUpdate(Car car) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    public Car findByPrimaryKey(final String plateNumber) {
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final Car car = session.createQuery("FROM Car c WHERE a.plate_number = :plate_number", Car.class)
                    .setParameter("plateNumber", plateNumber)
                    .getSingleResult();

            return car;
            
        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    public Car findByModel(final String model) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final Car car = session.createQuery("FROM Car a WHERE a.model= :model", Car.class)
                    .setParameter("model", model)
                    .getSingleResult();

            return car;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    public List<Car> findAll() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final List<Car> cars = session.createQuery("FROM Car", Car.class)
                    .getResultList();

            return cars;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    public void delete(Car car) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }
}
