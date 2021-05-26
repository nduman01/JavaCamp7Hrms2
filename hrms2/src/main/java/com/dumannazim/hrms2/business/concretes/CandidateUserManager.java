package com.dumannazim.hrms2.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumannazim.hrms2.business.abstracts.CandidateUserService;
import com.dumannazim.hrms2.business.abstracts.EmailService;
import com.dumannazim.hrms2.core.adapters.UserCheckService;
import com.dumannazim.hrms2.core.utilities.results.DataResult;
import com.dumannazim.hrms2.core.utilities.results.ErrorResult;
import com.dumannazim.hrms2.core.utilities.results.Result;
import com.dumannazim.hrms2.core.utilities.results.SuccessDataResult;
import com.dumannazim.hrms2.core.utilities.results.SuccessResult;
import com.dumannazim.hrms2.dataAccess.abstracts.CandidateUserDao;
import com.dumannazim.hrms2.entities.concretes.CandidateUser;
import com.dumannazim.hrms2.business.concretes.checkHelper.CandidateUserCheckHelper;

@Service
public class CandidateUserManager implements CandidateUserService  {

    private final CandidateUserDao candidateUserDao;
    private final UserCheckService userCheckService;
    private final EmailService emailService;

    @Autowired
    public CandidateUserManager(CandidateUserDao candidateUserDao, UserCheckService userCheckService, EmailService emailService) {
        this.candidateUserDao = candidateUserDao;
        this.userCheckService = userCheckService;
        this.emailService = emailService;
    }

    @Override
    public DataResult<List<CandidateUser>> getAll() {
        return new SuccessDataResult<>(this.candidateUserDao.findAll());
    }

    @Override
    public DataResult<List<CandidateUser>> findByEmailIs(String email) {
        return new SuccessDataResult<>(this.candidateUserDao.findByEmailIs(email));
    }

    @Override
    public DataResult<List<CandidateUser>> findByIdentityNumberIs(String identityNumber) {
        return new SuccessDataResult<>(this.candidateUserDao.findByIdentityNumberIs(identityNumber));
    }

    @Override
    public DataResult<Boolean> checkIfRealPerson(String nationalityId, String firstName, String lastName, LocalDate dateOfBirthYear) {
        return new DataResult<>(this.userCheckService.checkIfRealPerson(nationalityId, firstName, lastName, dateOfBirthYear), true);
    }

    @Override
    public Result add(CandidateUser candidateUser) {
        var checkEmail = this.findByEmailIs(candidateUser.getEmail()).getData().size() != 0;
        var checkIdentityNumber = this.findByIdentityNumberIs(candidateUser.getIdentityNumber()).getData().size() != 0;
        var checkUserRealOrNot = !this.checkIfRealPerson(candidateUser.getIdentityNumber(), candidateUser.getFirstName(), candidateUser.getLastName(), candidateUser.getDateOfBirth()).getData();
        var checkFields = !CandidateUserCheckHelper.allFieldsAreRequired(candidateUser);

        if (checkEmail || checkIdentityNumber || checkUserRealOrNot || checkFields) {

            String errorMessage = "";

            if (checkEmail || checkIdentityNumber){
                errorMessage += "Email or Identity Number already exists. ";
            }
            if (checkUserRealOrNot) {
                errorMessage += "Not a real person. ";
            }
            if (checkFields) {
                errorMessage += "All fields are required.";
            }

            return new ErrorResult(errorMessage);
        }

        this.candidateUserDao.save(candidateUser);
        return new SuccessResult(this.emailService.sendEmail(candidateUser).getMessage());
    }
}