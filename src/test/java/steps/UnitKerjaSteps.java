package steps;

import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitKerjaSteps {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;
    private HomePage homePage;
    private UnitKerjaPage unitKerjaPage;

    @Given("User is logged in and on Unit Kerja page")
    public void user_is_logged_in_and_on_unit_kerja_page() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();

        loginPage = new LoginPage(page);
        homePage = new HomePage(page);
        unitKerjaPage = new UnitKerjaPage(page);

        loginPage.navigateToLogin("https://demo.sipas.id/app/soho/");
        loginPage.login("intern", "password");
        homePage.clickPengaturan();
    }

    @When("User adds a unit with name {string}, code {string}, and active {string}")
    public void user_adds_unit(String name, String code, String active) {
        boolean isActive = active.equalsIgnoreCase("true");
        unitKerjaPage.addUnitKerja(name, code, isActive);
    }

    @When("User edits unit {string} to have name {string}, code {string}, and active {string}")
    public void user_edits_unit(String oldName, String newName, String newCode, String active) {
        boolean isActive = active.equalsIgnoreCase("true");
        unitKerjaPage.editUnitKerja(oldName, newName, newCode, isActive);
    }

    @When("User deletes unit with name {string}")
    public void user_deletes_unit(String name) {
        unitKerjaPage.deleteUnitKerja(name);
    }

    @When("User searches unit with keyword {string}")
    public void user_searches_unit(String keyword) {
        unitKerjaPage.searchUnitKerja(keyword);
    }

    @Then("Unit with name {string} should appear in the list")
    public void unit_should_appear(String name) {
        page.waitForSelector(":text('" + name + "')",
                new Page.WaitForSelectorOptions().setTimeout(10000)); // wait until it shows up
        assertTrue(page.locator(":text('" + name + "')").count() > 0,
                "❌ Could not find unit with name: " + name);
        System.out.println("✅ Found unit with name: " + name);
    }

    @Then("Unit with name {string} should not appear in the list")
    public void unit_should_not_appear(String name) {
        assertTrue(!page.isVisible("text=" + name));
    }
}
