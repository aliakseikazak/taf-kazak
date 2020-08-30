package by.kazak.taf.business.apiResources.userController;

public enum UserAPIPath {
    USER_INFO("%s");

    private final String apiPath;

    UserAPIPath(String apiPath) {
        this.apiPath = apiPath;
    }

    @Override
    public String toString() {
        return String.format("user/%s", apiPath);
    }
}
