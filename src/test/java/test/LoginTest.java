package test;

import blocks.ActionBlock;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import pages.CollectionWorkflowPage;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static enums.Action.CALL;
import static enums.Action.EMAIL;
import static enums.Menu.*;

public class LoginTest extends TestRunner {
    private String baseUrl = "https://www.demo.yaypay.com/app/login";
    private String email = "hnfbr@slipry.net";
    private String password = "1VN2IC";

    private String zeroDate = "0";
    private String desc1 = "days after creation date";
    private String desc2 = "days after due date";

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
                .clickOn(CALL)
                .saveAction();

        ActionBlock currentBlocks = new ActionBlock(CURRENT);

        //checks for for call block item
        currentBlocks
                .isBlock("phone")
                .creationTestHasText(desc1)
                .dateHasText(zeroDate);

        ActionBlock overdueBlocks = new ActionBlock(OVERDUE);
        workflowPage
                .hoverTo(OVERDUE)
                .clickOn(EMAIL)
                .saveAction();

        overdueBlocks
                //.isBlock("phone")
                .creationTestHasText(desc2)
                .dateHasText("1");


        ActionBlock delinquentBlocks = new ActionBlock(DELINQUENT);
        //delique check
        workflowPage
                .hoverTo(DELINQUENT)
                .clickOn(CALL)
                .hoverTo(DELINQUENT)
                .clickOn(EMAIL)
                .saveAction();

        delinquentBlocks
                //.isBlock("phone")
                .creationTestHasText(desc2)
                .dateHasText("90")
                .creationTestHasText(1, desc2)
                .dateHasText(1, "90");
    }

}
