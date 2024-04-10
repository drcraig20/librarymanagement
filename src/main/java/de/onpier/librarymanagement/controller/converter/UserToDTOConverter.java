package de.onpier.librarymanagement.controller.converter;

import de.onpier.librarymanagement.abstraction.controller.converter.DataToDTOConverter;
import de.onpier.librarymanagement.controller.dto.UserDTO;
import de.onpier.librarymanagement.persistence.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToDTOConverter extends DataToDTOConverter<User, UserDTO> {

    @Override
    public UserDTO fromDataToDTO(User user) {
        return new UserDTO(user.getName(), user.getFirstName(), user.getGender(), user.getMemberSince(), user.getMemberTill());
    }
}
