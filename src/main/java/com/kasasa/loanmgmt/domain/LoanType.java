package com.kasasa.loanmgmt.domain;

import java.math.BigDecimal;

public enum LoanType {
    STUDENT  (BigDecimal.valueOf(0)),
    AUTO     (BigDecimal.valueOf(500)),
    PERSONAL (BigDecimal.valueOf(750)),
    MORTGAGE (BigDecimal.valueOf(1500));

    private BigDecimal fee;

    LoanType(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal fee() {
        return fee;
    }
}
