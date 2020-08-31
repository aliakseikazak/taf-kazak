package by.kazak.taf.business.service;

import by.kazak.taf.business.apiResources.projectController.ProjectAPIPath;
import by.kazak.taf.business.common.BaseApiAssertion;
import by.kazak.taf.business.common.HTTPMethod;
import by.kazak.taf.business.model.ProjectApi;
import by.kazak.taf.business.model.User;
import io.restassured.response.ValidatableResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProjectApiService extends RestApiService implements BaseApiAssertion {

    public ValidatableResponse postCreateProject(User user, ProjectApi project) {
        ValidatableResponse response = HTTPMethod.post(user, setApiPath(ProjectAPIPath.PROJECT), project);
        response.log().ifError();
        return response;
    }
}
