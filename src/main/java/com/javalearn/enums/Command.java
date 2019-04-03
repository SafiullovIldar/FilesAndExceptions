package com.javalearn.enums;

public enum Command {
    HELP("help"),
    CREATE_FILE("createFile"),
    WRITE_FILE("writeFile"),
    CP("cp"),
    DELETE_FILE("deleteFile"),
    ZIP("zip"),
    CAT("cat");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
