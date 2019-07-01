package com.example.yuanz.server;

import com.alibaba.fastjson.JSONObject;
import com.example.yuanz.entity.WorkdynamicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Workdynamiclmpl extends JpaRepository <WorkdynamicEntity,String>{
    @Query(value = " select * from workdynamic  LIMIT ?1,?2", nativeQuery = true)
    List<WorkdynamicEntity> findByWorkdynamicRange(int start,int end);

    @Query(value = " select count(*) from workdynamic", nativeQuery = true)
    int findCountByWorkdynamic();

    WorkdynamicEntity findWorkdynamicEntityByTitle(String title);


}
