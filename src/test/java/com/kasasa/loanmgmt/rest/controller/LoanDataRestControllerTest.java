package com.kasasa.loanmgmt.rest.controller;

import com.kasasa.loanmgmt.domain.LoanData;
import com.kasasa.loanmgmt.dto.LoanDataDTO;
import com.kasasa.loanmgmt.mapper.LoanDataMapper;
import com.kasasa.loanmgmt.service.LoanDataService;
import com.kasasa.loanmgmt.util.LoanUtilTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RunWith(MockitoJUnitRunner.class)
public class LoanDataRestControllerTest {

    @InjectMocks
    private LoanDataRestController loanDataRestController;

    @Mock
    private LoanDataService loanDataService;

    @Test
    public void testGetLoanById() {
        LoanData expectedLoanData = LoanUtilTest.createLoanData();
        Long loanId = Long.valueOf("1");
        Mockito.when(loanDataService.getLoanById(loanId)).thenReturn(expectedLoanData);
        loanDataRestController.setMapper(new LoanDataMapper());
        ResponseEntity<LoanDataDTO> response = loanDataRestController.findByLoanId(loanId);
        LoanDataDTO loanData = response.getBody();
        assertEquals(Long.valueOf("1"), loanData.getLoanId());
        assertEquals("STUDENT", loanData.getLoanType());
        assertEquals(Long.valueOf("3650"), loanData.getLoanTerm());
        assertEquals("01/01/1999", loanData.getDob());
        assertEquals("123456789", loanData.getSsn());
        assertEquals(new BigDecimal("100000.0"), loanData.getPrincipalAmount());
        assertEquals(new BigDecimal("5.0"), loanData.getRate());
        assertEquals("John Doe", loanData.getFullName());
        assertEquals(new BigDecimal("5.0"), loanData.getApr());
    }

    @Test
    public void testGetLoanByIdNotFound() {
        Long loanId = Long.valueOf("1");
        Mockito.when(loanDataService.getLoanById(loanId)).thenThrow(new EntityNotFoundException("entity not found"));
        ResponseEntity<LoanDataDTO> response = loanDataRestController.findByLoanId(loanId);
        assertEquals(NO_CONTENT, response.getStatusCode());
    }


    @Test
    public void testInsertLoanData() {
        LoanData expectedLoanData = LoanUtilTest.createLoanData();
        LoanDataDTO intputLoanData = LoanUtilTest.createLoanDataDTO();
        Mockito.when(loanDataService.insertLoanData(Mockito.any(LoanData.class))).thenReturn(expectedLoanData);
        loanDataRestController.setMapper(new LoanDataMapper());
        LoanDataDTO loanData = loanDataRestController.createLoan(intputLoanData);
        assertEquals(Long.valueOf("1"), loanData.getLoanId());
        assertEquals("STUDENT", loanData.getLoanType());
        assertEquals(Long.valueOf("3650"), loanData.getLoanTerm());
        assertEquals("01/01/1999", loanData.getDob());
        assertEquals("123456789", loanData.getSsn());
        assertEquals(new BigDecimal("100000.0"), loanData.getPrincipalAmount());
        assertEquals(new BigDecimal("5.0"), loanData.getRate());
        assertEquals("John Doe", loanData.getFullName());
        assertEquals(new BigDecimal("5.0"), loanData.getApr());
    }

}
