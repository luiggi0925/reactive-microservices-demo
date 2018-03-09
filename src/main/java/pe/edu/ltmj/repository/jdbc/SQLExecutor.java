package pe.edu.ltmj.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public final class SQLExecutor {

    private SQLExecutor() {

    }

    private static void addParameters(PreparedStatement pstmt,
            Object... parameters) throws SQLException {
        if (parameters != null && parameters.length > 0) {
            int index = 1;
            for (Object param : parameters) {
                pstmt.setObject(index++, param);
            }
        }
    }

    public static <T> List<T> executeQuery(Connection con, String sql,
            Function<ResultSet, T> mapper, Object... parameters)
            throws SQLException {
        List<T> result = new LinkedList<>();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            addParameters(pstmt, parameters);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapper.apply(rs));
                }
            }
        }
        return result;
    }

    public static <T> T executeQuerySingleResult(Connection con, String sql,
            Function<ResultSet, T> mapper, Object... parameters)
            throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            addParameters(pstmt, parameters);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapper.apply(rs);
                }
            }
        }
        return null;
    }

    public static long executeUpdate(Connection con, String sql,
            Object... parameters)
            throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            addParameters(pstmt, parameters);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet keys = pstmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        return keys.getLong(1);
                    }
                }
            }
            return affectedRows;
        }
    }
}
