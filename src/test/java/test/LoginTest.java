package test;

import blocks.ActionBlock;
import blocks.EmailBlock;
import blocks.subblocks.NotificationBlock;
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

    //days number
    private String zeroDay = "0";
    private String oneDay = "1";
    private String ninthyDays = "90";

    private String daysAfterText = "days after creation date";
    private String daysDueText = "days after due date";
    private String callIconText = "phone";

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

        ActionBlock currentBlocks = new ActionBlock(CURRENT);
        //checks for for call block item
        currentBlocks
                .isBlock(callIconText)
                .creationTestHasText(daysAfterText)
                .dateHasText(zeroDay);

        ActionBlock overdueBlocks = new ActionBlock(OVERDUE);
        workflowPage
                .hoverTo(OVERDUE)
                .clickOn(EMAIL);

        overdueBlocks
                .isBlock(callIconText)
                .creationTestHasText(daysDueText)
                .dateHasText(oneDay);


        ActionBlock delinquentBlocks = new ActionBlock(DELINQUENT);
        //delique check
        workflowPage
                .hoverTo(DELINQUENT)
                .clickOn(EMAIL)
                .hoverTo(DELINQUENT)
                .clickOn(CALL);

        delinquentBlocks
                .isBlock(callIconText)
                .creationTestHasText(daysDueText)
                .dateHasText(ninthyDays)
                .creationTestHasText(1, daysDueText)
                .dateHasText(1, ninthyDays);

        delinquentBlocks.getEmailBlock()
                .clickRepeatCheckBox()
                .repeatDaysInputHasText("5");

        NotificationBlock notificationBlock = delinquentBlocks.getEmailBlock()
                .addNotification();

        //check days number
        notificationBlock
                    .withinDaysInput
                    .shouldHave(Condition.value(oneDay));
        //check text for issue input
        notificationBlock
                .issueInput
                .shouldHave(Condition.text("If last email wasn't opened"));

        workflowPage.saveAction();
    }

}
