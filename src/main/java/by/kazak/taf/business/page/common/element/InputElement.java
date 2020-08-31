package by.kazak.taf.business.page.common.element;

public enum InputElement {
    LOGIN("Login"), PASSWORD("Password"), ENTER_DASHBOARD_NAME("Enter dashboard name"), ENTER_DASHBOARD_DESCRIPTION("Enter dashboard description");

    private final String inputName;

    InputElement(String inputName) {
        this.inputName = inputName;
    }

    public String getInputName() {
        return inputName;
    }
}
