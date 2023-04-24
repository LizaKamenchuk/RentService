package com.kamenchuk.authefication.repository;

import com.kamenchuk.authefication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByLogin(String login);
}
