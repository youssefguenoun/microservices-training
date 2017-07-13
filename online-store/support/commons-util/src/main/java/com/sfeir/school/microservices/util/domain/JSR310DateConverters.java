package com.sfeir.school.microservices.util.domain;

import org.springframework.core.convert.converter.Converter;

import javax.persistence.AttributeConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public final class JSR310DateConverters {

    private JSR310DateConverters() {
    }

   // @javax.persistence.Converter(autoApply = true)
    public static class LocalDateToDateConverter implements Converter<LocalDate, Date> {

        public static final LocalDateToDateConverter INSTANCE = new LocalDateToDateConverter();

        private LocalDateToDateConverter() {
        }

        @Override
        public Date convert(LocalDate source) {
            return source == null ? null : Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    //@javax.persistence.Converter(autoApply = true)
    public static class DateToLocalDateConverter implements Converter<Date, LocalDate> {

        public static final DateToLocalDateConverter INSTANCE = new DateToLocalDateConverter();

        private DateToLocalDateConverter() {
        }

        @Override
        public LocalDate convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault())
                .toLocalDate();
        }
    }

    @javax.persistence.Converter(autoApply = true)
    public static class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Date> {

        public static final ZonedDateTimeConverter INSTANCE = new ZonedDateTimeConverter();

        public ZonedDateTimeConverter() {
        }


        @Override
        public Date convertToDatabaseColumn(ZonedDateTime attribute) {
            return  attribute == null ? null : Date.from(attribute.toInstant());
        }

        @Override
        public ZonedDateTime convertToEntityAttribute(Date dbData) {
            return dbData == null ? null : ZonedDateTime.ofInstant(dbData.toInstant(), ZoneId.systemDefault());
        }
    }

   // @javax.persistence.Converter(autoApply = true)
    public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

        public static final DateToZonedDateTimeConverter INSTANCE = new DateToZonedDateTimeConverter();

        private DateToZonedDateTimeConverter() {
        }

        @Override
        public ZonedDateTime convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }
    //@javax.persistence.Converter(autoApply = true)
    public static class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {

        public static final LocalDateTimeToDateConverter INSTANCE = new LocalDateTimeToDateConverter();

        private LocalDateTimeToDateConverter() {
        }

        @Override
        public Date convert(LocalDateTime source) {
            return source == null ? null : Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    //@javax.persistence.Converter(autoApply = true)
    public static class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

        public static final DateToLocalDateTimeConverter INSTANCE = new DateToLocalDateTimeConverter();

        private DateToLocalDateTimeConverter() {
        }

        @Override
        public LocalDateTime convert(Date source) {
            return source == null ? null : LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }
}
