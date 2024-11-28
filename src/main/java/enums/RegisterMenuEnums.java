package enums;

public enum RegisterMenuEnums {
    VALID_USERNAME("([a-zA-Z0-9]*)"),
    STRONG_PASSWORD("([a-zA-Z0-9]*)"),
    ;

    private final String regex;

    RegisterMenuEnums(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
