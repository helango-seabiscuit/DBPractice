package com.hemalatha.db.performance.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CaseConverter implements AttributeConverter<String,String> {
	@Override
	public String convertToDatabaseColumn(String s) {
		return s.toUpperCase();
	}

	@Override
	public String convertToEntityAttribute(String s) {
		return s.toLowerCase();
	}
}
