package by.kazak.taf.business.apiResources.userController;

public enum UserURIPath {
    GET("get");

    private final String apiPath;

    UserURIPath(String uriPath) {
        this.apiPath = String.format("apiJsonSchemas/user-%s-request.json", uriPath);
    }

    public String getUriPath() {
        return apiPath;
    }
}
