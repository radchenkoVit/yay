package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CollectionWorkflowPage {
    public SelenideElement newWorkFlowButton = $(".new-template > span");
    public SelenideElement editWorkFlow = $(".js-edit-template-name");

    public CollectionWorkflowPage createNewWorkFlow(){
        newWorkFlowButton.click(); return this;
    }

}
