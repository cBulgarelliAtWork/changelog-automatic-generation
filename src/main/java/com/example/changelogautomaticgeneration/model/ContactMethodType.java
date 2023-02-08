package com.example.changelogautomaticgeneration.model;

import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

@Getter
@ToString
public enum ContactMethodType {
    PHONE("P"), EMAIL("E"), FAX("F"), MAIL("M");
    private final String code;

    ContactMethodType(String code) {
        this.code = code;
    }

    @SuppressWarnings("unused")
    public static ContactMethodType of(String code) {
        return Stream.of(ContactMethodType.values())
                .filter(p -> p.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unexpected value %s", code)));
    }
}
