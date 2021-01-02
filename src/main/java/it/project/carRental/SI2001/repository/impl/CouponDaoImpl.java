package it.project.carRental.SI2001.repository.impl;

import it.project.carRental.SI2001.entity.Coupon;
import it.project.carRental.SI2001.hibernate.HibernateUtil;
import it.project.carRental.SI2001.repository.AbstractDao;

import java.util.List;

public class CouponDaoImpl extends AbstractDao<Coupon, Integer> {

    @Override
    public void saveOrUpdate(final Coupon coupon) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(coupon);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public Coupon findByPrimaryKey(final Integer id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final Coupon coupon = session.createQuery("FROM Coupon c WHERE c.id = :id", Coupon.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return coupon;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public List<Coupon> findAll() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final List<Coupon> coupons = session.createQuery("FROM Coupon", Coupon.class)
                    .getResultList();

            return coupons;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public List<Coupon> findAllByPrimaryKey(final Integer id) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final String query = "FROM Coupon c WHERE c.rental.user.user_id = :id";
            return session.createQuery(query, Coupon.class)
                    .setParameter("id", id)
                    .getResultList();

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public void delete(final Coupon coupon) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(coupon);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }
}
