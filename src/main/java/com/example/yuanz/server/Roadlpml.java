package com.example.yuanz.server;

import com.example.yuanz.entity.RoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Roadlpml extends JpaRepository<RoadEntity, String> {
    @Query(value = " select * from road  LIMIT ?1,?2", nativeQuery = true)
    List<RoadEntity> findByRoadRange(int start, int end);

    @Query(value = " select count(*) from road", nativeQuery = true)
    int findCountByRoad();

    List<RoadEntity> findRoadEntitiesBySchoolname(String schoolname);
}
