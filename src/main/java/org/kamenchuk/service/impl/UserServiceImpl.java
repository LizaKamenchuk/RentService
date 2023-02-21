package org.kamenchuk.service.impl;

import org.kamenchuk.dao.UserDao;
import org.kamenchuk.models.User;
import org.kamenchuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO implement UserService Interface!!!!!!!!!!!!!!!!!!!!!!!!
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    @Override
    public String getPage() {
        return "page";
    }

}

