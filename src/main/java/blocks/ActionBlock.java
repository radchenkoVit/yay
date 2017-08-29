package blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.Menu;

//could be Call or Email block, for now as a one block with locators which works for both
public class ActionBlock {
    private SelenideElement parentMenu;

    private String dateInput = ".js-reminder-input.add-numbers-max";
    private String issueCreationInput = ".//div[@class='wrapper']/div[@class='over']/span[last()]";
    private String iconElement = "(//div[contains(@class, 'card-block')]//i[@class='material-icons'])[1]";

    public ActionBlock(Menu menu){
        parentMenu = menu.getParent();
    }

    // commons methods for fast checking data in block
    public ActionBlock dateHasText(String dateNumber){
        return dateHasText(0, dateNumber); //first element
    }

    public ActionBlock dateHasText(int index, String dateNumber){
        parentMenu.$(dateInput, index).shouldHave(Condition.exactValue(dateNumber)); return this;
    }

    public ActionBlock creationTestHasText(String expectedText){
        return creationTestHasText(0, expectedText); //first element
    }

    public ActionBlock creationTestHasText(int index, String expectedText){
        parentMenu.$x(issueCreationInput, index).shouldHave(Condition.text(expectedText)); return this;
    }

    public ActionBlock isBlock(String type){
        parentMenu.$x(iconElement).shouldHave(Condition.text(type));
        return this;
    }
    // end commons methods

    // objects for better working with block
    public EmailBlock getEmailBlock(){
        return getEmailBlock(0);
    }

    public EmailBlock getEmailBlock(int index){
        SelenideElement block = parentMenu.$x(".//div[contains(@class, 'js-remainder-block')][.//i[@class='material-icons'][contains(text(), 'email')]]", index);
        return new EmailBlock(block);
    }

    public Block getCallBlock(){
        return getCallBlock(0);
    }

    public Block getCallBlock(int index){
        SelenideElement block = parentMenu.$x(".//div[contains(@class, 'js-remainder-block')][.//i[@class='material-icons'][contains(text(), 'phone')]]", index);
        return new CallBlock(block);
    }
    //end getting blocks methods
}
