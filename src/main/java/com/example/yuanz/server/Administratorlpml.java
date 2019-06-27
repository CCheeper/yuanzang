package com.example.yuanz.server;

import com.example.yuanz.entity.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Administratorlpml extends JpaRepository<AdministratorEntity,String> {
    AdministratorEntity findAdministratorEntityByUsername(String username);
}
