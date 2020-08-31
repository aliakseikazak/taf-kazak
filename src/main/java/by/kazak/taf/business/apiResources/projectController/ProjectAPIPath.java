package by.kazak.taf.business.apiResources.projectController;

public enum ProjectAPIPath {
    PROJECT("");

    private final String apiPath;

    ProjectAPIPath(String apiPath) {
        this.apiPath = apiPath;
    }

    @Override
    public String toString() {
        return String.format("project/%s", apiPath);
    }
}
