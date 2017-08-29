package blocks;

import com.codeborne.selenide.SelenideElement;

public class CallBlock implements Block {
    private SelenideElement parent;


    public CallBlock(SelenideElement parent){
        this.parent = parent;
    }

    @Override
    public Block clickRepeatCheckBox() {
        //TODO: implement in future
        return this;
    }
}
