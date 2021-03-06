package test;

import blocks.ActionBlock;
import blocks.subblocks.NotificationBlock;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import pages.CollectionWorkflowPage;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static enums.Action.CALL;
import static enums.Action.EMAIL;
import static enums.Menu.*;

public class CreateCollectionWorkFlowTest extends TestRunner {
    private String baseUrl = "https://www.demo.yaypay.com/app/login";
    private String email = "hnfbr@slipry.net";
    private String password = "1VN2IC";

    //days number
    private String zeroDay = "0";
    private String oneDay = "1";
    private String fiveDays = "5";
    private String ninetyDays = "90";

    private String daysAfterText = "days after creation date";
    private String daysDueText = "days after due date";
    private String callIconText = "phone";

    private String workFlowName = "Test Name for Work Flow";

    @Test
    public void createMainCollections(){
        CollectionWorkflowPage workflowPage = open(baseUrl, LoginPage.class)
                                                .login(email, password)
                                                .navigateToCollectionWorkFlowPage();

        workflowPage
                .createNewWorkFlow()
                .editWorkFlowName(workFlowName)
                .workFlowNameDiv.shouldHave(Condition.text(workFlowName));

        workflowPage
                .hoverTo(CURRENT)
                .clickOn(CALL);

        ActionBlock currentBlocks = new ActionBlock(CURRENT);
        //checks for for call block item
        currentBlocks
                .isBlock(callIconText)
                .creationDayInputHasText(daysAfterText)
                .dateInputHasText(zeroDay);

        workflowPage
                .hoverTo(OVERDUE)
                .clickOn(EMAIL);

        ActionBlock overdueBlocks = new ActionBlock(OVERDUE);
        overdueBlocks
                .isBlock(callIconText)
                .creationDayInputHasText(daysDueText)
                .dateInputHasText(oneDay);


        ActionBlock delinquentBlocks = new ActionBlock(DELINQUENT);
        //delique block check
        workflowPage
                .hoverTo(DELINQUENT)
                .clickOn(EMAIL)
                .hoverTo(DELINQUENT)
                .clickOn(CALL);

        delinquentBlocks
                .isBlock(callIconText)
                .creationDayInputHasText(daysDueText)
                .dateInputHasText(ninetyDays)
                .creationDayInputHasText(1, daysDueText)
                .dateInputHasText(1, ninetyDays);

        delinquentBlocks.getEmailBlock()
                .clickRepeatCheckBox()
                .repeatDaysInputHasText(fiveDays);

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
