package com.dumannazim.hrms2.core.adapters;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.dumannazim.hrms2.MernisService.BCFKPSPublicSoap;

@Component
public class MernisServiceAdapter implements UserCheckService {
    @Override
    public Boolean checkIfRealPerson(String nationalityId, String firstName, String lastName, LocalDate dateOfBirthYear) {
        BCFKPSPublicSoap soapClient = new BCFKPSPublicSoap();
        boolean result = false;
        try {
            result = soapClient.TCKimlikNoDogrula(Long.parseLong(nationalityId), firstName, lastName, dateOfBirthYear.getYear());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

