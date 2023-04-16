/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import model.Combo;

public class ComboDAO extends DBContext {

    public void add(String tag, String nameEN, String nameVI, String note, boolean isActive) {
        try {

            String sql = "INSERT INTO `swp391_bl5_g6`.`combo` (`tag`, `comboName_EN`, `comboName_VI`, `note`, `createDate`, `isActive`)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?) ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, tag);
            st.setString(2, nameEN);
            st.setString(3, nameVI);
            st.setString(4, note);
            Date now = new Date();
            Timestamp timestamp = new Timestamp(now.getTime());
            st.setTimestamp(5, timestamp);
            st.setBoolean(6, isActive);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ComboDAO -> add(): " + e);
        }
    }

    public void updateCombo(Combo combo) {
        try {

            String sql = "UPDATE `swp391_bl5_g6`.`combo` SET \n"
                    + "`tag` = N?,\n"
                    + "`comboName_EN` = N?,\n"
                    + "`comboName_VI` = N?,\n"
                    + "`note` = N?,\n"
                    + "`isActive` = ?\n"
                    + "WHERE `cid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, combo.getTag());
            st.setString(2, combo.getComboName_EN());
            st.setString(3, combo.getComboName_VI());
            st.setString(4, combo.getNote());
            st.setBoolean(5, combo.isIsActive());
            st.setInt(6, combo.getCid());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ComboDAO -> updateCombo(): " + e);
        }
    }

    public void addSubjectToCombo(String[] subjectCode, int cid) {
        try {

            for (String code : subjectCode) {
                String sql = "INSERT INTO `swp391_bl5_g6`.`combo_subject` (`subjectCode`, `cid`, `semester`)\n"
                        + "VALUES (?, ?, ?);";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, code);
                st.setInt(2, cid);
                st.setString(3, getSemesterBySubjectCode(code));
                st.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("ComboDAO -> addSubjectToCombo(): " + e);
        }
    }

    public void delete(int cid, String subjectCode, String semester) {
        try {

            String sql = "DELETE FROM `swp391_bl5_g6`.`combo_subject`\n"
                    + "WHERE    `combo_subject`.`cid` = ? \n"
                    + "         and `combo_subject`.`subjectCode` = ? \n"
                    + "         and `combo_subject`.`semester` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.setString(2, subjectCode);
            st.setString(3, semester);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ComboDAO -> delete(): " + e);
        }
    }

    public String getSemesterBySubjectCode(String subjectCode) {
        try {

            String sql = "SELECT `subject`.`semester`\n"
                    + "FROM `swp391_bl5_g6`.`subject`\n"
                    + "WHERE `subject`.`subjectCode` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, subjectCode);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println("ComboDAO -> getSemesterBySubjectCode(): " + e);
        }
        return "-1";
    }

    public boolean checkTagExist(String tag, String oldTag) {
        try {

            String sql = "SELECT `combo`.`cid`,\n"
                    + "    `combo`.`tag`,\n"
                    + "    `combo`.`comboName_EN`,\n"
                    + "    `combo`.`comboName_VI`,\n"
                    + "    `combo`.`note`,\n"
                    + "    `combo`.`createDate`,\n"
                    + "    `combo`.`isActive`\n"
                    + "FROM `swp391_bl5_g6`.`combo`\n"
                    + "WHERE `combo`.`tag` = ? and `combo`.`tag` not in (?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, tag);
            st.setString(2, oldTag);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("ComboDAO -> checkTagExist(): " + e);
        }
        return false;
    }
}
