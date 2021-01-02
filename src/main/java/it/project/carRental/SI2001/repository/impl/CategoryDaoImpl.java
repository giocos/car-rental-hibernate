package it.project.carRental.SI2001.repository.impl;

import it.project.carRental.SI2001.entity.Category;
import it.project.carRental.SI2001.hibernate.HibernateUtil;
import it.project.carRental.SI2001.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryDaoImpl implements CrudRepository<Category, String> {

    private Session session;
    private Transaction transaction;

    @Override
    public void saveOrUpdate(final Category category) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public Category findByPrimaryKey(final String name) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final Category category = session.createQuery("FROM Category c WHERE c.category = :category", Category.class)
                    .setParameter("category", name)
                    .getSingleResult();

            return category;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public List<Category> findAll() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            final List<Category> categories = session.createQuery("FROM Category", Category.class)
                    .getResultList();

            return categories;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public void delete(final Category category) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(category);
            transaction.commit();

        } catch (final Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }
}
