package com.example.yuanz.server;


import com.example.yuanz.entity.HelpmanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Helplmpl extends JpaRepository<HelpmanEntity,String> {

    @Query (value = "select * from user LIMIT ?1,?2",nativeQuery = true)
    List<HelpmanEntity> findByhelpmanRang(int start,int end);

    @Query(value = "select count (*) from user", nativeQuery = true)
    int findCountByhelpman();

    HelpmanEntity findUserEntitiesByUsername(String username);


}
