package blocks.subblocks;

import com.codeborne.selenide.SelenideElement;

public class NotificationBlock {
    private SelenideElement parent;

    public SelenideElement issueInput;
    public SelenideElement withinDaysInput;

    public NotificationBlock(SelenideElement parent){
        this.parent = parent;
        issueInput = parent.$("span.issue-type-notif");
        withinDaysInput = parent.$(".sub.js-reminder-input.add-numbers-max");
    }
}
