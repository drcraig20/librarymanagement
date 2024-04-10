package de.onpier.librarymanagement.service;


import de.onpier.librarymanagement.abstraction.service.AbstractService;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import de.onpier.librarymanagement.persistence.repo.BorrowedRepository;
import org.springframework.stereotype.Service;

@Service
public class BorrowedService extends AbstractService<Borrowed> {


    public BorrowedService(BorrowedRepository borrowedRepository) {
        super(borrowedRepository);
    }

}
