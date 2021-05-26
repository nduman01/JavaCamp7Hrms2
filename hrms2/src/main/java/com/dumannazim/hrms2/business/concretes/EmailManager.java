package com.dumannazim.hrms2.business.concretes;

import org.springframework.stereotype.Component;

import com.dumannazim.hrms2.business.abstracts.EmailService;
import com.dumannazim.hrms2.core.utilities.results.Result;
import com.dumannazim.hrms2.core.utilities.results.SuccessResult;
import com.dumannazim.hrms2.entities.concretes.User;

@Component
public class EmailManager implements EmailService {
    @Override
    public Result sendEmail(User user) {
        return new SuccessResult("Email has been successfuly sent to: "+user.getEmail() );
    }
}
