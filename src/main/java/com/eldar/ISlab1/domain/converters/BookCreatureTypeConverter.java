package com.eldar.ISlab1.domain.converters;

import com.eldar.ISlab1.domain.model.BookCreatureType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BookCreatureTypeConverter implements
        AttributeConverter<BookCreatureType, String> {

    @Override
    public String convertToDatabaseColumn(BookCreatureType attribute) {
        if (attribute == null)
            return null;

        return attribute.name();
    }

    @Override
    public BookCreatureType convertToEntityAttribute(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        return BookCreatureType.valueOf(type);
    }
}