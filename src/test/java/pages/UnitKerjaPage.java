package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class UnitKerjaPage {
    private final Page page;

    // Locators (replace with real ones later)
    private final String addButton = "text=Tambah";
    //private final String nameField = "input[name='unit_nama']";
    private final String nameField = "//label[contains(text(), 'Nama Unit Kerja')]/ancestor::td/following-sibling::td//input";
    private final String codeDropdown = "//label[contains(text(), 'Kode Unit Kerja')]/ancestor::td/following-sibling::td//input[@name='unit_kode']";
    private final String activeCheckbox = "//label[text()='Aktif']/preceding-sibling::input[@type='button']";
    private final String saveButton = "text=SIMPAN";
    private final String okButton = "//span[text()='OK' and contains(@class, 'x-btn-inner')]/ancestor::a[@role='button']";
    private final String searchField = "//span[text()='Nama Unit Kerja']/ancestor::div[contains(@class, 'x-column-header')]/following::input[@type='text'][1]";

    public UnitKerjaPage(Page page) {
        this.page = page;
    }

    public void addUnitKerja(String name, String code, boolean isActive) {
        page.click(addButton);
        page.waitForSelector(nameField);

        Locator nameFieldLocator = page.locator(nameField);
        nameFieldLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        nameFieldLocator.click(new Locator.ClickOptions().setForce(true));
        nameFieldLocator.fill(name);

        page.waitForSelector(codeDropdown);

        Locator codeDropdownLocator = page.locator(codeDropdown);
        codeDropdownLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        codeDropdownLocator.click(new Locator.ClickOptions().setForce(true));
        codeDropdownLocator.fill(code);

        // Handle checkbox: default is checked (Aktif)
        //page.check(activeCheckbox); // always make sure it's checked. page.uncheck for later
        //coba kalau kosong aja dulu. kan defaultnya sudah checked

        page.click(saveButton);
        page.locator(okButton).click();

        // Go to Tabel view
        page.click("text=Tampilan Tabel"); // adjust locator if needed

        // Search for the unit
        page.fill(searchField, name);
        page.keyboard().press("Enter");

        // Wait for the result to appear
        //page.waitForSelector("text=" + name);
    }

    public void editUnitKerja(String oldName, String newName, String newCode, boolean isActive) {
        page.click("text=" + oldName);
        page.waitForSelector(nameField);

        page.fill(nameField, newName);
        page.selectOption(codeDropdown, newCode);

        if (isActive) {
            if (!page.isChecked(activeCheckbox)) {
                page.check(activeCheckbox);
            }
        } else {
            if (page.isChecked(activeCheckbox)) {
                page.uncheck(activeCheckbox);
            }
        }

        page.click(saveButton);
        page.waitForSelector("text=" + newName);
    }

    public void deleteUnitKerja(String name) {
        page.click("text=" + name);
        page.press("text=" + name, "Delete");
        page.click("text=Yes"); // confirm delete
        page.waitForTimeout(1000);
    }

    public void searchUnitKerja(String keyword) {
        page.fill(searchField, keyword);
        page.press(searchField, "Enter");
        page.waitForSelector("text=" + keyword);
    }
}
