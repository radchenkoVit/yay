package test;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import pages.CollectionWorkflowPage;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static enums.Action.CALL;
import static enums.Menu.CURRENT;

public class LoginTest extends TestRunner {
    private String baseUrl = "https://www.demo.yaypay.com/app/login";
    private String email = "hnfbr@slipry.net";
    private String password = "1VN2IC";


    private String workFlowName = "Test Name for Work Flow";

    @Test
    public void test(){
        CollectionWorkflowPage workflowPage = open(baseUrl, LoginPage.class)
                                                .login(email, password)
                                                .navigateToCollectionWorkFlowPage();

        workflowPage
                .createNewWorkFlow()
                .editWorkFlowName(workFlowName)
                .workFlowNameDiv.should(Condition.text(workFlowName));

        workflowPage
                .hoverTo(CURRENT)
                .clickOn(CALL);
    }

}
