package com.mini.common.enums.converter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhl
 * mybatis string枚举类型转换
 */
public class StringEnumTypeHandler<E extends Enum<?> & StringEnum> extends BaseTypeHandler<StringEnum> {

    private Class<E> clazz;

    public StringEnumTypeHandler(Class<E> enumType) {
        if (enumType == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        clazz = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, StringEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.getStringValue());
    }

    @Override
    public StringEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convert(clazz, rs.getString(columnName));
    }

    @Override
    public StringEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convert(clazz, rs.getString(columnIndex));
    }

    @Override
    public StringEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convert(clazz, cs.getString(columnIndex));
    }

    private <T extends Enum<?> & StringEnum> T convert(Class<T> enumClass, String value) {
        T[] enumConstants = enumClass.getEnumConstants();
        for (T t : enumConstants) {
            if (t.getStringValue().equals(value)) {
                return t;
            }
        }
        return null;
    }

}
