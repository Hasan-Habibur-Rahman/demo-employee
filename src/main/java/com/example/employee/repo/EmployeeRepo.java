package com.example.employee.repo;

import com.example.employee.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT m FROM EmployeeEntity m WHERE :sText is null or lower(m.code||m.name) LIKE '%lower(:sText)%' ORDER BY m.code")
    Page<EmployeeEntity> findAllCustom(Pageable pageable, @Param("sText") String sText);

    @Query("SELECT m FROM EmployeeEntity m WHERE m.code=:code OR m.mobileNo=:mobileNo OR m.email=:email OR m.nid=:nid")
    Optional<EmployeeEntity> findIsUnique(@Param("code") String code, @Param("mobileNo") String mobileNo, @Param("email") String email, @Param("nid") String nid);

}
