package enums;

public enum Action {
    CALL, EMAIL;

    Action(){
    }

    private static String commonLocatorPattern = ".//li[@data-type='%S']";

    public String getLocator(){
        return String.format(commonLocatorPattern, name());
    }
}
