package com.fzc.lalw.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonTypeHandler<T> extends BaseTypeHandler<T> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private Class<T> tClass;

    public JsonTypeHandler(Class<T> tClass) {
        if (tClass == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.tClass = tClass;
    }

    private String parseObjToString(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private T parseStringToObj(String content, Class<T> tClass) {
        if (content != null && !content.isEmpty()) {
            try {
                return (T) mapper.readValue(content, tClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, parseObjToString(t));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseStringToObj(resultSet.getString(s), tClass);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseStringToObj(resultSet.getString(i), tClass);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseStringToObj(callableStatement.getString(i), tClass);
    }
}
