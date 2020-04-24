package com.kasasa.loanmgmt.rest.controller;

import com.kasasa.loanmgmt.domain.LoanData;
import com.kasasa.loanmgmt.dto.LoanDataDTO;
import com.kasasa.loanmgmt.mapper.LoanDataMapper;
import com.kasasa.loanmgmt.service.LoanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/loan")
public class LoanDataRestController {

    @Autowired
    private LoanDataService loanDataService;

    @Autowired
    private LoanDataMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<LoanDataDTO> findByLoanId(@PathVariable("id") Long loanId) {
        LoanDataDTO dto = null;
        try {
            dto = mapper.mapToDTO(loanDataService.getLoanById(loanId));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDataDTO createLoan(@RequestBody LoanDataDTO loanDataDTO) {
        LoanData domain = loanDataService.insertLoanData(mapper.mapToDomain(loanDataDTO));
        return mapper.mapToDTO(domain);
    }

    public void setMapper(LoanDataMapper mapper) {
        this.mapper = mapper;
    }
}
