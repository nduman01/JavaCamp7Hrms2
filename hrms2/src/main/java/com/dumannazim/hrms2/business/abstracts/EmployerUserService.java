package com.dumannazim.hrms2.business.abstracts;

import java.util.List;

import com.dumannazim.hrms2.core.utilities.results.DataResult;
import com.dumannazim.hrms2.core.utilities.results.Result;
import com.dumannazim.hrms2.entities.concretes.EmployerUser;

public interface EmployerUserService {
    DataResult<List<EmployerUser>> getAll();
    DataResult<List<EmployerUser>> findByEmailIs(String email);

    Result add(EmployerUser employerUser);
}
