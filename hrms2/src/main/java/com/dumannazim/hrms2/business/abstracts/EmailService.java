package com.dumannazim.hrms2.business.abstracts;

import com.dumannazim.hrms2.core.utilities.results.Result;
import com.dumannazim.hrms2.entities.concretes.User;

public interface EmailService {
    Result sendEmail(User user);
}
