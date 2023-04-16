/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Assessment;

/**
 *
 * @author phanh
 */
public class AssessmentDAO extends DBContext {

    public void add(Assessment ass) {
        try {

            String sql = "INSERT INTO `swp391_bl5_g6`.`assessment`\n"
                    + "(`category`, `type`, `part`, `weight`, `completionCriteria`, `duration`, `questionType`,\n"
                    + " `noQuestion`, `knowledge_Skill`, `gradingGuide`, `note`, `isAcitve`, `accountId`, `slbid`)\n"
                    + "VALUES\n"
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ass.getCategory());
            st.setString(2, ass.getType());
            st.setInt(3, ass.getPart());
            st.setFloat(4, ass.getWeight());
            st.setString(5, ass.getCompletionCriteria());
            st.setString(6, ass.getDuration());
            st.setString(7, ass.getQuestionType());
            st.setString(8, ass.getNoQuestion());
            st.setString(9, ass.getKnowledgeSkill());
            st.setString(10, ass.getGradingGuide());
            st.setString(11, ass.getNote());
            st.setBoolean(12, ass.isIsActive());
            st.setInt(13, ass.getAccountID());
            st.setInt(14, ass.getSlbid());
            st.executeUpdate();

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("AssessmentDAO -> add(): " + e);
        }
    }

    public void update(Assessment ass) {
        try {

            String sql = "UPDATE `swp391_bl5_g6`.`assessment` SET\n"
                    + "`category` = ?,\n"
                    + "`type` = ?,\n"
                    + "`part` = ?,\n"
                    + "`weight` = ?,\n"
                    + "`completionCriteria` = ?,\n"
                    + "`duration` = ?,\n"
                    + "`questionType` = ?,\n"
                    + "`noQuestion` = ?,\n"
                    + "`knowledge_Skill` = ?,\n"
                    + "`gradingGuide` = ?,\n"
                    + "`note` = ?,\n"
                    + "`isAcitve` = ?\n"
                    + "WHERE `assid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ass.getCategory());
            st.setString(2, ass.getType());
            st.setInt(3, ass.getPart());
            st.setFloat(4, ass.getWeight());
            st.setString(5, ass.getCompletionCriteria());
            st.setString(6, ass.getDuration());
            st.setString(7, ass.getQuestionType());
            st.setString(8, ass.getNoQuestion());
            st.setString(9, ass.getKnowledgeSkill());
            st.setString(10, ass.getGradingGuide());
            st.setString(11, ass.getNote());
            st.setBoolean(12, ass.isIsActive());
            st.setInt(13, ass.getAssid());
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("AssessmentDAO -> update(): " + e);
        }
    }

    public boolean checkCategoryExistInSyllabus(int slbid, String category) {
        try {
            String sql = "SELECT `assessment`.`category`\n"
                    + "FROM `assessment`\n"
                    + "WHERE `assessment`.`slbid` = ? and `assessment`.`category` = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            st.setString(2, category);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("SessionDAO -> checkSesionNoExistInSyllabus(): " + e);
        }
        return false;
    }

}
