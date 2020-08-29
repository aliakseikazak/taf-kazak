package by.kazak.taf.business.restApi.resources;

public enum UserAPIPath {
    USER_INFO("%s");

    private String apiPath;

    UserAPIPath(String apiPath) {
        this.apiPath = String.format("user/%s", apiPath);
    }

    @Override
    public String toString () {
        return apiPath;
    }
}
