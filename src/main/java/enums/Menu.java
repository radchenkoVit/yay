package enums;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public enum Menu {
    CURRENT("CREATION"), OVERDUE("OVERDUE"), DELINQUENT("DELINQUENT");

    private String locator;

    Menu(String locator){
        this.locator = locator;
    }


    private static String commonlocatorPattern = "//ul[contains(@data-type, '%S')]";

    public SelenideElement get(){
        return $x(String.format(commonlocatorPattern, getLocator()));
    }

    private String getLocator(){
        return locator;
    }
}
