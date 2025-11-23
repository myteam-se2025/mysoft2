package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modl.User;
import modl.Admin;

public class AdminDAO extends BaseDAO {

    // -------------------------
    // CRUD للأدمن
    // -------------------------
    
    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO public.admins (username, password, email) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setString(3, admin.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin findByIdAndEmail(int id, String email) {
        String sql = "SELECT * FROM public.admins WHERE admin_id = ? AND email = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, email.trim().toLowerCase());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // -------------------------
    // عمليات المستخدمين
    // -------------------------

    public boolean addUser(User u) {
        String sql = "INSERT INTO public.users (full_name, email, phone, address, membership_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getFull_name());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPhone());
            ps.setString(4, u.getAddress());
            ps.setDate(5, u.getMembership_date() != null ? new java.sql.Date(u.getMembership_date().getTime())
                                                         : new java.sql.Date(System.currentTimeMillis()));
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> findEligibleUsers() {
        List<User> list = new ArrayList<>();

        String sql =
            "SELECT u.user_id, u.full_name, u.email " +
            "FROM users u " +
            "LEFT JOIN loans l ON u.user_id = l.user_id " +
            "WHERE l.loan_id IS NULL";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User(
                        rs.getString("full_name"),
                        rs.getString("email"),
                        "",
                        "",
                        null
                );
                u.setUser_id(rs.getInt("user_id"));
                list.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    // حذف مستخدم حسب الـ ID
    public boolean deleteUserById(int id) {
        String sql = "DELETE FROM public.users WHERE user_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}