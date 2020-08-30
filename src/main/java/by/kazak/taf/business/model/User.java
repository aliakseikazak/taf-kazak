package by.kazak.taf.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {

    private final String userLogin;
    private final String password;
    private String token;
}
