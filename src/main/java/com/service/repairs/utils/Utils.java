package com.service.repairs.utils;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Utils {
    public final static String ORDER_NUMBER_REGEX = "[A-Z][A-Z][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";

    public Instant convertLocalDateToInstant(LocalDate date){
        return date == null ? null : date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public LocalDate convertInstantToLocalDate(Instant date){
        return date == null ? null : LocalDate.ofInstant(date, ZoneId.systemDefault());
    }

    public boolean validateUsingRegex(final String input, final String regex) {
        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public <T> String convertObjectToString (T source){
        return Optional.ofNullable(source).map(Object::toString).orElse("null");
    }
}
