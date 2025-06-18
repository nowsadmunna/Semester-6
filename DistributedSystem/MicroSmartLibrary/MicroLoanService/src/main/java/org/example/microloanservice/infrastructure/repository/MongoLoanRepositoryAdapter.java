package org.example.microloanservice.infrastructure.repository;

import org.example.microloanservice.domain.model.Loan;
import org.example.microloanservice.domain.port.out.LoanRepositoryPort;
import org.example.microloanservice.infrastructure.mapper.LoanMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository
public class MongoLoanRepositoryAdapter implements LoanRepositoryPort {
    private final SpringMongoLoanRepository repository;

    public MongoLoanRepositoryAdapter(SpringMongoLoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {
        return LoanMapper.toDomain(repository.save(LoanMapper.toEntity(loan)));
    }

    @Override
    public Optional<Loan> findById(String id) {
        return repository.findById(id).map(LoanMapper::toDomain);
    }

    @Override
    public List<Loan> findByUserId(String userId) {
        return repository.findByUserId(userId).stream().map(LoanMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Loan> findAll() {
        return repository.findAll().stream().map(LoanMapper::toDomain).collect(Collectors.toList());
    }
}
