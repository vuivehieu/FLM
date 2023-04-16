/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Grade;

/**
 *
 * @author phanh
 */
public class GradeDAO extends DBContext {

    private final static String SELECT_QUERY_CHECKPREREQUISITE = "select ga.value from Assessment ass inner join AssessmentDetail assde\n"
            + "on ass.assid = assde.assID inner join Grade ga\n"
            + "on assde.assdeID = ga.assdeid inner join Student st \n"
            + "on ga.stid = st.stid inner join Subject s\n"
            + "on s.subjectCode = ass.subjectCode\n"
            + "where s.subjectCode = ? and st.stid = ? and ga.value <= 0";

    private final static String SELECT_QUERY_CHECKMAINASSESSMENT = "select ga.value from Assessment ass inner join AssessmentDetail assde\n"
            + "on ass.assid = assde.assID inner join Grade ga\n"
            + "on assde.assdeID = ga.assdeid inner join Student st \n"
            + "on ga.stid = st.stid inner join Subject s\n"
            + "on s.subjectCode = ass.subjectCode\n"
            + "where s.subjectCode = ? and st.stid = ? and assde.isMain = 1";

    public Grade getGradeByAssessmentDetailIDAndStid(int assdeID, String stid) {
        try {

            String sql = "SELECT `grade`.`assdeid`,\n"
                    + "    `grade`.`stid`,\n"
                    + "    `grade`.`value`,\n"
                    + "    `grade`.`comment`\n"
                    + "FROM `swp391_bl5_g6`.`grade`\n"
                    + "WHERE `grade`.`assdeid` = ? and  `grade`.`stid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, assdeID);
            st.setString(2, stid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Grade grade = new Grade(rs.getInt("assdeid"), rs.getString("stid"), rs.getFloat("value"), rs.getString("comment"));
                return grade;
            }

        } catch (SQLException e) {
            System.out.println("GradeDAO -> getGradeByAssessmentDetailIDAndStid(): " + e);
        }
        return null;
    }

    public float getTotalGradeOfAssessmentBySubjectCodeANdMore(int slbid, int assid, String stid) {
        try {

            String sql = "select ( sum(`grade`.`value` * `assessmentdetail`.`weight`) ) from `swp391_bl5_g6`.`assessment` inner join `swp391_bl5_g6`.`assessmentdetail`\n"
                    + "                    on `assessment`.`assid` = `assessmentdetail`.`assID` inner join `swp391_bl5_g6`.`grade`\n"
                    + "                    on `assessmentdetail`.`assdeID` = `grade`.`assdeid` inner join `swp391_bl5_g6`.`student` \n"
                    + "                    on `grade`.`stid` = `student`.`stid` inner join `swp391_bl5_g6`.`syllabus`\n"
                    + "                    on `syllabus`.`slbid` = `assessment`.`slbid`\n"
                    + "                    where `syllabus`.`slbid` = ? and `assessment`.`assid` = ? and `student`.`stid` = ?\n"
                    + "                    group by `student`.`stid`, `assessmentdetail`.`weight`;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            st.setInt(2, assid);
            st.setString(3, stid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getFloat(1);
            }

        } catch (SQLException e) {
            System.out.println("GradeDAO -> getTotalGradeOfAssessmentBySubjectCodeANdMore(): " + e);

        }
        return -1;
    }

    public float getAverageGradeOfSubjectBySubjectCodeAndStid(int slbid, String stid) {
        try {

            String sql = "SELECT SUM(t.totalAssessment)\n"
                    + "            FROM (select ( sum(`grade`.`value` * `assessmentdetail`.`weight`) ) as totalAssessment\n"
                    + "					from `swp391_bl5_g6`.`assessment` inner join `swp391_bl5_g6`.`assessmentdetail`\n"
                    + "					on `assessment`.`assid` = `assessmentdetail`.`assID` inner join `swp391_bl5_g6`.`grade`\n"
                    + "					on `assessmentdetail`.`assdeID` = `grade`.`assdeid` inner join `swp391_bl5_g6`.`student` \n"
                    + "					on `grade`.`stid` = `student`.`stid` inner join `swp391_bl5_g6`.`syllabus`\n"
                    + "					on `syllabus`.`slbid` = `assessment`.`slbid`\n"
                    + "					where `syllabus`.`slbid` = ? and `student`.`stid` = ?\n"
                    + "					group by `student`.`stid`, `assessmentdetail`.`weight`) as t;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            st.setString(2, stid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getFloat(1);
            }

        } catch (SQLException e) {
            System.out.println("GradeDAO -> getTotalGradeOfAssessmentBySubjectCodeANdMore(): " + e);

        }
        return -1;
    }

//    public boolean checkPreRequisite(String subjectCode, String stid) {
//        try {
//
//            String sql = SELECT_QUERY_CHECKPREREQUISITE;
//
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, subjectCode);
//            st.setString(2, stid);
//            ResultSet rs = st.executeQuery();
//
//            if (rs.next()) {
//                return false;
//            }
//
//            String sql1 = SELECT_QUERY_CHECKMAINASSESSMENT;
//
//            PreparedStatement st1 = connection.prepareStatement(sql1);
//            st1.setString(1, subjectCode);
//            st1.setString(2, stid);
//            ResultSet rs1 = st1.executeQuery();
//
//            if (rs1.next() && rs1.getFloat("value") < 4) {
//                return false;
//            }
//
//            if (new GradeDAO().getAverageGradeOfSubjectBySubjectCodeAndStid(subjectCode, stid) < 5) {
//                return false;
//            }
//        } catch (SQLException e) {
//            System.out.println("GradeDAO -> getTotalGradeOfAssessmentBySubjectCodeANdMore(): " + e);
//
//        }
//        return true;
//    }
}
