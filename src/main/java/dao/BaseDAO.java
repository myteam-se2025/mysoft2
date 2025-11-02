package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO {

    // 🔹 دالة ترجع Connection جاهزة من DbConnection
    protected Connection getConnection() throws SQLException {
        return DbConnection.getConnection();
    }

    // 🔹 دالة لإغلاق الموارد بعد الاستخدام (لتجنب تسريب الذاكرة)
    protected void closeResources(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null && !con.isClosed()) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}