package pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;

    private final String pengaturanButton = "text=Pengaturan";
    private final String unitkerjaButton = "text=Unit Kerja";

    public HomePage(Page page) {
        this.page = page;
    }

    public void clickPengaturan() {
        page.waitForSelector(pengaturanButton);
        page.click(pengaturanButton);
        // wait until Unit Kerja menu is visible (adjust locator later)
        page.waitForSelector(unitkerjaButton);
        page.click(unitkerjaButton);
    }
}
