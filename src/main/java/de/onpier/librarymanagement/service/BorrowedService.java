package de.onpier.librarymanagement.service;


import de.onpier.librarymanagement.abstraction.service.AbstractService;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import de.onpier.librarymanagement.persistence.model.User;
import de.onpier.librarymanagement.persistence.repo.BorrowedRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BorrowedService extends AbstractService<Borrowed> {


    private final BorrowedRepository borrowedRepository;

    public BorrowedService(BorrowedRepository borrowedRepository) {
        super(borrowedRepository);
        this.borrowedRepository = borrowedRepository;
    }

    public Collection<Borrowed> getAllByUser(User user) {
        return borrowedRepository.getAllByUser(user);
    }

    public Collection<Borrowed> getAllStillBorrowed() {
        LocalDate now = LocalDate.now();
        return borrowedRepository.getAll().stream()
                .filter(borrowed -> borrowed.getBorrowedTo() == null || borrowed.getBorrowedTo().isAfter(now))
                .collect(Collectors.toSet());
    }
}
