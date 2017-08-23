package test;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.testng.annotations.BeforeClass;

public class TestRunner {

    @BeforeClass
    public void setUp(){
        ChromeDriverManager.getInstance().setup(); // download bin for chrome, set path to system
        Configuration.browser = "chrome";
    }

}
