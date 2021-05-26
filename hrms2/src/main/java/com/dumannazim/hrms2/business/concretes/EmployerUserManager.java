package com.dumannazim.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumannazim.hrms2.business.abstracts.EmailService;
import com.dumannazim.hrms2.business.abstracts.EmployerUserService;
import com.dumannazim.hrms2.business.concretes.checkHelper.EmployerUserCheckHelper;
import com.dumannazim.hrms2.business.concretes.checkHelper.StaffUserCheckHelper;
import com.dumannazim.hrms2.core.utilities.results.DataResult;
import com.dumannazim.hrms2.core.utilities.results.ErrorResult;
import com.dumannazim.hrms2.core.utilities.results.Result;
import com.dumannazim.hrms2.core.utilities.results.SuccessDataResult;
import com.dumannazim.hrms2.core.utilities.results.SuccessResult;
import com.dumannazim.hrms2.dataAccess.abstracts.EmployerUserDao;
import com.dumannazim.hrms2.entities.concretes.EmployerUser;

@Service
public class EmployerUserManager implements EmployerUserService {

    private final EmployerUserDao employerUserDao;
    private final EmailService emailService;

    @Autowired
    public EmployerUserManager(EmployerUserDao employerUserDao, EmailService emailService) {
        this.employerUserDao = employerUserDao;
        this.emailService = emailService;
    }

    @Override
    public DataResult<List<EmployerUser>> getAll() {
        return new SuccessDataResult<>(this.employerUserDao.findAll());
    }

    @Override
    public DataResult<List<EmployerUser>> findByEmailIs(String email) {
        return new SuccessDataResult<>(this.employerUserDao.findByEmailIs(email));
    }



    @Override
    public Result add(EmployerUser employerUser) {
        var checkEmail = this.findByEmailIs(employerUser.getEmail()).getData().size() != 0;
        var checkFields = !EmployerUserCheckHelper.allFieldsAreRequired(employerUser);
        var staffConfirm = !StaffUserCheckHelper.confirmEmployer(employerUser);

        if (checkEmail || checkFields || staffConfirm) {

            String errorMessage = "";

            if (checkEmail) {
                errorMessage += "Email already exists. ";
            }

            if (checkFields) {
                errorMessage += "All fields are required. ";
            }

            if (staffConfirm){
                errorMessage += "Your registration confirmation operation refused by our Employee.";
            }

            return new ErrorResult(errorMessage);
        }

        this.employerUserDao.save(employerUser);
        return new SuccessResult(this.emailService.sendEmail(employerUser).getMessage());
    }
}
