package com.kasasa.loanmgmt.util;

import com.kasasa.loanmgmt.domain.LoanData;
import com.kasasa.loanmgmt.dto.LoanDataDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static com.kasasa.loanmgmt.domain.LoanType.AUTO;
import static com.kasasa.loanmgmt.domain.LoanType.MORTGAGE;
import static com.kasasa.loanmgmt.domain.LoanType.PERSONAL;
import static com.kasasa.loanmgmt.domain.LoanType.STUDENT;
import static com.kasasa.loanmgmt.util.LoanUtil.calculateAPR;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoanUtilTest {

    @Test
    public void testCalculateAPRForStudentLoans() {
        LoanDataDTO dto = createLoanDataDTO();
        assertEquals(new BigDecimal("5.0"), calculateAPR.apply(dto));
    }

    @Test
    public void testCalculateAPRForMortgageLoans() {
        LoanDataDTO dto = createLoanDataDTO();
        dto.setLoanType(MORTGAGE.name());
        assertEquals(new BigDecimal("5.150"), calculateAPR.apply(dto));
    }

    @Test
    public void testCalculateAPRForPersonalLoans() {
        LoanDataDTO dto = createLoanDataDTO();
        dto.setLoanType(PERSONAL.name());
        assertEquals( new BigDecimal("5.0750"), calculateAPR.apply(dto));
    }

    @Test
    public void testCalculateAPRForAutoLoans() {
        LoanDataDTO dto = createLoanDataDTO();
        dto.setLoanType(AUTO.name());
        assertEquals(new BigDecimal("5.050"), calculateAPR.apply(dto));
    }

    public static LoanDataDTO createLoanDataDTO() {
        LoanDataDTO dto = new LoanDataDTO();
        dto.setSsn("123456789");
        dto.setRate(BigDecimal.valueOf(5.0));
        dto.setPrincipalAmount(BigDecimal.valueOf(100000.00));
        dto.setFullName("John Doe");
        dto.setLoanTerm(Long.valueOf("3650"));
        dto.setLoanType(STUDENT.name());
        dto.setDob("01/01/1999");
        return dto;
    }

    public static LoanData createLoanData() {
        LoanData domain = new LoanData();
        domain.setSsn("MTIzNDU2Nzg5");
        domain.setRate(BigDecimal.valueOf(5.0));
        domain.setPrincipalAmount(BigDecimal.valueOf(100000.00));
        domain.setFullName("John Doe");
        domain.setLoanTerm(Long.valueOf("3650"));
        domain.setLoanType(STUDENT);
        domain.setDob("01/01/1999");
        domain.setLoanId(1l);
        domain.setApr(BigDecimal.valueOf(5.0));
        return domain;
    }
}
