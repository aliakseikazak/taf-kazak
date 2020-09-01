package by.kazak.taf.business.page.dashboard;

import by.kazak.taf.business.page.common.ModalWindowPage;
import by.kazak.taf.business.page.common.element.ButtonElement;

public class DeleteDashboardModelPage extends ModalWindowPage {

    public AllDashboardsPage clickDeleteBtn() {
        clickSpecificBtn(ButtonElement.DELETE);
        return new AllDashboardsPage();
    }
}
