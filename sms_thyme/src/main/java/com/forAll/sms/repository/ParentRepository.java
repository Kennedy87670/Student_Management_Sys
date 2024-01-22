package com.forAll.sms.repository;


import com.forAll.sms.dto.ParentsDto;
import com.forAll.sms.entity.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parents, Long> {
    ParentsDto findByEmail(String email);
}

