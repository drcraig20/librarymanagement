package de.onpier.librarymanagement.controller;

import de.onpier.librarymanagement.abstraction.controller.AbstractController;
import de.onpier.librarymanagement.controller.converter.BorrowedToDTOConverter;
import de.onpier.librarymanagement.controller.converter.UserToDTOConverter;
import de.onpier.librarymanagement.controller.dto.BorrowedDTO;
import de.onpier.librarymanagement.controller.dto.UserDTO;
import de.onpier.librarymanagement.persistence.model.Borrowed;
import de.onpier.librarymanagement.persistence.model.User;
import de.onpier.librarymanagement.service.BorrowedService;
import de.onpier.librarymanagement.service.DateParser;
import de.onpier.librarymanagement.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping(value = "/borrow/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class BorrowedByUserController extends AbstractController<Borrowed, BorrowedDTO> {
    private final UserService userService;
    private final BorrowedService borrowedService;
    private final DateParser dateParser;
    private final UserToDTOConverter userToDTOConverter;

    public BorrowedByUserController(UserService userService,
                                    DateParser dateParser,
                                    BorrowedToDTOConverter dtoConverter,
                                    UserToDTOConverter userToDTOConverter,
                                    BorrowedService borrowedService) {
        super(dtoConverter, borrowedService);
        this.borrowedService = borrowedService;
        this.userService = userService;
        this.dateParser = dateParser;
        this.userToDTOConverter = userToDTOConverter;
    }


    @GetMapping("/all")
    public Set<UserDTO> findAllBorrower(@RequestParam(name = "date", required = false) String dateBorrowed) {
        LocalDate localDate = dateParser.parseDate(dateBorrowed);
        Set<User> allBorrowers = userService.getAllBorrowers(localDate);
        return userToDTOConverter.fromDataToDTOList(allBorrowers);
    }

    @GetMapping("/active")
    public Set<UserDTO> findAllActiveAndNotBorrowedUsers(@RequestParam(required = false, defaultValue = "false") boolean borrower) {
        Set<User> allActiveUsers = this.userService.getAllActiveUsers();
        if (!borrower) {
            Set<User> allCurrentBorrowers = borrowedService.getAllStillBorrowed().stream()
                    .map(Borrowed::getBorrower)
                    .collect(Collectors.toSet());
            allActiveUsers.removeAll(allCurrentBorrowers);
        }
        return userToDTOConverter.fromDataToDTOList(allActiveUsers);
    }
}
