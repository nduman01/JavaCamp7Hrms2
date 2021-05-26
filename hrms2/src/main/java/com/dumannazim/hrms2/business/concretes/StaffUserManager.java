package com.dumannazim.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumannazim.hrms2.business.abstracts.StaffUserService;
import com.dumannazim.hrms2.business.concretes.checkHelper.StaffUserCheckHelper;
import com.dumannazim.hrms2.core.utilities.results.DataResult;
import com.dumannazim.hrms2.core.utilities.results.SuccessDataResult;
import com.dumannazim.hrms2.dataAccess.abstracts.StaffUserDao;
import com.dumannazim.hrms2.entities.concretes.EmployerUser;
import com.dumannazim.hrms2.entities.concretes.StaffUser;

@Service
public class StaffUserManager implements StaffUserService {

    private final StaffUserDao staffUserDao;

    @Autowired
    public StaffUserManager(StaffUserDao staffUserDao) {
        this.staffUserDao = staffUserDao;
    }

    @Override
    public DataResult<List<StaffUser>> getAll() {
        return new SuccessDataResult<>(this.staffUserDao.findAll());
    }

    @Override
    public DataResult<Boolean> confirmEmployer(StaffUser staffUser, EmployerUser confirmedEmployerUser) {
        return new SuccessDataResult<>(StaffUserCheckHelper.confirmEmployer(confirmedEmployerUser));
    }
}

