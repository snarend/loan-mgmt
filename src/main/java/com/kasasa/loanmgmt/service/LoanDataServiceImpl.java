package com.kasasa.loanmgmt.service;

import com.kasasa.loanmgmt.domain.LoanData;
import com.kasasa.loanmgmt.repository.LoanDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanDataServiceImpl implements LoanDataService{

    @Autowired
    private LoanDataRepository loanDataRepository;

    @Override
    @Transactional(readOnly = true)
    public LoanData getLoanById(Long loanId) {
        return loanDataRepository.getOne(loanId);
    }

    @Override
    @Transactional
    public LoanData insertLoanData(LoanData loanData) {
        return loanDataRepository.save(loanData);
    }
}
