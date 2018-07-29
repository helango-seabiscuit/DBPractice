package com.hemalatha.db.performance.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


//can be used only in hibernate below 5 (as hibernate 5 provides this feature) and if we want to stick with JPA
@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration,Long> {
    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        System.out.println("Convert to long");
        return  duration.toNanos();
    }

    @Override
    public Duration convertToEntityAttribute(Long aLong) {
        System.out.println("convert to duration");
        return Duration.of(aLong, ChronoUnit.NANOS);
    }
}
