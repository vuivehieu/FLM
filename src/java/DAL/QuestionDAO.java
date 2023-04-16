/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Question;

/**
 *
 * @author phanh
 */
public class QuestionDAO extends DBContext {

    public void update(Question q) {
        try {
            String sql = "UPDATE `question` SET\n"
                    + "`sesid` = ?,\n"
                    + "`qname` = ?,\n"
                    + "`details` = ?,\n"
                    + "`answer` = ?,\n"
                    + "`isActive` = ?\n"
                    + "WHERE `qid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, q.getSesid());
            st.setString(2, q.getQname());
            st.setString(3, q.getDetails());
            st.setString(4, q.getAnswer());
            st.setBoolean(5, q.isIsActive());
            st.setInt(6, q.getQid());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("QuestionDAO -> update(): " + e);
        }

    }

    public void add(Question q) {
        try {
            String sql = "INSERT INTO `swp391_bl5_g6`.`question`\n"
                    + "(`sesid`, `qname`, `details`, `answer`, `isActive`, `accountID`)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, q.getSesid());
            st.setString(2, q.getQname());
            st.setString(3, q.getDetails());
            st.setString(4, q.getAnswer());
            st.setBoolean(5, q.isIsActive());
            st.setInt(6, q.getAccountID());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("QuestionDAO -> add(): " + e);
        }

    }

    public boolean checkQNameExist(int sesid, String qname) {
        try {

            String sql = "SELECT `question`.`qname` FROM `question`\n"
                    + "WHERE `question`.`sesid` = ? and `question`.`qname` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sesid);
            st.setString(2, qname);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("QuestionDAO -> checkQNameExist(): " + e);

        }
        return false;
    }
}
