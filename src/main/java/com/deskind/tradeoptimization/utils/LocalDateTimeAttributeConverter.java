package com.deskind.tradeoptimization.utils;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
	
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
    	return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
    }

//    @Override
//    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
//    	return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
//    }
    
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
    	LocalDateTime ldt = sqlTimestamp.toLocalDateTime().minusHours(2l);
        return ldt;
    }
}
