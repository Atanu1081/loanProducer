package com.example.demo;

import org.springframework.stereotype.Service;
import pojoClasses.Acknowledgement;
import pojoClasses.CustomerRequest;

import java.util.List;

@Service
public class LoanEligibilityService
{

    public Acknowledgement checkLoanEligibility (CustomerRequest request)
    {
        Acknowledgement acknowledgement = new Acknowledgement();
        List<String> mismatchCriteriaList = acknowledgement.getCriteriaMismatch();

        if (!(request.getAge()>30 && request.getAge()<=60))
        {
            mismatchCriteriaList.add("Person age should be in between 30 to 60");
        }

        if (!(request.getYearlyIncome()>200000))
        {
            mismatchCriteriaList.add("Persons income must be minimum 200000");
        }

        if (!(request.getCibilScore()>600))
        {
            mismatchCriteriaList.add("Low Cibil score. Try after 6 months");
        }

        if (mismatchCriteriaList.size()>0)
        {
            acknowledgement.setApprovedAmount(0);
            acknowledgement.setIsEligible("Not eligibile");
        }

        else
        {
            acknowledgement.setApprovedAmount(1000000);
            acknowledgement.setIsEligible("Loan Approved");
        }

        return acknowledgement;
    }
}
