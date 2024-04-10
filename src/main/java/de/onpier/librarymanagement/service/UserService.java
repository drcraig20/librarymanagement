package de.onpier.librarymanagement.service;


import de.onpier.librarymanagement.abstraction.service.AbstractService;
import de.onpier.librarymanagement.persistence.model.User;
import de.onpier.librarymanagement.persistence.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {


    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

}
