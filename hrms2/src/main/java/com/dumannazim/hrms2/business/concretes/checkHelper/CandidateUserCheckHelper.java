package com.dumannazim.hrms2.business.concretes.checkHelper;

import com.dumannazim.hrms2.entities.concretes.CandidateUser;

public class CandidateUserCheckHelper {
    public static boolean allFieldsAreRequired(CandidateUser candidateUser){
        if (candidateUser.getEmail().strip().isEmpty()
                || candidateUser.getPassword().strip().isEmpty()
                || candidateUser.getFirstName().strip().isEmpty()
                || candidateUser.getLastName().strip().isEmpty()
                || candidateUser.getIdentityNumber().strip().isEmpty()
                || candidateUser.getDateOfBirth().toString().strip().isEmpty())
        {
            return false;
        }
        return true;
    }
}
