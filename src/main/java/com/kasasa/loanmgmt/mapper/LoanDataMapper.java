package com.kasasa.loanmgmt.mapper;

import com.kasasa.loanmgmt.domain.LoanData;
import com.kasasa.loanmgmt.domain.LoanType;
import com.kasasa.loanmgmt.dto.LoanDataDTO;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static com.kasasa.loanmgmt.util.LoanUtil.calculateAPR;

@Component
public class LoanDataMapper {

    public LoanData mapToDomain(LoanDataDTO dto) {
        LoanData domain = new LoanData();
        domain.setDob(dto.getDob());
        domain.setFullName(dto.getFullName());
        domain.setLoanTerm(dto.getLoanTerm());
        domain.setLoanType(LoanType.valueOf(dto.getLoanType()));
        domain.setPrincipalAmount(dto.getPrincipalAmount());
        domain.setRate(dto.getRate());
        domain.setSsn(Base64.getEncoder().encodeToString(dto.getSsn().getBytes()));
        domain.setApr(calculateAPR.apply(dto));
        return domain;
    }

    public LoanDataDTO mapToDTO(LoanData domain) {
        LoanDataDTO dto = new LoanDataDTO();
        dto.setApr(domain.getApr());
        dto.setDob(domain.getDob());
        dto.setFullName(domain.getFullName());
        dto.setLoanId(domain.getLoanId());
        dto.setLoanTerm(domain.getLoanTerm());
        dto.setLoanType(domain.getLoanType().name());
        dto.setPrincipalAmount(domain.getPrincipalAmount());
        dto.setRate(domain.getRate());
        dto.setSsn(new String(Base64.getDecoder().decode(domain.getSsn())));
        return dto;
    }

}
