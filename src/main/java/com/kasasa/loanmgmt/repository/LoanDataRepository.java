package com.kasasa.loanmgmt.repository;

import com.kasasa.loanmgmt.domain.LoanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDataRepository extends JpaRepository<LoanData, Long> {
}
