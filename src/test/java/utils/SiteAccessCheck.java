package utils;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteAccessCheck {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) // visible browser for debugging
        );
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    void testSiteLoginAndPengaturan() {
        // 1. Open the website
        page.navigate("https://demo.sipas.id/app/soho/");

        // 2. Fill in login form
        page.fill("input[name='username']", "intern");     // replace with real username
        page.fill("input[name='password']", "password");  // replace with real password

        // 3. Click Login (just look for text "Login")
        page.click("text=Login");

        // 4. After login, wait for Pengaturan button
        page.waitForSelector("text=Pengaturan");
        page.click("text=Pengaturan");

        // 5. Wait 3 seconds (just so we can see what happens)
        page.waitForTimeout(3000);

        // 6. Check Pengaturan is still visible
        assertTrue(page.isVisible("text=Pengaturan"),
                "Expected to still see Pengaturan button after clicking.");

        System.out.println("✅ Login and Pengaturan click test passed.");
    }

}
