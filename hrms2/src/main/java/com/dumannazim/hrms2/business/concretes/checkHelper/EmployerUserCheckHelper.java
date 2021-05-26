package com.dumannazim.hrms2.business.concretes.checkHelper;

import com.dumannazim.hrms2.entities.concretes.EmployerUser;

public class EmployerUserCheckHelper {
    public static boolean allFieldsAreRequired(EmployerUser employerUser){
        if (employerUser.getEmail().strip().isEmpty()
                || employerUser.getPassword().strip().isEmpty()
                || employerUser.getCompanyName().strip().isEmpty()
                || employerUser.getPhoneNumber().strip().isEmpty()
                || employerUser.getWebAddress().strip().isEmpty())
        {
            return false;
        }
        return true;
    }
}
