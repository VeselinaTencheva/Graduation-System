package com.nbu.Graduation_System.entity.enums;

public enum DepartmentType {
    INFORMATICS("Informatics"),
    TELECOMMUNICATIONS("Telecommunications"),
    COMPUTER_SCIENCE("Computer Science"),
    INFORMATION_TECHNOLOGIES("Information Technologies"),
    NETWORK_TECHNOLOGIES("Network Technologies"),
    MATHEMATICS("Mathematics"),
    ECONOMICS("Economics"),
    BUSINESS_ADMINISTRATION("Business Administration");

    private final String name;

    DepartmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
