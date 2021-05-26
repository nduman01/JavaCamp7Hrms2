package com.dumannazim.hrms2.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dumannazim.hrms2.entities.concretes.StaffUser;

@Repository
public interface StaffUserDao extends JpaRepository<StaffUser, Integer> {

}
