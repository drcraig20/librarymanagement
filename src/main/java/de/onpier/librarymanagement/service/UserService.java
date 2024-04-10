package de.onpier.librarymanagement.service;


import de.onpier.librarymanagement.abstraction.service.AbstractService;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import de.onpier.librarymanagement.persistence.model.User;
import de.onpier.librarymanagement.persistence.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService<User> {

    private final BorrowedService borrowedService;

    public UserService(UserRepository userRepository,
                       BorrowedService borrowedService) {
        super(userRepository);

        this.borrowedService = borrowedService;
    }

    public Set<User> getAllBorrowers() {
        return borrowedService.getAll().stream()
                .map(Borrowed::getBorrower)
                .collect(Collectors.toSet());
    }

    public Set<User> getAllActiveUsers() {
        return dataRepository.getAll().stream()
                .filter(userDTO -> userDTO.getMemberTill() == null || userDTO.getMemberTill().isAfter(LocalDate.now()))
                .collect(Collectors.toSet());

    }

    public Set<User> getAllBorrowers(LocalDate date) {
        return borrowedService.getAll().stream()
                .filter(borrowed -> date == null || borrowed.getBorrowedFrom().isEqual(date))
                .map(Borrowed::getBorrower)
                .collect(Collectors.toSet());
    }

}
