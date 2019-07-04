package com.example.yuanz.server;

import com.example.yuanz.entity.PolicyEntity;
import com.example.yuanz.entity.PowerEditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public interface Powerlmpl extends CrudRepository<PowerEditEntity,String> {
    @Query(value = " select count(*) from power_edit", nativeQuery = true)
    int  findCountByPolicy();
    PowerEditEntity findPowerEditEntityByAdminId(String  id);
    void deleteByAdminId(String id);

}
