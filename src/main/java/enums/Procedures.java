package enums;

public enum Procedures {
    LABORAL_LIFE_DOC("Laboral Life Doc"),
    MEMBER_ACCREDITATION_DOC("Member Accreditation Doc");

    private final String keyWord;

    Procedures(String word) {
        this.keyWord = word;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
