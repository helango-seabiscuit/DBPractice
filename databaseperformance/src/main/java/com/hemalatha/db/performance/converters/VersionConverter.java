package com.hemalatha.db.performance.converters;

import org.hibernate.type.IntegerType;
import org.hibernate.type.VersionType;

import javax.persistence.AttributeConverter;

public class VersionConverter implements AttributeConverter<Integer, IntegerType> {

	@Override
	public IntegerType convertToDatabaseColumn(Integer integer) {
		return new IntegerType();
	}

	@Override
	public Integer convertToEntityAttribute(IntegerType integer) {
		return 1;
	}
}
