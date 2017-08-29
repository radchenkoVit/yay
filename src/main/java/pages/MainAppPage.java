package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class MainAppPage {
    private SelenideElement collectionWorkFlowTab =
            $x("//li[.//p[contains(text(), 'Collection Workflows')]]");

    public CollectionWorkflowPage navigateToCollectionWorkFlowPage(){
        collectionWorkFlowTab.click();

        return page(CollectionWorkflowPage.class);
    }
}
