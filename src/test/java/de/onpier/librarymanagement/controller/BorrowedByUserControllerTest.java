package de.onpier.librarymanagement.controller;

import de.onpier.librarymanagement.controller.converter.BorrowedToDTOConverter;
import de.onpier.librarymanagement.controller.converter.UserToDTOConverter;
import de.onpier.librarymanagement.controller.dto.UserDTO;
import de.onpier.librarymanagement.persistence.model.User;
import de.onpier.librarymanagement.service.BorrowedService;
import de.onpier.librarymanagement.service.DateParser;
import de.onpier.librarymanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BorrowedByUserControllerTest {

    @InjectMocks
    private BorrowedByUserController borrowedByUserController;

    @Mock
    UserService userService;

    @Mock
    DateParser dateParser;

    @Mock
    BorrowedToDTOConverter dtoConverter;

    @Mock
    UserToDTOConverter userToDTOConverter;

    @Mock
    BorrowedService borrowedService;

    MockMvc mockMvc;

    Set<User> users;
    User user;

    @BeforeEach
    void setUp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate start = LocalDate.parse("09/20/2000", dtf);
        user = new User("name1","firstName", "m", start, null);
        User user2 = new User("name2","firstName2", "m", start, null);

        users = new HashSet<>();
        users.add(user);
        users.add(user2);

        lenient().when(userService.getAllBorrowers()).thenReturn(Set.of(user));
        lenient().when(userService.getAllBorrowers(null)).thenReturn(users);
        lenient().when(userService.getAllActiveUsers()).thenReturn(users);

        Set<UserDTO> userDTOS = new UserToDTOConverter().fromDataToDTOList(users);
        lenient().when(userToDTOConverter.fromDataToDTOList(users)).thenReturn(userDTOS);


        mockMvc = MockMvcBuilders.standaloneSetup(borrowedByUserController)
                .build();
    }

    @Test
    @DisplayName("Validate and Test Get All Borrowers")
    void testGetAllBorrowers_thenValidateResponse() throws Exception {
        mockMvc.perform(get("/borrow/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andReturn().getResponse();
    }

    @Test
    @DisplayName("Validate and Test Get All Active Non Borrowers")
    void testGetAllActiveNonBorrowers_thenValidateResponse() throws Exception {
        Set<UserDTO> userDTOS = new UserToDTOConverter().fromDataToDTOList(Set.of(user));
        when(userToDTOConverter.fromDataToDTOList(any())).thenReturn(userDTOS);

        mockMvc.perform(get("/borrow/users/active")
                        .param("borrower", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andReturn().getResponse();
    }
}
