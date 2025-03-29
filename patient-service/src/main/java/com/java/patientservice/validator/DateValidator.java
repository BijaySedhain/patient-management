package com.java.patientservice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        // Null values are handled by @NotNull

        try {
            LocalDate.parse(s, FORMATTER);
            return true;
        } catch (DateTimeException e){
            return false;
        }
    }
}
