package by.kazak.taf.business.page.settings;

import java.lang.reflect.InvocationTargetException;

import by.kazak.taf.business.page.BasePage;
import by.kazak.taf.business.page.Page;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SettingsPage extends BasePage {

    public SettingsPage(Page page) {
        super(page);
    }

    public SettingsPage() {
        super(Page.SETTINGS);
    }

    public <TPage extends BasePage> TPage openTab(SettingsTab tab, Class<TPage> tabPage) {
        click(tab.toString());
        try {
            return tabPage.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(e);
            e.printStackTrace();
        }
        return null;
    }
}
