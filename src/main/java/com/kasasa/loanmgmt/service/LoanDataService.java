package com.kasasa.loanmgmt.service;

import com.kasasa.loanmgmt.domain.LoanData;

public interface LoanDataService {

    LoanData getLoanById(Long loanId);

    LoanData insertLoanData(LoanData loanData);
}
