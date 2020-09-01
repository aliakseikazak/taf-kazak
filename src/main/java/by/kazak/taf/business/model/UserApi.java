package by.kazak.taf.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserApi {

    private String accountRole;
    private String defaultProject;
    private String email;
    private String fullName;
    private String login;
    private String password;
    private String projectRole;
}
