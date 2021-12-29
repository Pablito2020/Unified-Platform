package services;

public enum Service {
    SS("ss");

    private final String name;

    Service(String name) {
        this.name = name;
    }

    public String getServiceName() {
        return name;
    }
}
