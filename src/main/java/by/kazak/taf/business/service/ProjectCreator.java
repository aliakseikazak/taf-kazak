package by.kazak.taf.business.service;

import by.kazak.taf.business.model.ProjectApi;
import by.kazak.taf.core.util.MockDataUtils;

public class ProjectCreator {

    public static ProjectApi generateNewProject() {
        return new ProjectApi("INTERNAL", MockDataUtils.generateProjectName());
    }
}
