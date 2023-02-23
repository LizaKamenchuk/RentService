package org.kamenchuk.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentSessionContext;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.service.ServiceRegistry;
import org.kamenchuk.models.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

@org.springframework.context.annotation.Configuration

public class HibernateSessionFactoryUtil {
    private SessionFactory sessionFactory;
    private final static String DRIVER = "org.postgresql.Driver",
            URL = "jdbc:postgresql://localhost:5432/rent_service",
            USER = "postgres",
            PASSWORD = "toor";

    @Bean
    @Scope("singleton")
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();

                properties.put(Environment.DRIVER, DRIVER);
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USER);
                properties.put(Environment.PASS, PASSWORD);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");


                configuration.setProperties(properties);


                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(ExtraUsersData.class);
                configuration.addAnnotatedClass(Mark.class);
                configuration.addAnnotatedClass(Model.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(CurrentSessionContext.class);
                configuration.addAnnotatedClass(HibernatePersistenceProvider.class);



                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.addProperties(properties).buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
