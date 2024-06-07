package com.training.liscenselifecycletracker.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private final DateTimeFormatter dateFormatter;

    public StringToLocalDateConverter() {
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please provide the date in the format: yyyy-MM-dd", e);
        }
    }
}
