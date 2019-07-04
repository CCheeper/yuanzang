package com.example.yuanz.server;


import com.example.yuanz.entity.TanlentintrductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Talentlmpl extends JpaRepository<TanlentintrductionEntity,String> {
    @Query(value = " select * from tanlentintrduction  LIMIT ?1,?2", nativeQuery = true)
    List<TanlentintrductionEntity> findByTalenRange(int start, int end);

    @Query(value = " select count(*) from tanlentintrduction", nativeQuery = true)
    int  findCountByTanlent();

    TanlentintrductionEntity findTanlentEntityByTitle(String title);


}
