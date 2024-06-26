package de.onpier.librarymanagement.persistence.reader;

import de.onpier.librarymanagement.abstraction.persistence.reader.AbstractReader;
import de.onpier.librarymanagement.persistence.model.User;
import de.onpier.librarymanagement.service.DateParser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserReader extends AbstractReader<User> {

    private final DateParser dateParser;

    public UserReader(DateParser dateParser) {
        this.dateParser = dateParser;
    }

    @Override
    public Function<List<Map<String, String>>, Map<String, User>> postProcessor() {
        return users -> users.stream()
                .filter(this::notEmpty)
                .map(map -> {
                    String name = map.get("Name");
                    String firstName = map.get("First name");
                    String gender = map.get("Gender");
                    LocalDate memberSince = dateParser.parseDate(map.get("Member since"));
                    LocalDate memberTill = dateParser.parseDate(map.get("Member till"));
                    return new User(name, firstName, gender, memberSince, memberTill);
                })
                .collect(Collectors.toMap(user ->
                        String.join(",", user.getName(), user.getFirstName()),
                        user -> user));
    }

    private boolean notEmpty(Map<String, String> users) {
        return StringUtils.hasText(users.get("Name")) &&
                StringUtils.hasText(users.get("First name"));
    }
}
