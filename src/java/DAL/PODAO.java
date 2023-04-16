/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author phanh
 */
public class PODAO extends DBContext {

    public void add(String name, String description, boolean isActive) {
        try {

            String sql = "INSERT INTO `swp391_bl5_g6`.`po` (`poName`,`poDescription`,`isActive`)\n"
                    + "VALUES (?, ?, ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setBoolean(3, isActive);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("PODAO -> add(): " + e);
        }
    }

    public void update(String name, String description, boolean isActive, int poid) {
        try {

            String sql = "UPDATE `swp391_bl5_g6`.`po` SET\n"
                    + "`poName` = ?,\n"
                    + "`poDescription` = ?,\n"
                    + "`isActive` = ?\n"
                    + "WHERE `poid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setBoolean(3, isActive);
            st.setInt(4, poid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("PODAO -> update(): " + e);
        }
    }

    public boolean checkExist(String name, String oldName) {
        try {

            String sql = "SELECT `po`.`poName` FROM `po` WHERE `po`.`poName` like ? and "
                    + "`po`.`poName` not in (SELECT `po`.`poName` FROM `po` WHERE `po`.`poName` like ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            st.setString(2, "%" + oldName + "%");

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("PODAO -> checkExist(): " + e);
        }
        return false;
    }
}
