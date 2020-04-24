package com.kasasa.loanmgmt.service;

import com.kasasa.loanmgmt.domain.LoanData;
import com.kasasa.loanmgmt.domain.LoanType;
import com.kasasa.loanmgmt.repository.LoanDataRepository;
import com.kasasa.loanmgmt.util.LoanUtilTest;

import static com.kasasa.loanmgmt.domain.LoanType.STUDENT;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class LoanDataServiceTest {

    @InjectMocks
    private LoanDataServiceImpl loanDataService;

    @Mock
    private LoanDataRepository repository;

    @Test
    public void testGetLoanById() {
        LoanData expectedLoanData = LoanUtilTest.createLoanData();
        Long loanId = Long.valueOf("1");
        Mockito.when(repository.getOne(loanId)).thenReturn(expectedLoanData);
        LoanData loanData = loanDataService.getLoanById(loanId);
        assertEquals(Long.valueOf("1"), loanData.getLoanId());
        assertEquals(STUDENT, loanData.getLoanType());
        assertEquals(Long.valueOf("3650"), loanData.getLoanTerm());
        assertEquals("01/01/1999", loanData.getDob());
        assertEquals("MTIzNDU2Nzg5", loanData.getSsn());
        assertEquals(new BigDecimal("100000.0"), loanData.getPrincipalAmount());
        assertEquals(new BigDecimal("5.0"), loanData.getRate());
        assertEquals("John Doe", loanData.getFullName());
        assertEquals(new BigDecimal("5.0"), loanData.getApr());
    }

    @Test
    public void testInsertLoanData() {
        LoanData expectedLoanData = LoanUtilTest.createLoanData();
        LoanData intputLoanData = LoanUtilTest.createLoanData();
        intputLoanData.setLoanId(null);
        Mockito.when(repository.save(intputLoanData)).thenReturn(expectedLoanData);
        LoanData loanData = loanDataService.insertLoanData(intputLoanData);
        assertEquals(Long.valueOf("1"), loanData.getLoanId());
        assertEquals(STUDENT, loanData.getLoanType());
        assertEquals(Long.valueOf("3650"), loanData.getLoanTerm());
        assertEquals("01/01/1999", loanData.getDob());
        assertEquals("MTIzNDU2Nzg5", loanData.getSsn());
        assertEquals(new BigDecimal("100000.0"), loanData.getPrincipalAmount());
        assertEquals(new BigDecimal("5.0"), loanData.getRate());
        assertEquals("John Doe", loanData.getFullName());
        assertEquals(new BigDecimal("5.0"), loanData.getApr());
    }

}
