package com.example.yuanz.server;

import com.example.yuanz.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Schoolmpl extends JpaRepository<SchoolEntity,String> {

    @Query(value = " select * from school  LIMIT ?1,?2", nativeQuery = true)
    List<SchoolEntity> findBySchoolRange(int start, int end);

    @Query(value = " select count(*) from school", nativeQuery = true)
    int  findCountBySchool();

    List<SchoolEntity> findSchoolEntitiesBySchoolName(String schoolname);
}
