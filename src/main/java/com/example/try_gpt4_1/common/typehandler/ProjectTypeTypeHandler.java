package com.example.try_gpt4_1.common.typehandler;

import com.example.try_gpt4_1.common.entity.ProjectType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ProjectType.class)
public class ProjectTypeTypeHandler extends BaseTypeHandler<ProjectType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ProjectType parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public ProjectType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return rs.wasNull() ? null : ProjectType.fromValue(value);
    }

    @Override
    public ProjectType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return rs.wasNull() ? null : ProjectType.fromValue(value);
    }

    @Override
    public ProjectType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return cs.wasNull() ? null : ProjectType.fromValue(value);
    }
}
