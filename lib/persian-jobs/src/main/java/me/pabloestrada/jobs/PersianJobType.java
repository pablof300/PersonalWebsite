package me.pabloestrada.jobs;

public enum PersianJobType {

    NORMAL("normal");

    private String id;

    PersianJobType(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
