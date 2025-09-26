package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    // Locators (replace with the real ones later)
    private final String usernameField = "input[name='username']";
    private final String passwordField = "input[name='password']";
    private final String loginButton = "text=Login";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLogin(String url) {
        page.navigate(url);
    }

    public void login(String username, String password) {
        page.fill(usernameField, username);
        page.fill(passwordField, password);
        page.click(loginButton);
        // wait until Pengaturan button appears to confirm login success
        page.waitForSelector("text=Pengaturan");
    }
}
