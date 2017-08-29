package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    private SelenideElement emailInput = $("#email");
    private SelenideElement passwordInput = $("#login-pass");
    private SelenideElement rememberMeCheckBox = $("#remember-me");
    private SelenideElement signInButton = $(".btn-primary");



    public MainAppPage login(String email, String pass){
        return login(email, pass, false);
    }

    public MainAppPage login(String email, String pass, boolean remember){
        emailInput.setValue(email);
        passwordInput.setValue(pass);
        if (remember) rememberMeCheckBox.click();
        signInButton.click();

        return page(MainAppPage.class);
    }
}
