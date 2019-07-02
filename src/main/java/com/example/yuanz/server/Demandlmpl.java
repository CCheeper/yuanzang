package com.example.yuanz.server;

import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.DemandmanagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.Null;
import java.util.List;

public interface Demandlmpl extends JpaRepository <DemandmanagerEntity,String> {

    @Query(value = "select * from demandmanager LIMIT ?1,?2 ",nativeQuery = true)
    List<DemandmanagerEntity> findByDemandRang(int start,int end);

    @Query(value = " select count(*) from demandmanager",nativeQuery = true)
    int findCountByDemand();

}
