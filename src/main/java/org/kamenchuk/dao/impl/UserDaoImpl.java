package org.kamenchuk.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.kamenchuk.dao.UserDao;
import org.kamenchuk.dao.config.HibernateSessionFactoryUtil;
import org.kamenchuk.dto.UCUserDto;
import org.kamenchuk.models.User;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao<User> {

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(HibernateSessionFactoryUtil.SESSION_FACTORY.getSessionFactory().openSession().get(User.class,id));
    }

    @Override
    public List<User> getAll() {
        List<User> users = (List<User>)HibernateSessionFactoryUtil.SESSION_FACTORY
                .getSessionFactory().getCurrentSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void update(UCUserDto user) {
        Session session = HibernateSessionFactoryUtil.SESSION_FACTORY
                .getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.SESSION_FACTORY
                .getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void save(UCUserDto user) {
        Session session = HibernateSessionFactoryUtil.SESSION_FACTORY
                .getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(user);
        tx1.commit();
        session.close();
    }

}

