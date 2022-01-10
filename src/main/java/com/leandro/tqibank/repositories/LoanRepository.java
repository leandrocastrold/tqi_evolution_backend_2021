package com.leandro.tqibank.repositories;

import com.leandro.tqibank.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    Optional<List<Loan>> findByClientId(Integer id);
}
