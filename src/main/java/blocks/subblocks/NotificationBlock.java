package blocks.subblocks;

import com.codeborne.selenide.SelenideElement;

public class NotificationBlock {
    public SelenideElement issueInput;
    public SelenideElement withinDaysInput;

    public NotificationBlock(SelenideElement parent){
        issueInput = parent.$("span.issue-type-notif");
        withinDaysInput = parent.$(".sub.js-reminder-input.add-numbers-max");
    }
}
