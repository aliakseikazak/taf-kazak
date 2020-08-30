package by.kazak.taf.business.apiResources.userController;

public enum UserFieldPath {
    USER_ID("userId");

    private final String fieldPath;

    UserFieldPath(String fieldPath) {
        this.fieldPath = fieldPath;
    }

    @Override
    public String toString() {
        return fieldPath;
    }
}
