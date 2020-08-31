package by.kazak.taf.business.service;

import by.kazak.taf.business.model.Dashboard;
import by.kazak.taf.core.util.MockDataUtils;

public class DashboardCreator {

    public static Dashboard generateNewDashboard(boolean isShare) {
        return new Dashboard(MockDataUtils.generateName(), MockDataUtils.generateDescription(), isShare);
    }

    public static Dashboard generateNewDashboard() {
        return new Dashboard(MockDataUtils.generateName());
    }
}
