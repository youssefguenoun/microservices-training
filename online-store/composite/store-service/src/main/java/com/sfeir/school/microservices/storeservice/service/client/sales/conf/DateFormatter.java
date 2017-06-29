package com.sfeir.school.microservices.storeservice.service.client.sales.conf;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by youssefguenoun on 29/06/2017.
 */
public class DateFormatter implements Formatter<ZonedDateTime> {

    private static final DateTimeFormatter ISOFormatter = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("Z"));
    @Override
    public ZonedDateTime parse(String text, Locale locale) throws ParseException {
        return ZonedDateTime.from(ISOFormatter.parse(text));
    }

    @Override
    public String print(ZonedDateTime date, Locale locale) {
        return date.format(ISOFormatter);
    }
}
