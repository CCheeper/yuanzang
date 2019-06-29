package com.example.yuanz.server;

import com.example.yuanz.entity.AdministratorEntity;
import com.example.yuanz.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Administratorlpml extends JpaRepository<AdministratorEntity,String> {
    AdministratorEntity findAdministratorEntityByUsername(String username);

    @Query(value = " select * from administrator  LIMIT ?1,?2", nativeQuery = true)
    List<AdministratorEntity> findByAdministratorRange(int start, int end);

    @Query(value = " select count(*) from administrator", nativeQuery = true)
    int  findCountByAdministrator();
}
