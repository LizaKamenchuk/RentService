package com.kamenchuk.authefication.service;

import com.kamenchuk.authefication.models.User;

public interface UserService {
    User getByLogin(String login);
}
