package enums;

public enum Procedures {
    LABORAL_LIFE_DOC("Laboral Life Doc", "SS"),
    MEMBER_ACCREDITATION_DOC("Member Accreditation Doc", "SS");

    private final String keyWord;
    private final String AAPP;

    Procedures(String word, String AAPP) {
        this.keyWord = word;
        this.AAPP = AAPP;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public String getAAPPName() {
        return AAPP;
    }
}
