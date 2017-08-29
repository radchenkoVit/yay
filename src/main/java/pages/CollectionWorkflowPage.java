package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import enums.Action;
import enums.Menu;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CollectionWorkflowPage {

    //save state of the page
    public SelenideElement currentMenuTab;


    // accessible elements
    public SelenideElement workFlowNameDiv = $(".js-template-name");


    private SelenideElement newWorkFlowButton = $(".new-template > span");
    private SelenideElement editWorkFlow = $(".js-edit-template-name");
    private SelenideElement workFlowInput = $x("//div[contains(@class, 'js-template-name')]/input");
    private SelenideElement floppyDiskIcon = $("i.js-save-template-name");

    private SelenideElement saveActionButton = $(".js-save-template-btn");
    private SelenideElement cancelActionButton = $("js-cancel-template-btn");

    public CollectionWorkflowPage createNewWorkFlow(){
        newWorkFlowButton.click(); return this;
    }

    public CollectionWorkflowPage editWorkFlowName(String name){
        editWorkFlow.click();
        workFlowInput.clear();
        workFlowInput.setValue(name);
        floppyDiskIcon.click();

        return this;
    }

    public CollectionWorkflowPage hoverTo(Menu menu){
        Selenide.sleep(3000);
        currentMenuTab = menu.get() // here we save state of the page
                .waitUntil(Condition.visible, 5000)
                .hover();

        return this;
    }

    public CollectionWorkflowPage clickOn(Action action){
        currentMenuTab.$x(action.getLocator())
                .waitUntil(Condition.visible, 5000)
                .click();
        return this;
    }

    public CollectionWorkflowPage saveAction(){
        saveActionButton.click(); return this;
    }

    public CollectionWorkflowPage cancelAction(){
        cancelActionButton.click(); return this;
    }
}
