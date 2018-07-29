package com.hemalatha.db.performance.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


public class BoolensToIntegerConverter implements AttributeConverter<Boolean,Integer> {
	@Override
	public Integer convertToDatabaseColumn(Boolean aBoolean) {
		return aBoolean? 1:0;
	}

	@Override
	public Boolean convertToEntityAttribute(Integer integer) {
		return integer >0 ;
	}
}
