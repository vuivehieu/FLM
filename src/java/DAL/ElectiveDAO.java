/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Elective;

/**
 *
 * @author phanh
 */
public class ElectiveDAO extends DBContext {

    public void add(Elective el) {
        try {
            String sql = "INSERT INTO `swp391_bl5_g6`.`elective`\n"
                    + "(`ename`, `tag`, `subjectName_EN`, `subjectName_VI`, `note`, `createDate`, `isActive`)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, el.getEname());
            st.setString(2, el.getTag());
            st.setString(3, el.getSubjectName_EN());
            st.setString(4, el.getSubjectName_VI());
            st.setString(5, el.getNote());
            st.setDate(6, el.getCreateDate());
            st.setBoolean(7, el.isIsActive());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ElectiveDAO -> add(): " + e);
        }

    }

    public void update(Elective el) {
        try {
            String sql = "UPDATE `swp391_bl5_g6`.`elective` SET\n"
                    + "`ename` = ?,\n"
                    + "`tag` = ?,\n"
                    + "`subjectName_EN` = ?,\n"
                    + "`subjectName_VI` = ?,\n"
                    + "`note` = ?,\n"
                    + "`isActive` = ?\n"
                    + "WHERE `eid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, el.getEname());
            st.setString(2, el.getTag());
            st.setString(3, el.getSubjectName_EN());
            st.setString(4, el.getSubjectName_VI());
            st.setString(5, el.getNote());
            st.setBoolean(6, el.isIsActive());
            st.setInt(7, el.getEid());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ElectiveDAO -> update(): " + e);
        }
    }

    public void deleteSubject(int eid, String subjectCode) {
        try {
            String sql = "DELETE FROM `swp391_bl5_g6`.`elective_subject`\n"
                    + "WHERE 	`elective_subject`.`eid` = ? AND \n"
                    + "		`elective_subject`.`subjectCode` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, eid);
            st.setString(2, subjectCode);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ElectiveDAO -> deleteSubject(): " + e);
        }
    }

    public void addSubject(String[] subjectCode, int eid) {
        try {

            for (String code : subjectCode) {
                String sql = "INSERT INTO `swp391_bl5_g6`.`elective_subject` (`eid`, `subjectCode`)\n"
                        + "VALUES (? , ?);";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, eid);
                st.setString(2, code);
                
                st.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("ElectiveDAO -> addSubject(): " + e);
        }
    }
}
