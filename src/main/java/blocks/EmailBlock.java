package blocks;

import blocks.subblocks.NotificationBlock;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class EmailBlock implements Block {
    private SelenideElement parent;

    private static String addActionsButton = ".//i[@class='material-icons'][contains(text(), 'add')]";
    private static String notificationMenuLocator = ".//i[@class='material-icons'][contains(text(), 'notifications_active')]";
    private static String repeatCheckBox = ".js-repeat";

    public EmailBlock(SelenideElement parent){
        this.parent = parent;
    }

    public EmailBlock clickRepeatCheckBox(){
        parent.$(repeatCheckBox).click(); return this;
    }

    public NotificationBlock addNotification(){
        parent.$x(addActionsButton).hover();
        parent.$x(notificationMenuLocator).waitUntil(Condition.visible, 1500).click();
        return getNotificationBlock();
    }

    public NotificationBlock getNotificationBlock(){
        return new NotificationBlock($(".sub-reminder.type-notif"));
    }
}
