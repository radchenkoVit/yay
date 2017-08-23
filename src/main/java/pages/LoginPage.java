package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    public SelenideElement emailInput = $("#email");
    public SelenideElement passwordInput = $("#login-pass");
    public SelenideElement rememberMeCheckBox = $("#remember-me");
    public SelenideElement signInButton = $(".btn-primary");



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
