package by.kazak.taf.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {

    @NonNull
    private String userLogin;
    @NonNull
    private String password;
    private String token;
}
