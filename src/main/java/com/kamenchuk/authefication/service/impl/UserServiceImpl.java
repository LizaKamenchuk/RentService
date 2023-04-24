package com.kamenchuk.authefication.service.impl;

import com.kamenchuk.authefication.models.Role;
import com.kamenchuk.authefication.models.User;
import com.kamenchuk.authefication.repository.RoleRepository;
import com.kamenchuk.authefication.repository.UserRepository;
import com.kamenchuk.authefication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getByLogin(String login) {
        return repository.getUserByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.getUserByLogin(login);
        Role role = roleRepository.getRoleById(user.getRole().getId());
        user.setRole(role);
        return user;
    }
}
