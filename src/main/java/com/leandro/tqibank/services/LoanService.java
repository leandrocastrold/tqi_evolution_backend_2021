package com.leandro.tqibank.services;

import com.leandro.tqibank.dtos.LoanDTO;
import com.leandro.tqibank.models.Loan;
import com.leandro.tqibank.repositories.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> get() {
        return loanRepository.findAll();
    }

    public Loan getById(Integer id) {
        return loanRepository.findById(id).get();
    }

    public Loan create(LoanDTO loanDTO) {
        Loan loan = convertLoadDTOToLoan(loanDTO);
        return loanRepository.save(loan);
    }

    public List<Loan> getByClientId(Integer id) {
        return loanRepository.findByClientId(id).get();
    }

    public Long count() {
       return loanRepository.count();
    }

    private Loan convertLoadDTOToLoan(LoanDTO loanDTO) {
        Loan loan = new Loan();
        BeanUtils.copyProperties(loanDTO, loan);
        return loan;
    }
}
