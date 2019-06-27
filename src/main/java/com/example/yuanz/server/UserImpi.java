package com.example.yuanz.server;

import com.example.yuanz.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserImpi extends JpaRepository<UserEntity,String> {
    UserEntity findUserEntityByUsername(String username);
}
