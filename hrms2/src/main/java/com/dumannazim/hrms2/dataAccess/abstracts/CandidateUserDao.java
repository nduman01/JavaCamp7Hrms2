package com.dumannazim.hrms2.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dumannazim.hrms2.entities.concretes.CandidateUser;

@Repository
public interface CandidateUserDao extends JpaRepository<CandidateUser, Integer> {
    List<CandidateUser> findByEmailIs(String email);
    List<CandidateUser> findByIdentityNumberIs(String identityNumber);
}
