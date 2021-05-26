package com.dumannazim.hrms2.business.abstracts;

import com.dumannazim.hrms2.entities.concretes.EmployerUser;
import com.dumannazim.hrms2.entities.concretes.StaffUser;

import java.util.List;

import com.dumannazim.hrms2.core.utilities.results.DataResult;
public interface StaffUserService {
    DataResult<List<StaffUser>> getAll();

    DataResult<Boolean> confirmEmployer(StaffUser staffUser, EmployerUser confirmedEmployerUser);
}

