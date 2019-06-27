package com.example.yuanz.server;

import com.example.yuanz.entity.PolicyEntity;
import com.example.yuanz.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Policylpml extends JpaRepository<PolicyEntity,String> {
    @Query(value = " select * from policy  LIMIT ?1,?2", nativeQuery = true)
    List<PolicyEntity> findByPolicyRange(int start, int end);

    @Query(value = " select count(*) from policy", nativeQuery = true)
    int  findCountByPolicy();

    PolicyEntity findPolicyEntityByTitle(String title);
}
