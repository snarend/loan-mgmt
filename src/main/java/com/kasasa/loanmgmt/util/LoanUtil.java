package com.kasasa.loanmgmt.util;

import com.kasasa.loanmgmt.domain.LoanType;
import com.kasasa.loanmgmt.dto.LoanDataDTO;

import java.math.BigDecimal;
import java.util.function.Function;

public class LoanUtil {

    public static Function<LoanDataDTO, BigDecimal> calculateAPR = dto -> {
        BigDecimal interest = dto.getPrincipalAmount().multiply(dto.getRate()).divide(BigDecimal.valueOf(100));
        interest = interest.multiply(BigDecimal.valueOf(dto.getLoanTerm() / 365));
        BigDecimal apr = LoanType.valueOf(dto.getLoanType()).fee().add(interest).divide(dto.getPrincipalAmount());
        apr = apr.multiply(BigDecimal.valueOf(365 * 100));
        apr = apr.divide(BigDecimal.valueOf(dto.getLoanTerm()));
        return apr;
    };
}
