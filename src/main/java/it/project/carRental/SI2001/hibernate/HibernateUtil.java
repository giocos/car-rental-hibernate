package it.project.carRental.SI2001.hibernate;

import it.project.carRental.SI2001.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                final Configuration configuration = new Configuration();
                //add properties from xml
                configuration.configure("hibernate.cfg.xml");
                //set entity classes to configuration
                HibernateUtil.addAnnotatedClass(configuration);

                final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static void addAnnotatedClass(final Configuration configuration) {
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Rental.class);
        configuration.addAnnotatedClass(Coupon.class);
        configuration.addAnnotatedClass(Fine.class);
    }
}
