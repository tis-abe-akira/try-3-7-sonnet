package com.example.try_gpt4_1.common.typehandler;

import com.example.try_gpt4_1.common.entity.ProjectRank;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ProjectRank.class)
public class ProjectRankTypeHandler extends BaseTypeHandler<ProjectRank> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ProjectRank parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public ProjectRank getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return rs.wasNull() ? null : ProjectRank.fromValue(value);
    }

    @Override
    public ProjectRank getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return rs.wasNull() ? null : ProjectRank.fromValue(value);
    }

    @Override
    public ProjectRank getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return cs.wasNull() ? null : ProjectRank.fromValue(value);
    }
}
