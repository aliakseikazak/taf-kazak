package by.kazak.taf.business.page.common.element;

public enum ButtonElement {
    ADD_NEW_DASHBOARD("Add New Dashboard"), ADD("Add"), DELETE("Delete"), UPDATE("Update");

    private final String buttonName;

    ButtonElement(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
