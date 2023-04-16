/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Session;

/**
 *
 * @author phanh
 */
public class SessionDAO extends DBContext {

    public void update(Session s) {
        try {
            String sql = "UPDATE `session` SET \n"
                    + "`slbid` = ?,\n"
                    + "`sesNo` = ?,\n"
                    + "`topic` = ?,\n"
                    + "`learning_Teaching_Type` = ?,\n"
                    + "`ITU` = ?,\n"
                    + "`StudentMaterial` = ?,\n"
                    + "`dowload` = ?,\n"
                    + "`StudentTask` = ?,\n"
                    + "`urls` = ?,\n"
                    + "`note` = ?,\n"
                    + "`isActive` = ?\n"
                    + "WHERE `sesid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, s.getSlbid());
            st.setInt(2, s.getSesNo());
            st.setString(3, s.getTopic());
            st.setString(4, s.getLearningTeachingType());
            st.setString(5, s.getITU());
            st.setString(6, s.getStudentMaterial());
            st.setString(7, s.getDowload());
            st.setString(8, s.getStudentTask());
            st.setString(9, s.getUrls());
            st.setString(10, s.getNote());
            st.setBoolean(11, s.isIsActive());
            st.setInt(12, s.getSesid());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SessionDAO -> update(): " + e);
        }
    }

    public void add(Session s) {
        try {
            String sql = "INSERT INTO `swp391_bl5_g6`.`session`\n"
                    + "(`slbid`, `sesNo`, `topic`, `learning_Teaching_Type`, `ITU`, `StudentMaterial`\n"
                    + ", `dowload`, `StudentTask`, `urls`, `note`, `isActive`, `accountID`)\n"
                    + "VALUES\n"
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, s.getSlbid());
            st.setInt(2, s.getSesNo());
            st.setString(3, s.getTopic());
            st.setString(4, s.getLearningTeachingType());
            st.setString(5, s.getITU());
            st.setString(6, s.getStudentMaterial());
            st.setString(7, s.getDowload());
            st.setString(8, s.getStudentTask());
            st.setString(9, s.getUrls());
            st.setString(10, s.getNote());
            st.setBoolean(11, s.isIsActive());
            st.setInt(12, s.getAccountID());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SessionDAO -> add(): " + e);
        }
    }

    public boolean checkSesionNoExistInSyllabus(int slbid, int sesNo) {
        try {
            String sql = "SELECT `session`.`sesNo`\n"
                    + "FROM `session`\n"
                    + "WHERE `session`.`slbid` = ? and `session`.`sesNo` = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            st.setInt(2, sesNo);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                return true;
            }

        } catch (SQLException e) {
            System.out.println("SessionDAO -> checkSesionNoExistInSyllabus(): " + e);
        }
        return false;
    }
}
