package test;

import org.testng.annotations.Test;
import pages.CollectionWorkflowPage;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest extends TestRunner {
    private String baseUrl = "https://www.demo.yaypay.com/app/login";
    private String email = "hnfbr@slipry.net";
    private String password = "1VN2IC";


    @Test
    public void test(){
        CollectionWorkflowPage workflowPage = open(baseUrl, LoginPage.class)
                                                .login(email, password)
                                                .navigateToCollectionWorkFlowPage();

        workflowPage.createNewWorkFlow();
    }

}
