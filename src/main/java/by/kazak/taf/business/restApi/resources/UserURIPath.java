package by.kazak.taf.business.restApi.resources;

public enum UserURIPath {
    GET("get");

    private String apiPath;

    UserURIPath(String uriPath) {
        this.apiPath = String.format("apiJsonSchemas/user-%s-request.json", uriPath);
    }

    public String getUriPath() {
        return apiPath;
    }
}
