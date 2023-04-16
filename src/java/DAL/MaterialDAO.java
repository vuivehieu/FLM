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
import model.Material;

/**
 *
 * @author phanh
 */
public class MaterialDAO extends DBContext {

    public void update(Material m) {
        try {

            String sql = "UPDATE `material` SET\n "
                    + "`slbid` = ?,\n"
                    + "`description` = ?,\n"
                    + "`author` = ?,\n"
                    + "`publisher` = ?,\n"
                    + "`publishedDate` = ?,\n"
                    + "`edition` = ?,\n"
                    + "`ISBN` = ?,\n"
                    + "`isMainMaterial` = ?,\n"
                    + "`isHardCopy` = ?,\n"
                    + "`isOnline` = ?,\n"
                    + "`note` = ?,\n"
                    + "`isActive` = ?\n"
                    + "WHERE `mid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, m.getSlbid());
            st.setString(2, m.getDescription());
            st.setString(3, m.getAuthor());
            st.setString(4, m.getPublisher());
            st.setDate(5, m.getPublishedDate());
            st.setString(6, m.getEdition());
            st.setString(7, m.getISBN());
            st.setBoolean(8, m.isIsMainMaterial());
            st.setBoolean(9, m.isIsHardCopy());
            st.setBoolean(10, m.isIsOnline());
            st.setString(11, m.getNote());
            st.setBoolean(12, m.isIsActive());
            st.setInt(13, m.getMid());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MaterialDAO -> update(): " + e);
        }

    }

    public void add(Material m) {
        try {

            String sql = "INSERT INTO `swp391_bl5_g6`.`material`\n"
                    + "(`slbid`, `description`, `author`, `publisher`, `publishedDate`, `edition`,\n"
                    + "`ISBN`, `isMainMaterial`, `isHardCopy`, `isOnline`, `note`, `isActive`, `accountID`)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, m.getSlbid());
            st.setString(2, m.getDescription());
            st.setString(3, m.getAuthor());
            st.setString(4, m.getPublisher());
            st.setDate(5, m.getPublishedDate());
            st.setString(6, m.getEdition());
            st.setString(7, m.getISBN());
            st.setBoolean(8, m.isIsMainMaterial());
            st.setBoolean(9, m.isIsHardCopy());
            st.setBoolean(10, m.isIsOnline());
            st.setString(11, m.getNote());
            st.setBoolean(12, m.isIsActive());
            st.setInt(13, m.getAccountID());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MaterialDAO -> add(): " + e);

        }
    }

    public List<Material> getMaterialNotInSyllabus(int slbid) {
        List<Material> list = new ArrayList<>();
        try {
            String sql = "SELECT `material`.`mid`,\n"
                    + "    `material`.`slbid`,\n"
                    + "    `material`.`description`,\n"
                    + "    `material`.`author`,\n"
                    + "    `material`.`publisher`,\n"
                    + "    `material`.`publishedDate`,\n"
                    + "    `material`.`edition`,\n"
                    + "    `material`.`ISBN`,\n"
                    + "    `material`.`isMainMaterial`,\n"
                    + "    `material`.`isHardCopy`,\n"
                    + "    `material`.`isOnline`,\n"
                    + "    `material`.`note`,\n"
                    + "    `material`.`isActive`,\n"
                    + "    `material`.`accountID`\n"
                    + "FROM `material`\n"
                    + "WHERE `material`.`mid` not in (SELECT `material`.`mid` FROM `material` WHERE `material`.`slbid` = ?) ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Material material = new Material(rs.getInt("mid"), rs.getInt("slbid"), rs.getString("description"), rs.getString("author"),
                        rs.getString("publisher"), rs.getDate("publishedDate"), rs.getString("edition"), rs.getString("ISBN"),
                        rs.getBoolean("isMainMaterial"), rs.getBoolean("isHardCopy"), rs.getBoolean("isOnline"), rs.getBoolean("isActive"),
                        rs.getString("note"), rs.getInt("accountID"));
                list.add(material);
            }

        } catch (SQLException e) {
            System.out.println("MaterialDAO -> getMaterialNotInSyllabus(): " + e);
        }
        return list;
    }

}
