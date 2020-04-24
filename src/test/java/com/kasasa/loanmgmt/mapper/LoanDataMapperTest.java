package com.kasasa.loanmgmt.mapper;

import com.kasasa.loanmgmt.domain.LoanData;
import com.kasasa.loanmgmt.domain.LoanType;
import com.kasasa.loanmgmt.dto.LoanDataDTO;
import com.kasasa.loanmgmt.util.LoanUtilTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoanDataMapperTest {

    private LoanDataMapper mapper = new LoanDataMapper();

    @Test
    public void testMapDomainToDTO() {
        LoanData domain = LoanUtilTest.createLoanData();
        LoanDataDTO dto = mapper.mapToDTO(domain);
        assertEquals(Long.valueOf("1"), dto.getLoanId());
        assertEquals("STUDENT", dto.getLoanType());
        assertEquals(Long.valueOf("3650"), dto.getLoanTerm());
        assertEquals("01/01/1999", dto.getDob());
        assertEquals("123456789", dto.getSsn());
        assertEquals(new BigDecimal("100000.0"), dto.getPrincipalAmount());
        assertEquals(new BigDecimal("5.0"), dto.getRate());
        assertEquals("John Doe", dto.getFullName());
        assertEquals(new BigDecimal("5.0"), dto.getApr());
    }

    @Test
    public void testMapDTOToDomain() {
        LoanDataDTO dto = LoanUtilTest.createLoanDataDTO();
        LoanData domain = mapper.mapToDomain(dto);
        assertEquals(null, domain.getLoanId());
        assertEquals(LoanType.STUDENT, domain.getLoanType());
        assertEquals(Long.valueOf("3650"), domain.getLoanTerm());
        assertEquals("01/01/1999", domain.getDob());
        assertEquals("MTIzNDU2Nzg5", domain.getSsn());
        assertEquals(new BigDecimal("100000.0"), domain.getPrincipalAmount());
        assertEquals(new BigDecimal("5.0"), domain.getRate());
        assertEquals("John Doe", domain.getFullName());
        assertEquals(new BigDecimal("5.0"), domain.getApr());
    }
}
