package by.kazak.taf.business.common;

import by.kazak.taf.core.config.ConfigData;

public class BaseTestApi extends Base {

    @Override
    protected void initParams() {
        ConfigData.BROWSER = "none";
    }
}
