package org.example.microloanservice.infrastructure.mapper;

import org.example.microloanservice.domain.model.Loan;
import org.example.microloanservice.infrastructure.entity.LoanEntity;

public class LoanMapper {
    public static Loan toDomain(LoanEntity entity) {
        Loan new_loan= new Loan(
                entity.getUserId(),
                entity.getBookId(),
                entity.getIssueDate(),
                entity.getDueDate(),
                entity.getReturnDate(),
                entity.getStatus()
        );
        new_loan.setId(entity.getId());
        return new_loan;
    }

    public static LoanEntity toEntity(Loan loan) {
        LoanEntity entity = new LoanEntity();
        entity.setId(loan.getId());
        entity.setUserId(loan.getUserId());
        entity.setBookId(loan.getBookId());
        entity.setIssueDate(loan.getIssueDate());
        entity.setDueDate(loan.getDueDate());
        entity.setReturnDate(loan.getReturnDate());
        entity.setStatus(loan.getStatus());
        return entity;
    }
}
