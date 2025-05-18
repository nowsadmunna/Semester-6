package org.example.smartlibrary.service;

import org.example.smartlibrary.Entity.Loan;
import org.example.smartlibrary.Entity.User;
import org.example.smartlibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanService loanService;
    public User registerUser(User user) {
        return userRepository.save(user);
    }
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public long countUsers() {
        return userRepository.count();
    }
    public List<Map<String, Object>> getMostActiveUsers() {
        List<Loan> loans = loanService.getAllLoans(); // Inject and call LoanService

        Map<String, List<Loan>> userLoanMap = loans.stream()
                .collect(Collectors.groupingBy(loan -> loan.getUser().getId()));

        List<Map<String, Object>> result = new ArrayList<>();
        for (var entry : userLoanMap.entrySet()) {
            User user = getUserById(entry.getKey());
            long totalLoans = entry.getValue().size();
            long activeLoans = entry.getValue().stream().filter(l -> "ACTIVE".equalsIgnoreCase(l.getStatus())).count();

            Map<String, Object> map = new HashMap<>();
            map.put("user_id", user.getId());
            map.put("name", user.getName());
            map.put("books_borrowed", totalLoans);
            map.put("current_borrows", activeLoans);

            result.add(map);
        }

        result.sort((a, b) -> Long.compare((long) b.get("books_borrowed"), (long) a.get("books_borrowed")));
        return result;
    }

}
