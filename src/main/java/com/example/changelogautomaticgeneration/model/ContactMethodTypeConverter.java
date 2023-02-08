package com.example.changelogautomaticgeneration.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class ContactMethodTypeConverter implements AttributeConverter<ContactMethodType, String> {

    @Override
    public String convertToDatabaseColumn(ContactMethodType contactMethodType) {
        return Optional.ofNullable(contactMethodType).map(ContactMethodType::getCode).orElse(null);
    }

    @Override
    public ContactMethodType convertToEntityAttribute(String value) {
        return Optional.ofNullable(value).map(ContactMethodType::of).orElse(null);
    }
}
