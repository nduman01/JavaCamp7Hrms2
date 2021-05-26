package com.dumannazim.hrms2.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dumannazim.hrms2.entities.concretes.EmployerUser;

@Repository
public interface EmployerUserDao extends JpaRepository<EmployerUser, Integer> {
    List<EmployerUser> findByEmailIs(String email);
}
