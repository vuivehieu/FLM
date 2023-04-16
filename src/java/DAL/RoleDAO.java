/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.PaginationModel;
import model.Role;

/**
 *
 * @author PCM
 */
public class RoleDAO extends DBContext {

    public List<Role> getAllRole() {
        List<Role> roleList = new ArrayList<>();
        try {
            String sql = "Select r.rid,"
                    + "rname from role r";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt("rid"), rs.getString("rname"));
                roleList.add(role);
            }
        } catch (SQLException e) {
            System.out.println("RoleDAO -> getAllRole(): " + e);
        }
        return roleList;
    }

    public void ChangeRoleStatus(int id, int status) {
        try {
            String sql = "UPDATE `swp391_bl5_g6`.`role`"
                    + " set role.status = ? "
                    + "where role.rid= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RoleDAO -> ChangeRoleStatus(): " + e);
        }
    }

    public List<Role> getAllSettingByPageAndFilter(PaginationModel pagination) {

        List<Role> list = new ArrayList<>();
        try {
            String sql = "SELECT * from `swp391_bl5_g6`.`role`\n";
            if (!pagination.getFilterType().equals("User Role") || pagination.getFilterStatus() != 3 || !pagination.getSearch().equals("")) {
                sql += " WHERE";
                if (!pagination.getFilterType().equals("User Role")) {
                    sql += " role.type = ?";
                }
                if (pagination.getFilterStatus() != 3) {
                    if (!pagination.getFilterType().equals("User Role")) {
                        sql += " AND";
                    }
                    sql += " role.status = ?";
                }
                if (!pagination.getSearch().equals("")) {
                    if (!pagination.getFilterType().equals("User Role") || pagination.getFilterStatus() != 3) {
                        sql += " AND";
                    }
                    sql += " (role.rname LIKE ? OR role.description LIKE ? OR role.display_order LIKE ? OR role.type LIKE ?)";
                }
            }
            sql += " LIMIT " + (pagination.getPageNo() - 1) * pagination.getPageSize() + "," + pagination.getPageSize() + ";";
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 1;
            if (!pagination.getFilterType().equals("User Role")) {
                st.setInt(i++, pagination.getFilterRole());
            }
            if (pagination.getFilterStatus() != 3) {
                st.setInt(i++, pagination.getFilterStatus());
            }
            if (!pagination.getSearch().equals("")) {
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
            }
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Role role = new Role(rs.getInt("rid"), rs.getString("rname"), rs.getInt("status"), rs.getInt("display_order"), rs.getString("description"), rs.getString("type"));
                list.add(role);
            }

        } catch (SQLException e) {
            System.out.println("RoleDAO -> getAllSettingByPageAndFilter(): " + e);

        }
        return list;
    }

    public int countAllSettingByPageAndFilter(PaginationModel pagination) {

        int count = 0;
        try {
            String sql = "SELECT COUNT(*) as count FROM `swp391_bl5_g6`.`role`";
            if (!pagination.getFilterType().equals("User Role") || pagination.getFilterStatus() != 3 || !pagination.getSearch().equals("")) {
                sql += " WHERE";
                if (!pagination.getFilterType().equals("User Role")) {
                    sql += " role.type = ?";
                }
                if (pagination.getFilterStatus() != 3) {
                    if (!pagination.getFilterType().equals("User Role")) {
                        sql += " AND";
                    }
                    sql += " role.status = ?";
                }
                if (!pagination.getSearch().equals("")) {
                    if (!pagination.getFilterType().equals("User Role") || pagination.getFilterStatus() != 3) {
                        sql += " AND";
                    }
                    sql += " (role.rname LIKE ? OR role.description LIKE ? OR role.display_order LIKE ? OR role.type LIKE ?)";
                }
            }
            sql += ";";
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 1;
            if (!pagination.getFilterType().equals("User Role")) {
                st.setInt(i++, pagination.getFilterRole());
            }
            if (pagination.getFilterStatus() != 3) {
                st.setInt(i++, pagination.getFilterStatus());
            }
            if (!pagination.getSearch().equals("")) {
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
            }
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("RoleDAO -> countAllSettingByPageAndFilter(): " + e);
        }
        return (int) Math.ceil((double) count / pagination.getPageSize());
    }

    public void insertRole(Role r) {
        try {
            String sql = "INSERT INTO `swp391_bl5_g6`.`role`\n"
                    + "(`rid`,\n"
                    + "`rname`,\n"
                    + "`status`,\n"
                    + "`display_order`,\n"
                    + "`description`,\n"
                    + "`type`)\n"
                    + "VALUES\n"
                    + "(null,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, r.getRname());
            st.setInt(2, r.getStatus());
            st.setInt(3, r.getDisplayOrder());
            st.setString(4, r.getDescription());
            st.setString(5, "User Role");
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RoleDAO -> insertRole(): " + e);
        }
    }

    public void updateRole(Role r) {
        try {
            String sql = "UPDATE `swp391_bl5_g6`.`role`\n"
                    + "SET\n"
                    + "`rname` = ?,\n"
                    + "`status` = ?,\n"
                    + "`display_order` = ?,\n"
                    + "`description` = ? \n"
                    + "WHERE `rid` = ?;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, r.getRname());
            st.setInt(2, r.getStatus());
            st.setInt(3, r.getDisplayOrder());
            st.setString(4, r.getDescription());
            st.setInt(5, r.getRid());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("RoleDAO -> updateRole(): " + e);
        }
    }
}
