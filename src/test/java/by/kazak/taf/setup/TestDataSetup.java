package by.kazak.taf.setup;

import by.kazak.taf.business.model.ProjectApi;
import by.kazak.taf.business.model.User;
import by.kazak.taf.business.model.UserApi;
import by.kazak.taf.business.service.ProjectApiService;
import by.kazak.taf.business.service.ProjectCreator;
import by.kazak.taf.business.service.UserApiService;
import by.kazak.taf.business.service.UserCreator;

public class TestDataSetup {

    public static ProjectApi getNewProject() {
        ProjectApi newProject = ProjectCreator.generateNewProject();
        new ProjectApiService().postCreateProject(UserCreator.getSuperUser(), newProject);
        return newProject;
    }

    public static User getNewUser() {
        UserApi userData = UserCreator.generateNewUserForApi(getNewProject().getProjectName());
        User newUser = new User(userData.getLogin(), userData.getPassword());
        new UserApiService().postCreateUser(UserCreator.getSuperUser(), userData);
        return newUser;
    }
}
