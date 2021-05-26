package com.dumannazim.hrms2.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.dumannazim.hrms2.core.utilities.results.DataResult;
import com.dumannazim.hrms2.core.utilities.results.Result;
import com.dumannazim.hrms2.entities.concretes.CandidateUser;

public interface CandidateUserService {
    DataResult<List<CandidateUser>> getAll();
    DataResult<List<CandidateUser>> findByEmailIs(String email);
    DataResult<List<CandidateUser>> findByIdentityNumberIs(String identityNumber);

    DataResult<Boolean> checkIfRealPerson(String nationalityId, String firstName, String lastName, LocalDate dateOfBirthYear);

    Result add(CandidateUser candidateUser);
}
