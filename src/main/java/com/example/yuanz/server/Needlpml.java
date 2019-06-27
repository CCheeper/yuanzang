package com.example.yuanz.server;

import com.example.yuanz.entity.NeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Needlpml extends JpaRepository<NeedEntity,String> {




        @Query(value = " select * from need  LIMIT ?1,?2", nativeQuery = true)
        List<NeedEntity> findByNeedRange(int start, int end);

        @Query(value = " select count(*) from need", nativeQuery = true)
        int  findCountByNeed();


    }


