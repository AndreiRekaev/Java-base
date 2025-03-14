package edu.school21.utils;

import edu.school21.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private HibernateSessionFactoryUtil() {}

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}