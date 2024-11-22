package data;

public enum LanguageLevel {
    BEGINNER("beginner"),
    INTERMEDIATE("intermediate"),
    ADVANCED("advanced"),
    NATIVE("native");

    private final String value;

    LanguageLevel(String value) {
        this.value = value.toLowerCase();
    }

    public String getValue() {
        return value;
    }
}
