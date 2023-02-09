package org.kamenchuk.dao.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.kamenchuk.models.*;

import java.util.Properties;


public enum HibernateSessionFactoryUtil {
    SESSION_FACTORY;
    private SessionFactory sessionFactory;
    private final String DRIVER = "org.postgresql.Driver",
            URL = "jdbc:postgresql://localhost:5432/rent_service",
            USER = "postgres",
            PASSWORD = "toor";

    HibernateSessionFactoryUtil() {
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");


                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(ExtraUsersData.class);
                configuration.addAnnotatedClass(Mark.class);
                configuration.addAnnotatedClass(Model.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(User.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.addProperties(settings).buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
