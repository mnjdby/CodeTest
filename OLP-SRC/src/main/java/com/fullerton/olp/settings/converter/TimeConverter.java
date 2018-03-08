package com.fullerton.olp.settings.converter;
import java.sql.Time;

import org.springframework.core.convert.converter.Converter;

public class TimeConverter implements Converter<String, Time> {



    @Override
    public Time convert (String id) {
        try {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
