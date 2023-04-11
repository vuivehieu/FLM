package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.*;

public class DAO extends DBContext {

    List<Syllabus> listSyllabus;
    List<Subject> listSubject;
    List<PreRequisite> listPreReq;

    public List<Subject> getSubjectByCurriculumID(int id) {
        try {
            String sql = "SELECT `subject`.`subjectCode`,\n"
                    + "    `subject`.`subjectName_En`,\n"
                    + "    `subject`.`subjectName_Vi`,\n"
                    + "    `subject`.`semester`,\n"
                    + "    `subject`.`noCredit`,\n"
                    + "    `subject`.`note`,\n"
                    + "    `subject`.`isCombo`,\n"
                    + "    `subject`.`comboName`,\n"
                    + "    `subject`.`isElective`,\n"
                    + "    `subject`.`electiveName`,\n"
                    + "    `subject`.`createDate`,\n"
                    + "    `subject`.`isActive`\n"
                    + "FROM `subject` inner join `curriculum_subject`\n"
                    + "ON `curriculum_subject`.`subjectCode` = `subject`.`subjectCode`\n"
                    + "WHERE `curriculum_subject`.`curid` = ?\n"
                    + "order by `subject`.`semester`, `subject`.`subjectCode`;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            listSubject = new ArrayList<>();
            while (rs.next()) {
                listSyllabus = new ArrayList<>();

                String subjectCode = rs.getString("subjectCode");

                listSyllabus = getSyllabusBySubjectCode(subjectCode);

                Subject subject = new Subject(subjectCode, rs.getString("subjectName_EN"), rs.getString("subjectName_VI"),
                        rs.getString("semester"), rs.getInt("noCredit"), rs.getString("note"), rs.getDate("createDate"),
                        rs.getBoolean("isCombo"), rs.getString("comboName"), rs.getBoolean("isElective"), rs.getString("electiveName"),
                        rs.getBoolean("isActive"), listSyllabus);
                listSubject.add(subject);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSubjectByCurriculumID(): " + e);
        }

        return listSubject;
    }

    public String getSubjectNameENBySubjectCode(String subjectCode) {
        try {
            String sql = "SELECT `subject`.`subjectName_En`\n"
                    + "FROM `subject`\n"
                    + "WHERE `subject`.`subjectCode` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, subjectCode);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSubjectByCurriculumID(): " + e);
        }

        return null;
    }

    public Syllabus getSyllabusBySlbID(int slbid) {
        try {
            String sql = "SELECT `syllabus`.`slbid`,\n"
                    + "    `syllabus`.`subjectCode`,\n"
                    + "    `syllabus`.`decisionNo`,\n"
                    + "    `syllabus`.`slbName_EN`,\n"
                    + "    `syllabus`.`slbName_VI`,\n"
                    + "    `syllabus`.`degreeLevel`,\n"
                    + "    `syllabus`.`timeAllocation`,\n"
                    + "    `syllabus`.`description`,\n"
                    + "    `syllabus`.`studentTask`,\n"
                    + "    `syllabus`.`tool`,\n"
                    + "    `syllabus`.`isApproved`,\n"
                    + "    `syllabus`.`isActive`,\n"
                    + "    `syllabus`.`approvedDate`,\n"
                    + "    `syllabus`.`note`,\n"
                    + "    `syllabus`.`MinAvgMarkToPass`,"
                    + "    `syllabus`.`accountID`\n"
                    + "FROM `syllabus`\n"
                    + "WHERE `syllabus`.`slbid` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));
                List<Material> material = getMaterialBySlbID(slbid);
                List<Session> session = getSessionBySlbID(slbid);
                List<Assessment> assessment = getAssessmentBySlbID(slbid);
                List<LO> lo = getLoBySlbid(slbid);

                listPreReq = new ArrayList<>();
                listPreReq = getPreRequisiteBySlbid(slbid);

                Syllabus syllabus = new Syllabus(slbid, rs.getString("subjectCode"), rs.getString("slbName_EN"),
                        rs.getString("slbName_VI"), rs.getString("degreeLevel"), rs.getString("timeAllocation"), rs.getString("description"),
                        rs.getString("studentTask"), rs.getString("tool"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"),
                        rs.getDate("approvedDate"), rs.getString("note"), rs.getInt("MinAvgMarkToPass"),
                        decision, material, session, assessment, listPreReq, lo, rs.getInt("accountID"));
                return syllabus;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSyllabusBySlbID(): " + e);
        }
        return null;
    }

    public List<LO> getAllLO() {
        List<LO> list = new ArrayList<>();

        try {

            String sql = "SELECT `lo`.`loid`,\n"
                    + "    `lo`.`slbid`,\n"
                    + "    `lo`.`cloName`,\n"
                    + "    `lo`.`cloDetail`,\n"
                    + "    `lo`.`loDetail`,\n"
                    + "    `lo`.`isActive`\n"
                    + "FROM `lo`";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                LO lo = new LO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                list.add(lo);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllLO(): " + e);
        }
        return list;
    }

    public List<LO> GetLoByKey(String key) {
        List<LO> list = new ArrayList<>();

        try {

            String sql = "SELECT `lo`.`loid`,\n"
                    + "    `lo`.`slbid`,\n"
                    + "    `lo`.`cloName`,\n"
                    + "    `lo`.`cloDetail`,\n"
                    + "    `lo`.`loDetail`,\n"
                    + "    `lo`.`isActive`\n"
                    + "FROM `lo`\n"
                    + " WHERE `lo`.`cloName` like ? or `lo`.`cloDetail` like ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            st.setString(2, "%" + key + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                LO lo = new LO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                list.add(lo);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> GetLoByKey(): " + e);
        }
        return list;
    }

    public List<LO> getLoBySlbid(int slbid) {
        List<LO> list = new ArrayList<>();

        try {

            String sql = "SELECT `lo`.`loid`,\n"
                    + "    `lo`.`slbid`,\n"
                    + "    `lo`.`cloName`,\n"
                    + "    `lo`.`cloDetail`,\n"
                    + "    `lo`.`loDetail`,\n"
                    + "    `lo`.`isActive`\n"
                    + "FROM `lo`\n"
                    + "WHERE `lo`.`slbid` = ? ;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                LO lo = new LO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                list.add(lo);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getLoBySlbid(): " + e);
        }
        return list;
    }

    public String getSubjectCodeBySlbid(int slbid) {

        try {
            String sql = "SELECT `syllabus`.`subjectCode`\n"
                    + "FROM `syllabus`\n"
                    + "WHERE `syllabus`.`slbid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSyllabusBySubjectCode(): " + e);
        }
        return null;
    }

    public List<Syllabus> getSyllabusBySubjectCode(String subjectCode) {

        try {
            String sql = "SELECT `syllabus`.`slbid`,\n"
                    + "    `syllabus`.`subjectCode`,\n"
                    + "    `syllabus`.`decisionNo`,\n"
                    + "    `syllabus`.`slbName_EN`,\n"
                    + "    `syllabus`.`slbName_VI`,\n"
                    + "    `syllabus`.`degreeLevel`,\n"
                    + "    `syllabus`.`timeAllocation`,\n"
                    + "    `syllabus`.`description`,\n"
                    + "    `syllabus`.`studentTask`,\n"
                    + "    `syllabus`.`tool`,\n"
                    + "    `syllabus`.`isApproved`,\n"
                    + "    `syllabus`.`isActive`,\n"
                    + "    `syllabus`.`approvedDate`,\n"
                    + "    `syllabus`.`note`,\n"
                    + "    `syllabus`.`MinAvgMarkToPass`,"
                    + "    `syllabus`.`accountID`\n"
                    + "FROM `syllabus`\n"
                    + "WHERE `syllabus`.`subjectCode` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, subjectCode);
            ResultSet rs = st.executeQuery();

            listSyllabus = new ArrayList<>();
            while (rs.next()) {

                int slbid = rs.getInt("slbid");

                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));
                List<Material> material = getMaterialBySlbID(slbid);
                List<Session> session = getSessionBySlbID(slbid);
                List<Assessment> assessment = getAssessmentBySlbID(slbid);
                List<LO> lo = getLoBySlbid(slbid);

                listPreReq = getPreRequisiteBySlbid(slbid);

                Syllabus syllabus = new Syllabus(slbid, rs.getString("subjectCode"), rs.getString("slbName_EN"),
                        rs.getString("slbName_VI"), rs.getString("degreeLevel"), rs.getString("timeAllocation"), rs.getString("description"),
                        rs.getString("studentTask"), rs.getString("tool"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"),
                        rs.getDate("approvedDate"), rs.getString("note"), rs.getInt("MinAvgMarkToPass"),
                        decision, material, session, assessment, listPreReq, lo, rs.getInt("accountID"));
                listSyllabus.add(syllabus);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSyllabusBySubjectCode(): " + e);
        }
        return listSyllabus;
    }

    public List<Syllabus> getSyllabusByKey(String key) {

        try {
            String sql = "SELECT `syllabus`.`slbid`,\n"
                    + "    `syllabus`.`subjectCode`,\n"
                    + "    `syllabus`.`decisionNo`,\n"
                    + "    `syllabus`.`slbName_EN`,\n"
                    + "    `syllabus`.`slbName_VI`,\n"
                    + "    `syllabus`.`degreeLevel`,\n"
                    + "    `syllabus`.`timeAllocation`,\n"
                    + "    `syllabus`.`description`,\n"
                    + "    `syllabus`.`studentTask`,\n"
                    + "    `syllabus`.`tool`,\n"
                    + "    `syllabus`.`isApproved`,\n"
                    + "    `syllabus`.`isActive`,\n"
                    + "    `syllabus`.`approvedDate`,\n"
                    + "    `syllabus`.`note`,\n"
                    + "    `syllabus`.`MinAvgMarkToPass`,"
                    + "    `syllabus`.`accountID`\n"
                    + "FROM `syllabus`\n"
                    + "WHERE `syllabus`.`subjectCode` like ? or `syllabus`.`slbName_EN` like ? or `syllabus`.`slbName_VI` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            st.setString(2, "%" + key + "%");
            st.setString(3, "%" + key + "%");

            ResultSet rs = st.executeQuery();

            listSyllabus = new ArrayList<>();
            while (rs.next()) {

                int slbid = rs.getInt("slbid");

                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));
                List<Material> material = getMaterialBySlbID(slbid);
                List<Session> session = getSessionBySlbID(slbid);
                List<Assessment> assessment = getAssessmentBySlbID(slbid);
                List<LO> lo = getLoBySlbid(slbid);

                listPreReq = getPreRequisiteBySlbid(slbid);

                Syllabus syllabus = new Syllabus(slbid, rs.getString("subjectCode"), rs.getString("slbName_EN"),
                        rs.getString("slbName_VI"), rs.getString("degreeLevel"), rs.getString("timeAllocation"), rs.getString("description"),
                        rs.getString("studentTask"), rs.getString("tool"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"),
                        rs.getDate("approvedDate"), rs.getString("note"), rs.getInt("MinAvgMarkToPass"),
                        decision, material, session, assessment, listPreReq, lo, rs.getInt("accountID"));
                listSyllabus.add(syllabus);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSyllabusByKey(): " + e);
        }
        return listSyllabus;
    }

    public List<Syllabus> getSyllabusByKetAndAccountID(String key, int accountID) {

        try {
            String sql = "SELECT `syllabus`.`slbid`,\n"
                    + "    `syllabus`.`subjectCode`,\n"
                    + "    `syllabus`.`decisionNo`,\n"
                    + "    `syllabus`.`slbName_EN`,\n"
                    + "    `syllabus`.`slbName_VI`,\n"
                    + "    `syllabus`.`degreeLevel`,\n"
                    + "    `syllabus`.`timeAllocation`,\n"
                    + "    `syllabus`.`description`,\n"
                    + "    `syllabus`.`studentTask`,\n"
                    + "    `syllabus`.`tool`,\n"
                    + "    `syllabus`.`isApproved`,\n"
                    + "    `syllabus`.`isActive`,\n"
                    + "    `syllabus`.`approvedDate`,\n"
                    + "    `syllabus`.`note`,\n"
                    + "    `syllabus`.`MinAvgMarkToPass`,"
                    + "    `syllabus`.`accountID`\n"
                    + "FROM `syllabus`\n"
                    + "WHERE `syllabus`.`accountID` = ? and `syllabus`.`subjectCode` like ? or `syllabus`.`slbName_EN` like ? or `syllabus`.`slbName_VI` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            st.setString(2, "%" + key + "%");
            st.setString(3, "%" + key + "%");
            st.setString(4, "%" + key + "%");

            ResultSet rs = st.executeQuery();

            listSyllabus = new ArrayList<>();
            while (rs.next()) {

                int slbid = rs.getInt("slbid");

                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));
                List<Material> material = getMaterialBySlbID(slbid);
                List<Session> session = getSessionBySlbID(slbid);
                List<Assessment> assessment = getAssessmentBySlbID(slbid);
                List<LO> lo = getLoBySlbid(slbid);

                listPreReq = getPreRequisiteBySlbid(slbid);

                Syllabus syllabus = new Syllabus(slbid, rs.getString("subjectCode"), rs.getString("slbName_EN"),
                        rs.getString("slbName_VI"), rs.getString("degreeLevel"), rs.getString("timeAllocation"), rs.getString("description"),
                        rs.getString("studentTask"), rs.getString("tool"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"),
                        rs.getDate("approvedDate"), rs.getString("note"), rs.getInt("MinAvgMarkToPass"),
                        decision, material, session, assessment, listPreReq, lo, rs.getInt("accountID"));
                listSyllabus.add(syllabus);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSyllabusByKey(): " + e);
        }
        return listSyllabus;
    }

    public List<PreRequisite> getAllPreRequisite() {
        List<PreRequisite> list = new ArrayList<>();
        try {
            String sql = "SELECT `prerequisite`.`preID`,\n"
                    + "    `prerequisite`.`slbid`,\n"
                    + "    `prerequisite`.`subjectCode`,\n"
                    + "    `prerequisite`.`preReqCode`\n"
                    + "FROM `prerequisite`;";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PreRequisite preReq = new PreRequisite(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                list.add(preReq);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllPreRequisite(): " + e);
        }
        return list;
    }

    public List<Syllabus> getSyllabusByAccountID(int accountID) {
        List<Syllabus> list = new ArrayList<>();

        try {

            String sql = "SELECT `syllabus`.`slbid`,\n"
                    + "    `syllabus`.`subjectCode`,\n"
                    + "    `syllabus`.`decisionNo`,\n"
                    + "    `syllabus`.`slbName_EN`,\n"
                    + "    `syllabus`.`slbName_VI`,\n"
                    + "    `syllabus`.`degreeLevel`,\n"
                    + "    `syllabus`.`timeAllocation`,\n"
                    + "    `syllabus`.`description`,\n"
                    + "    `syllabus`.`studentTask`,\n"
                    + "    `syllabus`.`tool`,\n"
                    + "    `syllabus`.`isApproved`,\n"
                    + "    `syllabus`.`isActive`,\n"
                    + "    `syllabus`.`approvedDate`,\n"
                    + "    `syllabus`.`note`,\n"
                    + "    `syllabus`.`MinAvgMarkToPass`,"
                    + "    `syllabus`.`accountID`\n"
                    + "FROM `syllabus`\n"
                    + "WHERE `syllabus`.`accountID` = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int slbid = rs.getInt("slbid");

                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));
                List<Material> material = getMaterialBySlbID(slbid);
                List<Session> session = getSessionBySlbID(slbid);
                List<Assessment> assessment = getAssessmentBySlbID(slbid);
                List<LO> lo = getLoBySlbid(slbid);

                listPreReq = getPreRequisiteBySlbid(slbid);

                Syllabus syllabus = new Syllabus(slbid, rs.getString("subjectCode"), rs.getString("slbName_EN"),
                        rs.getString("slbName_VI"), rs.getString("degreeLevel"), rs.getString("timeAllocation"), rs.getString("description"),
                        rs.getString("studentTask"), rs.getString("tool"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"),
                        rs.getDate("approvedDate"), rs.getString("note"), rs.getInt("MinAvgMarkToPass"),
                        decision, material, session, assessment, listPreReq, lo, rs.getInt("accountID"));
                list.add(syllabus);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSyllabusByAccountID(): " + e);

        }
        return list;
    }

    public List<Syllabus> getAllSyllabus() {
        listSyllabus = new ArrayList<>();

        try {
            String sql = "SELECT `syllabus`.`slbid`,\n"
                    + "    `syllabus`.`subjectCode`,\n"
                    + "    `syllabus`.`decisionNo`,\n"
                    + "    `syllabus`.`slbName_EN`,\n"
                    + "    `syllabus`.`slbName_VI`,\n"
                    + "    `syllabus`.`degreeLevel`,\n"
                    + "    `syllabus`.`timeAllocation`,\n"
                    + "    `syllabus`.`description`,\n"
                    + "    `syllabus`.`studentTask`,\n"
                    + "    `syllabus`.`tool`,\n"
                    + "    `syllabus`.`isApproved`,\n"
                    + "    `syllabus`.`isActive`,\n"
                    + "    `syllabus`.`approvedDate`,\n"
                    + "    `syllabus`.`note`,\n"
                    + "    `syllabus`.`MinAvgMarkToPass`,"
                    + "    `syllabus`.`accountID`\n"
                    + "FROM `syllabus`;";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int slbid = rs.getInt("slbid");

                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));
                List<Material> material = getMaterialBySlbID(slbid);
                List<Session> session = getSessionBySlbID(slbid);
                List<Assessment> assessment = getAssessmentBySlbID(slbid);
                List<LO> lo = getLoBySlbid(slbid);

                listPreReq = getPreRequisiteBySlbid(slbid);

                Syllabus syllabus = new Syllabus(slbid, rs.getString("subjectCode"), rs.getString("slbName_EN"),
                        rs.getString("slbName_VI"), rs.getString("degreeLevel"), rs.getString("timeAllocation"), rs.getString("description"),
                        rs.getString("studentTask"), rs.getString("tool"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"),
                        rs.getDate("approvedDate"), rs.getString("note"), rs.getInt("MinAvgMarkToPass"),
                        decision, material, session, assessment, listPreReq, lo, rs.getInt("accountID"));
                listSyllabus.add(syllabus);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllSyllabus(): " + e);
        }
        return listSyllabus;
    }

    public List<PreRequisite> getPreRequisiteBySlbid(int slbid) {
        List<PreRequisite> list = new ArrayList<>();
        try {
            String sql = "SELECT `prerequisite`.`preID`,\n"
                    + "    `prerequisite`.`slbid`,\n"
                    + "    `prerequisite`.`subjectCode`,\n"
                    + "    `prerequisite`.`preReqCode`\n"
                    + "FROM `prerequisite`\n"
                    + "WHERE `prerequisite`.`slbid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PreRequisite preReq = new PreRequisite(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                list.add(preReq);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getPreRequisiteBySlbid(): " + e);
        }
        return list;
    }

    public Decision getDecisionByDecisionNo(String decisionNo) {

        try {
            String sql = "SELECT `decision`.`decisionNo`,\n"
                    + "    `decision`.`decisionName`,\n"
                    + "    `decision`.`approveDate`,\n"
                    + "    `decision`.`note`,\n"
                    + "    `decision`.`createDate`,\n"
                    + "    `decision`.`isActive`,\n"
                    + "    `decision`.`fileName`\n"
                    + "FROM `decision`\n"
                    + "WHERE `decision`.`decisionNo` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, decisionNo);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Decision decision = new Decision(rs.getString("decisionNo"), rs.getString("decisionName"), rs.getDate("approveDate"),
                        rs.getString("note"), rs.getDate("createDate"), rs.getString("fileName"), rs.getBoolean("isActive"));
                return decision;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getDecisionByDecisionNo(): " + e);
        }
        return null;
    }

    public List<Decision> getAllDecision() {
        List<Decision> list = new ArrayList<>();

        try {
            String sql = "SELECT `decision`.`decisionNo`,\n"
                    + "    `decision`.`decisionName`,\n"
                    + "    `decision`.`approveDate`,\n"
                    + "    `decision`.`note`,\n"
                    + "    `decision`.`createDate`,\n"
                    + "    `decision`.`isActive`,\n"
                    + "    `decision`.`fileName`\n"
                    + "FROM `decision`";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Decision decision = new Decision(rs.getString("decisionNo"), rs.getString("decisionName"), rs.getDate("approveDate"),
                        rs.getString("note"), rs.getDate("createDate"), rs.getString("fileName"), rs.getBoolean("isActive"));
                list.add(decision);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllDecision(): " + e);
        }
        return list;
    }

    public List<Decision> getDecisionByBySearch(String key) {
        List<Decision> list = new ArrayList<>();

        try {
            String sql = "SELECT `decision`.`decisionNo`,\n"
                    + "    `decision`.`decisionName`,\n"
                    + "    `decision`.`approveDate`,\n"
                    + "    `decision`.`note`,\n"
                    + "    `decision`.`createDate`,\n"
                    + "    `decision`.`isActive`,\n"
                    + "    `decision`.`fileName`\n"
                    + "FROM `decision`\n"
                    + "WHERE `decision`.`decisionNo` = ?\n"
                    + "OR `decision`.`decisionName` like ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, key);
            st.setString(2, "%" + key + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Decision decision = new Decision(rs.getString("decisionNo"), rs.getString("decisionName"), rs.getDate("approveDate"),
                        rs.getString("note"), rs.getDate("createDate"), rs.getString("fileName"), rs.getBoolean("isActive"));
                list.add(decision);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getDecisionByDecisionNo(): " + e);
        }
        return null;
    }

    public List<Material> getMaterialByAccountID(int accountID) {
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
                    + "WHERE `material`.`accountID` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Material material = new Material(rs.getInt("mid"), rs.getInt("slbid"), rs.getString("description"), rs.getString("author"),
                        rs.getString("publisher"), rs.getDate("publishedDate"), rs.getString("edition"), rs.getString("ISBN"),
                        rs.getBoolean("isMainMaterial"), rs.getBoolean("isHardCopy"), rs.getBoolean("isOnline"), rs.getBoolean("isActive"),
                        rs.getString("note"), rs.getInt("accountID"));
                list.add(material);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getMaterialByAccountID(): " + e);
        }
        return list;
    }

    public List<Material> getMaterialByAccountIDAndKey(int accountID, String key) {
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
                    + "WHERE `material`.`accountID` = ? "
                    + "and `material`.`description` like ? "
                    + "or `material`.`author` like ? "
                    + "or `material`.`publisher` like ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            st.setString(2, "%" + key + "%");
            st.setString(3, "%" + key + "%");
            st.setString(4, "%" + key + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Material material = new Material(rs.getInt("mid"), rs.getInt("slbid"), rs.getString("description"), rs.getString("author"),
                        rs.getString("publisher"), rs.getDate("publishedDate"), rs.getString("edition"), rs.getString("ISBN"),
                        rs.getBoolean("isMainMaterial"), rs.getBoolean("isHardCopy"), rs.getBoolean("isOnline"), rs.getBoolean("isActive"),
                        rs.getString("note"), rs.getInt("accountID"));
                list.add(material);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getMaterialByAccountID(): " + e);
        }
        return list;
    }

    public List<Material> getMaterialBySlbID(int slbid) {
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
                    + "WHERE `material`.`slbid` = ? ;";

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
            System.out.println("DAO -> getMaterialBySlbID(): " + e);
        }
        return list;
    }

    public Material getMaterialByMid(int mid) {
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
                    + "WHERE `material`.`mid` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Material material = new Material(rs.getInt("mid"), rs.getInt("slbid"), rs.getString("description"), rs.getString("author"),
                        rs.getString("publisher"), rs.getDate("publishedDate"), rs.getString("edition"), rs.getString("ISBN"),
                        rs.getBoolean("isMainMaterial"), rs.getBoolean("isHardCopy"), rs.getBoolean("isOnline"), rs.getBoolean("isActive"),
                        rs.getString("note"), rs.getInt("accountID"));
                return material;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getMaterialByMid(): " + e);
        }
        return null;
    }

    public List<Session> getSessionByAccountIDAndKey(int accountID, String key) {
        List<Session> list = new ArrayList<>();
        try {
            String sql = "SELECT `session`.`sesid`,\n"
                    + "    `session`.`slbid`,\n"
                    + "    `session`.`sesNo`,\n"
                    + "    `session`.`topic`,\n"
                    + "    `session`.`learning_Teaching_Type`,\n"
                    + "    `session`.`ITU`,\n"
                    + "    `session`.`StudentMaterial`,\n"
                    + "    `session`.`dowload`,\n"
                    + "    `session`.`StudentTask`,\n"
                    + "    `session`.`urls`,\n"
                    + "    `session`.`note`,\n"
                    + "    `session`.`isActive`,\n"
                    + "    `session`.`accountID`\n"
                    + "FROM `session`\n"
                    + "WHERE `session`.`accountID` = ? and `session`.`topic` like ? or `session`.`learning_Teaching_Type` like ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            st.setString(2, "%" + key + "%");
            st.setString(3, "%" + key + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int sesid = rs.getInt("sesid");

                List<Question> question = getQuestionBySesID(sesid);

                Session session = new Session(sesid, rs.getInt("slbid"), rs.getInt("sesNo"), rs.getString("topic"), rs.getString("learning_Teaching_Type"),
                        rs.getString("ITU"), rs.getString("StudentMaterial"), rs.getString("dowload"), rs.getString("StudentTask"),
                        rs.getString("urls"), rs.getString("note"), rs.getBoolean("isActive"), rs.getInt("accountID"), question);
                list.add(session);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSessionByAccountID(): " + e);
        }
        return list;
    }

    public List<Session> getSessionByAccountID(int accountID) {
        List<Session> list = new ArrayList<>();
        try {
            String sql = "SELECT `session`.`sesid`,\n"
                    + "    `session`.`slbid`,\n"
                    + "    `session`.`sesNo`,\n"
                    + "    `session`.`topic`,\n"
                    + "    `session`.`learning_Teaching_Type`,\n"
                    + "    `session`.`ITU`,\n"
                    + "    `session`.`StudentMaterial`,\n"
                    + "    `session`.`dowload`,\n"
                    + "    `session`.`StudentTask`,\n"
                    + "    `session`.`urls`,\n"
                    + "    `session`.`note`,\n"
                    + "    `session`.`isActive`,\n"
                    + "    `session`.`accountID`\n"
                    + "FROM `session`\n"
                    + "WHERE `session`.`accountID` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int sesid = rs.getInt("sesid");

                List<Question> question = getQuestionBySesID(sesid);

                Session session = new Session(sesid, rs.getInt("slbid"), rs.getInt("sesNo"), rs.getString("topic"), rs.getString("learning_Teaching_Type"),
                        rs.getString("ITU"), rs.getString("StudentMaterial"), rs.getString("dowload"), rs.getString("StudentTask"),
                        rs.getString("urls"), rs.getString("note"), rs.getBoolean("isActive"), rs.getInt("accountID"), question);
                list.add(session);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSessionByAccountID(): " + e);
        }
        return list;
    }

    public List<Question> getQuestionByAccountIDAndKey(int accountID, String key) {
        List<Question> list = new ArrayList<>();
        try {
            String sql = "SELECT `question`.`qid`,\n"
                    + "    `question`.`sesid`,\n"
                    + "    `question`.`qname`,\n"
                    + "    `question`.`details`,\n"
                    + "    `question`.`answer`,\n"
                    + "    `question`.`isActive`,\n"
                    + "    `question`.`accountID`\n"
                    + "FROM `question`\n"
                    + "WHERE `question`.`accountID` = ?  and `question`.`qname` like ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            st.setString(2, "%" + key + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Question question = new Question(rs.getInt("qid"), rs.getInt("sesid"), rs.getString("qname"), rs.getString("details"),
                        rs.getString("answer"), rs.getBoolean("isActive"), rs.getInt("accountID"));

                list.add(question);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getQuestionByAccountIDAndKey(): " + e);
        }
        return list;
    }

    public List<Question> getQuestionByAccountID(int accountID) {
        List<Question> list = new ArrayList<>();
        try {
            String sql = "SELECT `question`.`qid`,\n"
                    + "    `question`.`sesid`,\n"
                    + "    `question`.`qname`,\n"
                    + "    `question`.`details`,\n"
                    + "    `question`.`answer`,\n"
                    + "    `question`.`isActive`,\n"
                    + "    `question`.`accountID`\n"
                    + "FROM `question`\n"
                    + "WHERE `question`.`accountID` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Question question = new Question(rs.getInt("qid"), rs.getInt("sesid"), rs.getString("qname"), rs.getString("details"),
                        rs.getString("answer"), rs.getBoolean("isActive"), rs.getInt("accountID"));

                list.add(question);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getQuestionByAccountID(): " + e);
        }
        return list;
    }

    public List<Session> getSessionBySlbID(int slbid) {
        List<Session> list = new ArrayList<>();
        try {
            String sql = "SELECT `session`.`sesid`,\n"
                    + "    `session`.`slbid`,\n"
                    + "    `session`.`sesNo`,\n"
                    + "    `session`.`topic`,\n"
                    + "    `session`.`learning_Teaching_Type`,\n"
                    + "    `session`.`ITU`,\n"
                    + "    `session`.`StudentMaterial`,\n"
                    + "    `session`.`dowload`,\n"
                    + "    `session`.`StudentTask`,\n"
                    + "    `session`.`urls`,\n"
                    + "    `session`.`note`,\n"
                    + "    `session`.`isActive`,\n"
                    + "    `session`.`accountID`\n"
                    + "FROM `session`\n"
                    + "WHERE `session`.`slbid` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int sesid = rs.getInt("sesid");

                List<Question> question = getQuestionBySesID(sesid);

                Session session = new Session(sesid, rs.getInt("slbid"), rs.getInt("sesNo"), rs.getString("topic"), rs.getString("learning_Teaching_Type"),
                        rs.getString("ITU"), rs.getString("StudentMaterial"), rs.getString("dowload"), rs.getString("StudentTask"),
                        rs.getString("urls"), rs.getString("note"), rs.getBoolean("isActive"), rs.getInt("accountID"), question);
                list.add(session);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSessionBySlbID(): " + e);
        }
        return list;
    }

    public Session getSessionBySesid(int sesid) {
        try {
            String sql = "SELECT `session`.`sesid`,\n"
                    + "    `session`.`slbid`,\n"
                    + "    `session`.`sesNo`,\n"
                    + "    `session`.`topic`,\n"
                    + "    `session`.`learning_Teaching_Type`,\n"
                    + "    `session`.`ITU`,\n"
                    + "    `session`.`StudentMaterial`,\n"
                    + "    `session`.`dowload`,\n"
                    + "    `session`.`StudentTask`,\n"
                    + "    `session`.`urls`,\n"
                    + "    `session`.`note`,\n"
                    + "    `session`.`isActive`,\n"
                    + "    `session`.`accountID`\n"
                    + "FROM `session`\n"
                    + "WHERE `session`.`sesid` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sesid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                List<Question> question = getQuestionBySesID(sesid);

                Session session = new Session(sesid, rs.getInt("slbid"), rs.getInt("sesNo"), rs.getString("topic"), rs.getString("learning_Teaching_Type"),
                        rs.getString("ITU"), rs.getString("StudentMaterial"), rs.getString("dowload"), rs.getString("StudentTask"),
                        rs.getString("urls"), rs.getString("note"), rs.getBoolean("isActive"), rs.getInt("accountID"), question);
                return session;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSessionBySesid(): " + e);
        }
        return null;
    }

    public Question getQuestionByQid(int qid) {
        try {
            String sql = "SELECT `question`.`qid`,\n"
                    + "    `question`.`sesid`,\n"
                    + "    `question`.`qname`,\n"
                    + "    `question`.`details`,\n"
                    + "    `question`.`answer`,\n"
                    + "    `question`.`isActive`,\n"
                    + "    `question`.`accountID`\n"
                    + "FROM `question`\n"
                    + "WHERE `question`.`qid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qid);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Question question = new Question(rs.getInt("qid"), rs.getInt("sesid"), rs.getString("qname"), rs.getString("details"),
                        rs.getString("answer"), rs.getBoolean("isActive"), rs.getInt("accountID"));
                return question;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getQuestionByQid(): " + e);
        }
        return null;
    }

    public List<Question> getQuestionBySesID(int sesid) {
        List<Question> list = new ArrayList<>();
        try {
            String sql = "SELECT `question`.`qid`,\n"
                    + "    `question`.`sesid`,\n"
                    + "    `question`.`qname`,\n"
                    + "    `question`.`details`,\n"
                    + "    `question`.`answer`,\n"
                    + "    `question`.`isActive`,\n"
                    + "    `question`.`accountID`\n"
                    + "FROM `question`\n"
                    + "WHERE `question`.`sesid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sesid);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Question question = new Question(rs.getInt("qid"), rs.getInt("sesid"), rs.getString("qname"), rs.getString("details"),
                        rs.getString("answer"), rs.getBoolean("isActive"), rs.getInt("accountID"));
                list.add(question);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getQuestionBySesID(): " + e);
        }
        return list;
    }

    public List<Assessment> getAssessmentBySlbID(int slbid) {
        List<Assessment> list = new ArrayList<>();
        try {
            String sql = "SELECT `assessment`.`assid`,\n"
                    + "    `assessment`.`slbid`,\n"
                    + "    `assessment`.`category`,\n"
                    + "    `assessment`.`type`,\n"
                    + "    `assessment`.`part`,\n"
                    + "    `assessment`.`weight`,\n"
                    + "    `assessment`.`completionCriteria`,\n"
                    + "    `assessment`.`duration`,\n"
                    + "    `assessment`.`questionType`,\n"
                    + "    `assessment`.`noQuestion`,\n"
                    + "    `assessment`.`knowledge_Skill`,\n"
                    + "    `assessment`.`gradingGuide`,\n"
                    + "    `assessment`.`note`,\n"
                    + "    `assessment`.`isAcitve`,\n"
                    + "    `assessment`.`accountID`\n"
                    + "FROM `assessment`\n"
                    + "WHERE `assessment`.`slbid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, slbid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                List<AssessmentDetail> detail = getAssessmentDetailByAssid(rs.getInt("assid"));

                Assessment assessment = new Assessment(rs.getInt("assid"), rs.getInt("slbid"), rs.getString("category"), rs.getString("type"), rs.getInt("part"),
                        rs.getFloat("weight"), rs.getString("completionCriteria"), rs.getString("duration"), rs.getString("questionType"), rs.getString("noQuestion"),
                        rs.getString("knowledge_Skill"), rs.getString("gradingGuide"), rs.getString("note"), rs.getBoolean("isAcitve"),
                        detail, rs.getInt("accountID"));
                list.add(assessment);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAssessmentBySlbID(): " + e);
        }
        return list;
    }

    public Assessment getAssessmentByassID(int assID) {

        try {
            String sql = "SELECT `assessment`.`assid`,\n"
                    + "    `assessment`.`slbid`,\n"
                    + "    `assessment`.`category`,\n"
                    + "    `assessment`.`type`,\n"
                    + "    `assessment`.`part`,\n"
                    + "    `assessment`.`weight`,\n"
                    + "    `assessment`.`completionCriteria`,\n"
                    + "    `assessment`.`duration`,\n"
                    + "    `assessment`.`questionType`,\n"
                    + "    `assessment`.`noQuestion`,\n"
                    + "    `assessment`.`knowledge_Skill`,\n"
                    + "    `assessment`.`gradingGuide`,\n"
                    + "    `assessment`.`note`,\n"
                    + "    `assessment`.`isAcitve`,\n"
                    + "    `assessment`.`accountID`\n"
                    + "FROM `assessment`\n"
                    + "WHERE `assessment`.`assID` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, assID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                List<AssessmentDetail> detail = getAssessmentDetailByAssid(rs.getInt("assid"));

                Assessment assessment = new Assessment(rs.getInt("assid"), rs.getInt("slbid"), rs.getString("category"), rs.getString("type"), rs.getInt("part"),
                        rs.getFloat("weight"), rs.getString("completionCriteria"), rs.getString("duration"), rs.getString("questionType"), rs.getString("noQuestion"),
                        rs.getString("knowledge_Skill"), rs.getString("gradingGuide"), rs.getString("note"), rs.getBoolean("isAcitve"),
                        detail, rs.getInt("accountID"));
                return assessment;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAssessmentByassID(): " + e);
        }
        return null;
    }

    public List<Assessment> getAssessmentByAccountID(int accountID) {
        List<Assessment> list = new ArrayList<>();
        try {
            String sql = "SELECT `assessment`.`assid`,\n"
                    + "    `assessment`.`slbid`,\n"
                    + "    `assessment`.`category`,\n"
                    + "    `assessment`.`type`,\n"
                    + "    `assessment`.`part`,\n"
                    + "    `assessment`.`weight`,\n"
                    + "    `assessment`.`completionCriteria`,\n"
                    + "    `assessment`.`duration`,\n"
                    + "    `assessment`.`questionType`,\n"
                    + "    `assessment`.`noQuestion`,\n"
                    + "    `assessment`.`knowledge_Skill`,\n"
                    + "    `assessment`.`gradingGuide`,\n"
                    + "    `assessment`.`note`,\n"
                    + "    `assessment`.`isAcitve`,\n"
                    + "    `assessment`.`accountID`\n"
                    + "FROM `assessment`\n"
                    + "WHERE `assessment`.`accountID` = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                List<AssessmentDetail> detail = getAssessmentDetailByAssid(rs.getInt("assid"));

                Assessment assessment = new Assessment(rs.getInt("assid"), rs.getInt("slbid"), rs.getString("category"), rs.getString("type"), rs.getInt("part"),
                        rs.getFloat("weight"), rs.getString("completionCriteria"), rs.getString("duration"), rs.getString("questionType"), rs.getString("noQuestion"),
                        rs.getString("knowledge_Skill"), rs.getString("gradingGuide"), rs.getString("note"), rs.getBoolean("isAcitve"),
                        detail, rs.getInt("accountID"));
                list.add(assessment);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAssessmentByAccountID(): " + e);
        }
        return list;
    }

    public List<Assessment> getAssessmentByAccountIDAndKey(int accountID, String key) {
        List<Assessment> list = new ArrayList<>();
        try {
            String sql = "SELECT `assessment`.`assid`,\n"
                    + "    `assessment`.`slbid`,\n"
                    + "    `assessment`.`category`,\n"
                    + "    `assessment`.`type`,\n"
                    + "    `assessment`.`part`,\n"
                    + "    `assessment`.`weight`,\n"
                    + "    `assessment`.`completionCriteria`,\n"
                    + "    `assessment`.`duration`,\n"
                    + "    `assessment`.`questionType`,\n"
                    + "    `assessment`.`noQuestion`,\n"
                    + "    `assessment`.`knowledge_Skill`,\n"
                    + "    `assessment`.`gradingGuide`,\n"
                    + "    `assessment`.`note`,\n"
                    + "    `assessment`.`isAcitve`,\n"
                    + "    `assessment`.`accountID`\n"
                    + "FROM `assessment`\n"
                    + "WHERE `assessment`.`accountID` = ? \n"
                    + "AND `assessment`.`category` like ? "
                    + "OR `assessment`.`type` like ? "
                    + "OR `assessment`.`isAcitve` = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            st.setString(2, "%" + key + "%");
            st.setString(3, "%" + key + "%");
            st.setBoolean(4, (key.equalsIgnoreCase("active") ? true : key.equalsIgnoreCase("deactive") ? false : false));

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                List<AssessmentDetail> detail = getAssessmentDetailByAssid(rs.getInt("assid"));

                Assessment assessment = new Assessment(rs.getInt("assid"), rs.getInt("slbid"), rs.getString("category"), rs.getString("type"), rs.getInt("part"),
                        rs.getFloat("weight"), rs.getString("completionCriteria"), rs.getString("duration"), rs.getString("questionType"), rs.getString("noQuestion"),
                        rs.getString("knowledge_Skill"), rs.getString("gradingGuide"), rs.getString("note"), rs.getBoolean("isAcitve"),
                        detail, rs.getInt("accountID"));
                list.add(assessment);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAssessmentByAccountID(): " + e);
        }
        return list;
    }

    public List<AssessmentDetail> getAssessmentDetailByAssid(int assid) {
        List<AssessmentDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT `assessmentdetail`.`assdeID`,\n"
                    + "    `assessmentdetail`.`assID`,\n"
                    + "    `assessmentdetail`.`assessmentItem`,\n"
                    + "    `assessmentdetail`.`isMain`,\n"
                    + "    `assessmentdetail`.`weight`\n"
                    + "FROM `assessmentdetail`\n"
                    + "WHERE `assessmentdetail`.`assID` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, assid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                AssessmentDetail detail = new AssessmentDetail(rs.getInt("assdeID"), rs.getInt("assid"), rs.getString("assessmentItem"), rs.getFloat("weight"), rs.getBoolean("isMain"));
                list.add(detail);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAssessmentDetailByAssid(): " + e);
        }
        return list;
    }

    public List<Curriculum> getCurriculumByMajorID(int id) {
        List<Curriculum> list = new ArrayList<>();

        try {
            String sql = "SELECT `curriculum`.`curid`,\n"
                    + "    `curriculum`.`curCode`,\n"
                    + "    `curriculum`.`majorID`,\n"
                    + "    `curriculum`.`decisionNo`,\n"
                    + "    `curriculum`.`curName_EN`,\n"
                    + "    `curriculum`.`curName_VI`,\n"
                    + "    `curriculum`.`description`,\n"
                    + "    `curriculum`.`isApproved`,\n"
                    + "    `curriculum`.`isActive`,\n"
                    + "    `curriculum`.`image`\n"
                    + "FROM `curriculum`\n"
                    + "WHERE `curriculum`.`majorID` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int curid = rs.getInt("curid");

                Major major = getMajorByMajorID(rs.getInt("majorID"));
                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));

                List<Subject> subject = getSubjectByCurriculumID(curid);

                List<PLO> plo = getPLOByCurriculumID(curid);
                List<PO> po = getPOByCurriculumID(curid);
                List<Combo> combo = getComboByCurriculumID(curid);
                List<Elective> elective = getElectiveByCurriculumID(curid);

                Curriculum curriculum = new Curriculum(curid, rs.getString("curCode"), rs.getString("curName_EN"), rs.getString("curName_VI"),
                        rs.getString("description"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"), rs.getString("image"),
                        major, decision, subject, plo, po, combo, elective);

                list.add(curriculum);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getCurriculumByMajorID(): " + e);
        }

        return list;
    }

    public Curriculum getCurriculumByCurid(int curid) {

        try {
            String sql = "SELECT `curriculum`.`curid`,\n"
                    + "    `curriculum`.`curCode`,\n"
                    + "    `curriculum`.`majorID`,\n"
                    + "    `curriculum`.`decisionNo`,\n"
                    + "    `curriculum`.`curName_EN`,\n"
                    + "    `curriculum`.`curName_VI`,\n"
                    + "    `curriculum`.`description`,\n"
                    + "    `curriculum`.`isApproved`,\n"
                    + "    `curriculum`.`isActive`,\n"
                    + "    `curriculum`.`image`\n"
                    + "FROM `curriculum`\n"
                    + "WHERE `curriculum`.`curid` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, curid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                Major major = getMajorByMajorID(rs.getInt("majorID"));
                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));

                List<Subject> subject = getSubjectByCurriculumID(curid);

                List<PLO> plo = getPLOByCurriculumID(curid);
                List<PO> po = getPOByCurriculumID(curid);
                List<Combo> combo = getComboByCurriculumID(curid);
                List<Elective> elective = getElectiveByCurriculumID(curid);

                Curriculum curriculum = new Curriculum(curid, rs.getString("curCode"), rs.getString("curName_EN"), rs.getString("curName_VI"),
                        rs.getString("description"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"), rs.getString("image"),
                        major, decision, subject, plo, po, combo, elective);
                return curriculum;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getCurriculumByCurid(): " + e);
        }

        return null;
    }

    public List<PO_Mapping_PLO> getMappingByCurriculumID(int id) {
        List<PO_Mapping_PLO> list = new ArrayList<>();

        try {
            String sql = "SELECT `po_mapping_plo`.`curid`,\n"
                    + "    `po_mapping_plo`.`ploid`,\n"
                    + "    `po_mapping_plo`.`poid`,\n"
                    + "    `po_mapping_plo`.`isMapping`\n"
                    + "FROM `po_mapping_plo`\n"
                    + "WHERE `po_mapping_plo`.`curid` = ? \n"
                    + "order by `po_mapping_plo`.`ploid`, `po_mapping_plo`.`poid` ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PO_Mapping_PLO map = new PO_Mapping_PLO(rs.getInt("curid"), rs.getInt("ploid"), rs.getInt("poid"), rs.getBoolean("isMapping"));
                list.add(map);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Subject_Mapping_PLO> getMappingSubject_PLOByCurriculumID(int id) {
        List<Subject_Mapping_PLO> list = new ArrayList<>();

        try {
            String sql = "SELECT    `subject_mapping_plo`.`subjectCode`,"
                    + "             `subject_mapping_plo`.`ploid`,"
                    + "             `subject_mapping_plo`.`isMapping`\n"
                    + "FROM `subject_mapping_plo` inner join `plo`\n"
                    + "ON `plo`.`ploid` = `subject_mapping_plo`.`ploid` inner join `plo_curriculum`\n"
                    + "ON `plo`.`ploid` = `plo_curriculum`.`ploid`\n"
                    + "WHERE `plo_curriculum`.`curid` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Subject_Mapping_PLO map = new Subject_Mapping_PLO(rs.getString("subjectCode"), rs.getInt("ploid"), rs.getBoolean("isMapping"));
                list.add(map);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getMappingSubject_PLOByCurriculumID(): " + e);
        }
        return list;
    }

    public List<Curriculum> getAllCurriculum() {
        List<Curriculum> list = new ArrayList<>();

        try {
            String sql = "SELECT `curriculum`.`curid`,\n"
                    + "    `curriculum`.`curCode`,\n"
                    + "    `curriculum`.`majorID`,\n"
                    + "    `curriculum`.`decisionNo`,\n"
                    + "    `curriculum`.`curName_EN`,\n"
                    + "    `curriculum`.`curName_VI`,\n"
                    + "    `curriculum`.`description`,\n"
                    + "    `curriculum`.`isApproved`,\n"
                    + "    `curriculum`.`isActive`,\n"
                    + "    `curriculum`.`image`\n"
                    + "FROM `curriculum` ;";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int curid = rs.getInt("curid");

                Major major = getMajorByMajorID(rs.getInt("majorID"));
                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));

                List<Subject> subject = getSubjectByCurriculumID(curid);

                List<PLO> plo = getPLOByCurriculumID(curid);
                List<PO> po = getPOByCurriculumID(curid);
                List<Combo> combo = getComboByCurriculumID(curid);
                List<Elective> elective = getElectiveByCurriculumID(curid);

                Curriculum curriculum = new Curriculum(curid, rs.getString("curCode"), rs.getString("curName_EN"), rs.getString("curName_VI"),
                        rs.getString("description"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"), rs.getString("image"),
                        major, decision, subject, plo, po, combo, elective);

                list.add(curriculum);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllCurriculum(): " + e);
        }

        return list;
    }

    public List<Curriculum> getCurriculumBySearch(String key) {
        List<Curriculum> list = new ArrayList<>();

        try {
            String sql = "SELECT `curriculum`.`curid`,\n"
                    + "    `curriculum`.`curCode`,\n"
                    + "    `curriculum`.`majorID`,\n"
                    + "    `curriculum`.`decisionNo`,\n"
                    + "    `curriculum`.`curName_EN`,\n"
                    + "    `curriculum`.`curName_VI`,\n"
                    + "    `curriculum`.`description`,\n"
                    + "    `curriculum`.`isApproved`,\n"
                    + "    `curriculum`.`isActive`,\n"
                    + "    `curriculum`.`image`\n"
                    + "FROM `curriculum` "
                    + "where `curriculum`.`curCode` like ? or `curriculum`.`curName_EN` like ? or `curriculum`.`curName_VI` like ?;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            st.setString(2, "%" + key + "%");
            st.setString(3, "%" + key + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int curid = rs.getInt("curid");

                Major major = getMajorByMajorID(rs.getInt("majorID"));
                Decision decision = getDecisionByDecisionNo(rs.getString("decisionNo"));

                List<Subject> subject = getSubjectByCurriculumID(curid);

                List<PLO> plo = getPLOByCurriculumID(curid);
                List<PO> po = getPOByCurriculumID(curid);
                List<Combo> combo = getComboByCurriculumID(curid);
                List<Elective> elective = getElectiveByCurriculumID(curid);

                Curriculum curriculum = new Curriculum(curid, rs.getString("curCode"), rs.getString("curName_EN"), rs.getString("curName_VI"),
                        rs.getString("description"), rs.getBoolean("isApproved"), rs.getBoolean("isActive"), rs.getString("image"),
                        major, decision, subject, plo, po, combo, elective);

                list.add(curriculum);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllCurriculum(): " + e);
        }

        return list;
    }

    public Major getMajorByMajorID(int majorID) {

        try {
            String sql = "SELECT `major`.`majorId`,\n"
                    + "    `major`.`majorName_En`,\n"
                    + "    `major`.`majorName_Vi`,\n"
                    + "    `major`.`keyword`,\n"
                    + "    `major`.`image`\n"
                    + "FROM `major`\n"
                    + "WHERE `major`.`majorId` = ? ;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, majorID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Major major = new Major(majorID, rs.getString("majorName_En"), rs.getString("majorName_Vi"), rs.getString("keyword"),
                        rs.getString("image"));
                return major;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getMajorByMajorID(): " + e);
        }

        return null;
    }

    public List<PLO> getPLOByCurriculumID(int curid) {
        List<PLO> list = new ArrayList<>();

        try {
            String sql = "SELECT `plo`.`ploid`,\n"
                    + "    `plo`.`ploName`,\n"
                    + "    `plo`.`ploDescription`,\n"
                    + "    `plo`.`isActive`\n"
                    + "FROM `plo`inner join `plo_curriculum`\n"
                    + "ON `plo_curriculum`.`ploid` = `plo`.`ploid`\n"
                    + "WHERE `plo_curriculum`.`curid` = ? ;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, curid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PLO plo = new PLO(rs.getInt("ploid"), rs.getString("ploName"), rs.getString("ploDescription"),
                        rs.getBoolean("isActive"));
                list.add(plo);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getPLOByCurriculumID(): " + e);
        }

        return list;
    }

    public PLO getPLOByID(int ploid) {

        try {
            String sql = "SELECT `plo`.`ploid`,\n"
                    + "    `plo`.`ploName`,\n"
                    + "    `plo`.`ploDescription`,\n"
                    + "    `plo`.`isActive`\n"
                    + "FROM `plo`\n"
                    + "WHERE `plo`.`ploid` = ? ;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ploid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                PLO plo = new PLO(rs.getInt("ploid"), rs.getString("ploName"), rs.getString("ploDescription"),
                        rs.getBoolean("isActive"));
                return plo;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getPLOByID(): " + e);
        }

        return null;
    }

    public PO getPOByID(int poid) {

        try {
            String sql = "SELECT `po`.`poid`,\n"
                    + "    `po`.`poName`,\n"
                    + "    `po`.`poDescription`,\n"
                    + "    `po`.`isActive`\n"
                    + "FROM `po`\n"
                    + "WHERE `po`.`poid` = ? ;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, poid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                PO po = new PO(rs.getInt("poid"), rs.getString("poName"), rs.getString("poDescription"),
                        rs.getBoolean("isActive"));
                return po;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getPOByID(): " + e);
        }

        return null;
    }

    public List<PO> getPOByKey(String key) {
        List<PO> list = new ArrayList<>();
        try {
            String sql = "SELECT `po`.`poid`,\n"
                    + "    `po`.`poName`,\n"
                    + "    `po`.`poDescription`,\n"
                    + "    `po`.`isActive`\n"
                    + "FROM `po`\n"
                    + "WHERE `po`.`poName` like ? or `po`.`poDescription` like ? or `po`.`isActive` = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            st.setString(2, "%" + key + "%");
            st.setInt(3, (key.equalsIgnoreCase("block") ? 0 : key.equalsIgnoreCase("active") ? 1 : -1));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PO po = new PO(rs.getInt("poid"), rs.getString("poName"), rs.getString("poDescription"), rs.getBoolean("isActive"));
                list.add(po);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getPOByKey(): " + e);
        }

        return list;
    }

    public List<PLO> getPloByKey(String key) {
        List<PLO> list = new ArrayList<>();

        try {
            String sql = "SELECT `plo`.`ploid`,\n"
                    + "    `plo`.`ploName`,\n"
                    + "    `plo`.`ploDescription`,\n"
                    + "    `plo`.`isActive`\n"
                    + "FROM `plo`\n"
                    + "WHERE `plo`.`ploName` like ? or `plo`.`ploDescription` like ? or `plo`.`isActive` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            st.setString(2, "%" + key + "%");
            st.setInt(3, (key.equalsIgnoreCase("block") ? 0 : key.equalsIgnoreCase("active") ? 1 : -1));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PLO plo = new PLO(rs.getInt("ploid"), rs.getString("ploName"), rs.getString("ploDescription"),
                        rs.getBoolean("isActive"));
                list.add(plo);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getPloByKey(): " + e);
        }

        return list;
    }

    public List<PLO> getAllPLO() {
        List<PLO> list = new ArrayList<>();

        try {
            String sql = "SELECT `plo`.`ploid`,\n"
                    + "    `plo`.`ploName`,\n"
                    + "    `plo`.`ploDescription`,\n"
                    + "    `plo`.`isActive`\n"
                    + "FROM `plo`;";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PLO plo = new PLO(rs.getInt("ploid"), rs.getString("ploName"), rs.getString("ploDescription"),
                        rs.getBoolean("isActive"));
                list.add(plo);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllPLO(): " + e);
        }

        return list;
    }

    public List<PO> getPOByCurriculumID(int curid) {
        List<PO> list = new ArrayList<>();
        try {
            String sql = "SELECT `po`.`poid`,\n"
                    + "    `po`.`poName`,\n"
                    + "    `po`.`poDescription`,\n"
                    + "    `po`.`isActive`\n"
                    + "FROM `po` inner join `po_curriculum`\n"
                    + "ON `po_curriculum`.`poid` = `po`.`poid`"
                    + "WHERE `po_curriculum`.`curid` = ? ;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, curid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PO po = new PO(rs.getInt("poid"), rs.getString("poName"), rs.getString("poDescription"), rs.getBoolean("isActive"));
                list.add(po);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getPOByCurriculumID(): " + e);
        }

        return list;
    }

    public List<PO> getAllPO() {
        List<PO> list = new ArrayList<>();
        try {
            String sql = "SELECT `po`.`poid`,\n"
                    + "    `po`.`poName`,\n"
                    + "    `po`.`poDescription`,\n"
                    + "    `po`.`isActive`\n"
                    + "FROM `po`;";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                PO po = new PO(rs.getInt("poid"), rs.getString("poName"), rs.getString("poDescription"), rs.getBoolean("isActive"));
                list.add(po);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllPO(): " + e);
        }

        return list;
    }

    public List<Combo> getComboByKey(String key) {
        List<Combo> list = new ArrayList<>();

        try {
            String sql = "SELECT `combo`.`cid`,\n"
                    + "       `combo`.`tag`,\n"
                    + "       `combo`.`comboName_EN`,\n"
                    + "       `combo`.`comboName_VI`,\n"
                    + "       `combo`.`note`,\n"
                    + "       `combo`.`createDate`,\n"
                    + "       `combo`.`isActive`\n"
                    + "   FROM `combo` inner join `curriculum_combo`\n"
                    + "   ON `curriculum_combo`.`cid` = `combo`.`cid`\n"
                    + "   WHERE `combo`.`tag` like ? or `combo`.`comboName_EN` like ? or \n"
                    + "         `combo`.`comboName_VI` = ? or `combo`.`isActive` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            st.setString(2, "%" + key + "%");
            st.setString(3, "%" + key + "%");
            st.setInt(4, (key.equalsIgnoreCase("block") ? 0 : key.equalsIgnoreCase("active") ? 1 : -1));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                List<Subject> subject = getSubjectByCid(rs.getInt("cid"));

                Combo combo = new Combo(rs.getInt("cid"), rs.getString("tag"), rs.getString("comboName_EN"), rs.getString("comboName_VI"),
                        rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                list.add(combo);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getComboByKey(): " + e);
        }

        return list;
    }

    public List<Combo> getAllCombo() {
        List<Combo> list = new ArrayList<>();
        try {
            String sql = "SELECT `combo`.`cid`,\n"
                    + "       `combo`.`tag`,\n"
                    + "       `combo`.`comboName_EN`,\n"
                    + "       `combo`.`comboName_VI`,\n"
                    + "       `combo`.`note`,\n"
                    + "       `combo`.`createDate`,\n"
                    + "       `combo`.`isActive`\n"
                    + "   FROM `combo`;";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                List<Subject> subject = getSubjectByCid(rs.getInt("cid"));

                Combo combo = new Combo(rs.getInt("cid"), rs.getString("tag"), rs.getString("comboName_EN"), rs.getString("comboName_VI"),
                        rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                list.add(combo);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllCombo(): " + e);
        }

        return list;
    }

    public List<Combo> getComboByCurriculumID(int curid) {
        List<Combo> list = new ArrayList<>();
        try {
            String sql = "SELECT `combo`.`cid`,\n"
                    + "       `combo`.`tag`,\n"
                    + "       `combo`.`comboName_EN`,\n"
                    + "       `combo`.`comboName_VI`,\n"
                    + "       `combo`.`note`,\n"
                    + "       `combo`.`createDate`,\n"
                    + "       `combo`.`isActive`\n"
                    + "   FROM `combo` inner join `curriculum_combo`\n"
                    + "   ON `curriculum_combo`.`cid` = `combo`.`cid`\n"
                    + "   WHERE `curriculum_combo`.`curid` = ? ;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, curid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                List<Subject> subject = getSubjectByCid(rs.getInt("cid"));

                Combo combo = new Combo(rs.getInt("cid"), rs.getString("tag"), rs.getString("comboName_EN"), rs.getString("comboName_VI"),
                        rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                list.add(combo);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getComboByCurriculumID(): " + e);
        }

        return list;
    }

    public Combo getComboByCID(int cid) {
        try {
            String sql = "SELECT `combo`.`cid`,\n"
                    + "       `combo`.`tag`,\n"
                    + "       `combo`.`comboName_EN`,\n"
                    + "       `combo`.`comboName_VI`,\n"
                    + "       `combo`.`note`,\n"
                    + "       `combo`.`createDate`,\n"
                    + "       `combo`.`isActive`\n"
                    + "   FROM `combo`\n"
                    + "   WHERE `combo`.`cid` = ? ;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                List<Subject> subject = getSubjectByCid(rs.getInt("cid"));

                Combo combo = new Combo(rs.getInt("cid"), rs.getString("tag"), rs.getString("comboName_EN"), rs.getString("comboName_VI"),
                        rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                return combo;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getComboByCurriculumID(): " + e);
        }
        return null;
    }

    public Subject getSubjectBySubjectCode(String subjectCode) {

        try {
            String sql = "SELECT  `subject`.`subjectCode`,\n"
                    + "           `subject`.`subjectName_En`,\n"
                    + "           `subject`.`subjectName_Vi`,\n"
                    + "           `subject`.`semester`,\n"
                    + "           `subject`.`noCredit`,\n"
                    + "           `subject`.`note`,\n"
                    + "           `subject`.`isCombo`,\n"
                    + "           `subject`.`comboName`,\n"
                    + "           `subject`.`isElective`,\n"
                    + "           `subject`.`electiveName`,\n"
                    + "           `subject`.`createDate`,\n"
                    + "           `subject`.`isActive`\n"
                    + "           FROM `subject`\n"
                    + "           WHERE `subject`.`subjectCode` = ?\n"
                    + "           order by `subject`.`semester`, `subject`.`subjectCode`;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, subjectCode);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                listSyllabus = new ArrayList<>();
                listPreReq = new ArrayList<>();

                listSyllabus = getSyllabusBySubjectCode(subjectCode);

                Subject subject = new Subject(subjectCode, rs.getString("subjectName_EN"), rs.getString("subjectName_VI"),
                        rs.getString("semester"), rs.getInt("noCredit"), rs.getString("note"), rs.getDate("createDate"),
                        rs.getBoolean("isCombo"), rs.getString("comboName"), rs.getBoolean("isElective"), rs.getString("electiveName"),
                        rs.getBoolean("isActive"), listSyllabus);

                return subject;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSubjectByCid(): " + e);
        }

        return null;
    }

    public List<Combo> getComboByClick(int curid, String semester) {
        List<Combo> list = new ArrayList<>();

        try {
            String sql = "SELECT `combo`.`cid`,\n"
                    + "    `combo`.`tag`,\n"
                    + "    `combo`.`comboName_EN`,\n"
                    + "    `combo`.`comboName_VI`,\n"
                    + "    `combo`.`note`,\n"
                    + "    `combo`.`createDate`,\n"
                    + "    `combo`.`isActive`\n"
                    + "FROM `subject` inner join `combo_subject`\n"
                    + "ON `combo_subject`.`subjectCode` = `subject`.`subjectCode` inner join `combo`\n"
                    + "ON `combo`.`cid` = `combo_subject`.`cid` inner join  `curriculum_combo`\n"
                    + "ON `combo`.`cid` = `curriculum_combo`.`cid`\n"
                    + "WHERE `subject`.`semester` = `combo_subject`.`semester` and `curriculum_combo`.`curid` = ? and `combo_subject`.`semester` = ?;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, curid);
            st.setString(2, semester);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int cid = rs.getInt("cid");

                List<Subject> subject = getSubjectByCidAndSemester(curid, cid, semester);

                Combo combo = new Combo(rs.getInt("cid"), rs.getString("tag"), rs.getString("comboName_EN"), rs.getString("comboName_VI"),
                        rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                list.add(combo);
            }
        } catch (SQLException e) {
            System.out.println("DAO -> getComboByClick(): " + e);
        }
        return list;
    }

    public List<Subject> getSubjectByCidAndSemester(int curid, int cid, String semester) {

        try {
            String sql = "SELECT `subject`.`subjectCode`,\n"
                    + "    `subject`.`subjectName_En`,\n"
                    + "    `subject`.`subjectName_Vi`,\n"
                    + "    `subject`.`semester`,\n"
                    + "    `subject`.`noCredit`,\n"
                    + "    `subject`.`note`,\n"
                    + "    `subject`.`isCombo`,\n"
                    + "    `subject`.`comboName`,\n"
                    + "    `subject`.`isElective`,\n"
                    + "    `subject`.`electiveName`,\n"
                    + "    `subject`.`createDate`,\n"
                    + "    `subject`.`isActive`\n"
                    + "FROM `subject` inner join `combo_subject`\n"
                    + "ON `combo_subject`.`subjectCode` = `subject`.`subjectCode` inner join `combo`\n"
                    + "ON `combo`.`cid` = `combo_subject`.`cid` inner join  `curriculum_combo`\n"
                    + "ON `combo`.`cid` = `curriculum_combo`.`cid`\n"
                    + "WHERE `subject`.`semester` = `combo_subject`.`semester` and `curriculum_combo`.`curid` = ? and `combo_subject`.`semester` = ? and `combo`.`cid` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, curid);
            st.setString(2, semester);
            st.setInt(3, cid);
            ResultSet rs = st.executeQuery();

            listSubject = new ArrayList<>();
            while (rs.next()) {
                listSyllabus = new ArrayList<>();
                listPreReq = new ArrayList<>();

                String subjectCode = rs.getString("subjectCode");

                listSyllabus = getSyllabusBySubjectCode(subjectCode);

                Subject subject = new Subject(subjectCode, rs.getString("subjectName_EN"), rs.getString("subjectName_VI"),
                        rs.getString("semester"), rs.getInt("noCredit"), rs.getString("note"), rs.getDate("createDate"),
                        rs.getBoolean("isCombo"), rs.getString("comboName"), rs.getBoolean("isElective"), rs.getString("electiveName"),
                        rs.getBoolean("isActive"), listSyllabus);

                listSubject.add(subject);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSubjectByCidAndSemester(): " + e);
        }
        return listSubject;
    }

    public List<Elective> getElectiveByCurriculumID(int curid) {
        List<Elective> list = new ArrayList<>();
        try {
            String sql = "SELECT `elective`.`eid`,\n"
                    + "    `elective`.`ename`,\n"
                    + "    `elective`.`tag`,\n"
                    + "    `elective`.`subjectName_EN`,\n"
                    + "    `elective`.`subjectName_VI`,\n"
                    + "    `elective`.`note`,\n"
                    + "    `elective`.`createDate`,\n"
                    + "    `elective`.`isActive`\n"
                    + "FROM `elective` inner join `curriculum_elective`\n"
                    + "ON `elective`.`eid` = `curriculum_elective`. `eid`\n"
                    + "where `curriculum_elective`.`curid` = ?;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, curid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                List<Subject> subject = getSubjectByEid(rs.getInt("eid"));

                Elective elective = new Elective(rs.getInt("eid"), rs.getString("ename"), rs.getString("tag"), rs.getString("subjectName_EN"),
                        rs.getString("subjectName_VI"), rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                list.add(elective);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getElectiveByCurriculumID(): " + e);
        }

        return list;
    }

    public List<Elective> getAllElective() {
        List<Elective> list = new ArrayList<>();
        try {
            String sql = "SELECT `elective`.`eid`,\n"
                    + "    `elective`.`ename`,\n"
                    + "    `elective`.`tag`,\n"
                    + "    `elective`.`subjectName_EN`,\n"
                    + "    `elective`.`subjectName_VI`,\n"
                    + "    `elective`.`note`,\n"
                    + "    `elective`.`createDate`,\n"
                    + "    `elective`.`isActive`\n"
                    + "FROM `elective`;";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                List<Subject> subject = getSubjectByEid(rs.getInt("eid"));

                Elective elective = new Elective(rs.getInt("eid"), rs.getString("ename"), rs.getString("tag"), rs.getString("subjectName_EN"),
                        rs.getString("subjectName_VI"), rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                list.add(elective);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllElective(): " + e);
        }

        return list;
    }

    public List<Elective> getElectiveByKey(String key) {
        List<Elective> list = new ArrayList<>();
        try {
            String sql = "SELECT `elective`.`eid`,\n"
                    + "    `elective`.`ename`,\n"
                    + "    `elective`.`tag`,\n"
                    + "    `elective`.`subjectName_EN`,\n"
                    + "    `elective`.`subjectName_VI`,\n"
                    + "    `elective`.`note`,\n"
                    + "    `elective`.`createDate`,\n"
                    + "    `elective`.`isActive`\n"
                    + "FROM `elective`"
                    + "WHERE `elective`.`ename` like ? \n"
                    + "         or  `elective`.`tag` like ? \n "
                    + "         or `elective`.`subjectName_EN` like ? \n"
                    + "         or `elective`.`subjectName_VI` like ? ;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            st.setString(2, "%" + key + "%");
            st.setString(3, "%" + key + "%");
            st.setString(4, "%" + key + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                List<Subject> subject = getSubjectByEid(rs.getInt("eid"));

                Elective elective = new Elective(rs.getInt("eid"), rs.getString("ename"), rs.getString("tag"), rs.getString("subjectName_EN"),
                        rs.getString("subjectName_VI"), rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                list.add(elective);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getElectiveByKey(): " + e);
        }

        return list;
    }

    public List<Subject> getSubjectByCid(int cid) {
        try {
            String sql = "SELECT `subject`.`subjectCode`,\n"
                    + "           `subject`.`subjectName_En`,\n"
                    + "           `subject`.`subjectName_Vi`,\n"
                    + "           `subject`.`semester`,\n"
                    + "           `subject`.`noCredit`,\n"
                    + "           `subject`.`note`,\n"
                    + "           `subject`.`isCombo`,\n"
                    + "           `subject`.`comboName`,\n"
                    + "           `subject`.`isElective`,\n"
                    + "           `subject`.`electiveName`,\n"
                    + "           `subject`.`createDate`,\n"
                    + "           `subject`.`isActive`\n"
                    + "           FROM `subject` inner join `combo_subject`\n"
                    + "           ON `combo_subject`.`subjectCode` = `subject`.`subjectCode`\n"
                    + "           WHERE `combo_subject`.`cid` = ? \n"
                    + "           order by `subject`.`semester`, `subject`.`subjectCode`;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();

            listSubject = new ArrayList<>();
            while (rs.next()) {
                listSyllabus = new ArrayList<>();
                listPreReq = new ArrayList<>();

                String subjectCode = rs.getString("subjectCode");

                listSyllabus = getSyllabusBySubjectCode(subjectCode);

                Subject subject = new Subject(subjectCode, rs.getString("subjectName_EN"), rs.getString("subjectName_VI"),
                        rs.getString("semester"), rs.getInt("noCredit"), rs.getString("note"), rs.getDate("createDate"),
                        rs.getBoolean("isCombo"), rs.getString("comboName"), rs.getBoolean("isElective"), rs.getString("electiveName"),
                        rs.getBoolean("isActive"), listSyllabus);

                listSubject.add(subject);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSubjectByCid(): " + e);
        }

        return listSubject;
    }

    public List<Subject> getSubjectNotInCombo(int cid) {
        try {
            String sql = "SELECT `subject`.`subjectCode`,\n"
                    + "    `subject`.`subjectName_En`,\n"
                    + "    `subject`.`subjectName_Vi`,\n"
                    + "    `subject`.`semester`,\n"
                    + "    `subject`.`noCredit`,\n"
                    + "    `subject`.`note`,\n"
                    + "    `subject`.`isCombo`,\n"
                    + "    `subject`.`comboName`,\n"
                    + "    `subject`.`isElective`,\n"
                    + "    `subject`.`electiveName`,\n"
                    + "    `subject`.`createDate`,\n"
                    + "    `subject`.`isActive`\n"
                    + "FROM `subject`\n"
                    + "where `subject`.`subjectCode` NOT IN (SELECT `combo_subject`.`subjectCode`\n"
                    + "                                     FROM `combo_subject`\n"
                    + "                                     WHERE `combo_subject`.`cid` = ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();

            listSubject = new ArrayList<>();
            while (rs.next()) {
                listSyllabus = new ArrayList<>();
                listPreReq = new ArrayList<>();

                String subjectCode = rs.getString("subjectCode");

                listSyllabus = getSyllabusBySubjectCode(subjectCode);

                Subject subject = new Subject(subjectCode, rs.getString("subjectName_EN"), rs.getString("subjectName_VI"),
                        rs.getString("semester"), rs.getInt("noCredit"), rs.getString("note"), rs.getDate("createDate"),
                        rs.getBoolean("isCombo"), rs.getString("comboName"), rs.getBoolean("isElective"), rs.getString("electiveName"),
                        rs.getBoolean("isActive"), listSyllabus);

                listSubject.add(subject);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getListSubjectNotInCombo(): " + e);
        }

        return listSubject;
    }

    public List<Subject> getSubjectNotInElective(int eid) {
        try {
            String sql = "SELECT `subject`.`subjectCode`,\n"
                    + "    `subject`.`subjectName_En`,\n"
                    + "    `subject`.`subjectName_Vi`,\n"
                    + "    `subject`.`semester`,\n"
                    + "    `subject`.`noCredit`,\n"
                    + "    `subject`.`note`,\n"
                    + "    `subject`.`isCombo`,\n"
                    + "    `subject`.`comboName`,\n"
                    + "    `subject`.`isElective`,\n"
                    + "    `subject`.`electiveName`,\n"
                    + "    `subject`.`createDate`,\n"
                    + "    `subject`.`isActive`\n"
                    + "FROM `subject`\n"
                    + "where `subject`.`subjectCode` NOT IN (SELECT `elective_subject`.`subjectCode`\n"
                    + "                                     FROM `elective_subject`\n"
                    + "                                     WHERE `elective_subject`.`eid` = ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, eid);
            ResultSet rs = st.executeQuery();

            listSubject = new ArrayList<>();
            while (rs.next()) {
                listSyllabus = new ArrayList<>();
                listPreReq = new ArrayList<>();

                String subjectCode = rs.getString("subjectCode");

                listSyllabus = getSyllabusBySubjectCode(subjectCode);

                Subject subject = new Subject(subjectCode, rs.getString("subjectName_EN"), rs.getString("subjectName_VI"),
                        rs.getString("semester"), rs.getInt("noCredit"), rs.getString("note"), rs.getDate("createDate"),
                        rs.getBoolean("isCombo"), rs.getString("comboName"), rs.getBoolean("isElective"), rs.getString("electiveName"),
                        rs.getBoolean("isActive"), listSyllabus);

                listSubject.add(subject);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getListSubjectNotInCombo(): " + e);
        }

        return listSubject;
    }

    public List<Subject> getAllSubject() {
        try {
            String sql = "SELECT `subject`.`subjectCode`,\n"
                    + "           `subject`.`subjectName_En`,\n"
                    + "           `subject`.`subjectName_Vi`,\n"
                    + "           `subject`.`semester`,\n"
                    + "           `subject`.`noCredit`,\n"
                    + "           `subject`.`note`,\n"
                    + "           `subject`.`isCombo`,\n"
                    + "           `subject`.`comboName`,\n"
                    + "           `subject`.`isElective`,\n"
                    + "           `subject`.`electiveName`,\n"
                    + "           `subject`.`createDate`,\n"
                    + "           `subject`.`isActive`\n"
                    + "           FROM `subject`\n"
                    + "           order by `subject`.`semester`, `subject`.`subjectCode`;";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            listSubject = new ArrayList<>();
            while (rs.next()) {
                listSyllabus = new ArrayList<>();
                listPreReq = new ArrayList<>();

                String subjectCode = rs.getString("subjectCode");

                listSyllabus = getSyllabusBySubjectCode(subjectCode);

                Subject subject = new Subject(subjectCode, rs.getString("subjectName_EN"), rs.getString("subjectName_VI"),
                        rs.getString("semester"), rs.getInt("noCredit"), rs.getString("note"), rs.getDate("createDate"),
                        rs.getBoolean("isCombo"), rs.getString("comboName"), rs.getBoolean("isElective"), rs.getString("electiveName"),
                        rs.getBoolean("isActive"), listSyllabus);

                listSubject.add(subject);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAllSubject(): " + e);
        }

        return listSubject;
    }

    private List<Subject> getSubjectByEid(int eid) {
        try {
            String sql = "SELECT `subject`.`subjectCode`,\n"
                    + "           `subject`.`subjectName_En`,\n"
                    + "           `subject`.`subjectName_Vi`,\n"
                    + "           `subject`.`semester`,\n"
                    + "           `subject`.`noCredit`,\n"
                    + "           `subject`.`note`,\n"
                    + "           `subject`.`isCombo`,\n"
                    + "           `subject`.`comboName`,\n"
                    + "           `subject`.`isElective`,\n"
                    + "           `subject`.`electiveName`,\n"
                    + "           `subject`.`createDate`,\n"
                    + "           `subject`.`isActive`\n"
                    + "           FROM `subject` inner join `elective_subject`\n"
                    + "           ON `elective_subject`.`subjectCode` = `subject`.`subjectCode`\n"
                    + "           WHERE `elective_subject`.`eid` = ? \n"
                    + "           order by `subject`.`semester`, `subject`.`subjectCode`;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, eid);
            ResultSet rs = st.executeQuery();

            listSubject = new ArrayList<>();
            while (rs.next()) {
                listSyllabus = new ArrayList<>();
                listPreReq = new ArrayList<>();

                String subjectCode = rs.getString("subjectCode");

                listSyllabus = getSyllabusBySubjectCode(subjectCode);

                Subject subject = new Subject(subjectCode, rs.getString("subjectName_EN"), rs.getString("subjectName_VI"),
                        rs.getString("semester"), rs.getInt("noCredit"), rs.getString("note"), rs.getDate("createDate"),
                        rs.getBoolean("isCombo"), rs.getString("comboName"), rs.getBoolean("isElective"), rs.getString("electiveName"),
                        rs.getBoolean("isActive"), listSyllabus);

                listSubject.add(subject);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getSubjectByEid(): " + e);
        }

        return listSubject;
    }

    public Elective getElectiveByTag(String tag) {

        try {

            String sql = "SELECT `elective`.`eid`,\n"
                    + "    `elective`.`ename`,\n"
                    + "    `elective`.`tag`,\n"
                    + "    `elective`.`subjectName_EN`,\n"
                    + "    `elective`.`subjectName_VI`,\n"
                    + "    `elective`.`note`,\n"
                    + "    `elective`.`createDate`,\n"
                    + "    `elective`.`isActive`\n"
                    + "FROM `elective`\n"
                    + "where `elective`.`tag` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, tag);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                List<Subject> subject = getSubjectByEid(rs.getInt("eid"));

                Elective elective = new Elective(rs.getInt("eid"), rs.getString("ename"), rs.getString("tag"), rs.getString("subjectName_EN"),
                        rs.getString("subjectName_VI"), rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                return elective;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getElectiveByTag(): " + e);
        }
        return null;
    }

    public Elective getElectiveByEid(int eid) {

        try {

            String sql = "SELECT `elective`.`eid`,\n"
                    + "    `elective`.`ename`,\n"
                    + "    `elective`.`tag`,\n"
                    + "    `elective`.`subjectName_EN`,\n"
                    + "    `elective`.`subjectName_VI`,\n"
                    + "    `elective`.`note`,\n"
                    + "    `elective`.`createDate`,\n"
                    + "    `elective`.`isActive`\n"
                    + "FROM `elective`\n"
                    + "where `elective`.`eid` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, eid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                List<Subject> subject = getSubjectByEid(rs.getInt("eid"));

                Elective elective = new Elective(rs.getInt("eid"), rs.getString("ename"), rs.getString("tag"), rs.getString("subjectName_EN"),
                        rs.getString("subjectName_VI"), rs.getString("note"), rs.getDate("createDate"), rs.getBoolean("isActive"), subject);
                return elective;
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getElectiveByEid(): " + e);
        }
        return null;
    }

    public List<Major> getAllMajor() {
        List<Major> list = new ArrayList<>();
        try {
            String sql = "SELECT `major`.`majorId`,\n"
                    + "    `major`.`majorName_En`,\n"
                    + "    `major`.`majorName_Vi`,\n"
                    + "    `major`.`keyword`,\n"
                    + "    `major`.`image`\n"
                    + "FROM `major`;";

            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Major major = new Major(rs.getInt("majorID"), rs.getString("majorName_EN"), rs.getString("majorName_VI"), rs.getString("keyword"), rs.getString("image"));
                list.add(major);
            }
        } catch (SQLException e) {
            System.out.println("MajorDAO -> getMajorByMajorID(): " + e);
        }
        return list;
    }

    public List<?> listByPage(List<?> list, int start, int end) {
        List<Object> result = new ArrayList<>();

        for (int i = start; i < end; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    public PO_Mapping_PLO[][] getMappingPOToPLO(Curriculum cur) {

        List<PLO> list1 = cur.getPlo();
        List<PO> list2 = cur.getPo();
        List<PO_Mapping_PLO> list3 = getMappingByCurriculumID(cur.getCurid());

        PO_Mapping_PLO[][] matrix = new PO_Mapping_PLO[list1.size()][list2.size()];
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                boolean flag = false;
                for (PO_Mapping_PLO item : list3) {
                    if (list1.get(i).getPloid() == item.getPloid() && list2.get(j).getPoid() == item.getPoid()) {
                        flag = true;
                    }
                }
                PO_Mapping_PLO map = new PO_Mapping_PLO(cur.getCurid(), list1.get(i).getPloid(), list2.get(j).getPoid(), flag);
                matrix[i][j] = map;
            }
        }

        return matrix;
    }

    public Subject_Mapping_PLO[][] getMappingSubjectToPLO(Curriculum cur) {

        List<Subject> subject = cur.getSubject();
        List<PLO> plo = cur.getPlo();
        List<Subject_Mapping_PLO> mapping = getMappingSubject_PLOByCurriculumID(cur.getCurid());

        Subject_Mapping_PLO[][] matrix = new Subject_Mapping_PLO[subject.size()][plo.size()];

        for (int i = 0; i < subject.size(); i++) {
            for (int j = 0; j < plo.size(); j++) {
                boolean flag = false;
                for (Subject_Mapping_PLO item : mapping) {
                    if (subject.get(i).getSubjectCode().equalsIgnoreCase(item.getSubjectCode()) && plo.get(j).getPloid() == item.getPloid()) {
                        flag = true;
                    }
                }
                Subject_Mapping_PLO element = new Subject_Mapping_PLO(subject.get(i).getSubjectCode(), plo.get(j).getPloid(), flag);
                matrix[i][j] = element;
            }
        }

        return matrix;
    }

    public String getUserNameByGmail(String mail) {
        try {

            String sql = "SELECT `account`.`userName` FROM `account` WHERE `email` = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, mail);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println("DAO -> getAccountIDByGmail(): " + e);
        }
        return "";
    }

    public void insertCodeMail(String type, String code, int accountID) {
        try {
            String sql = "INSERT INTO `codesendmail` (`type`, `code`, `accountID`, `createDate`)\n"
                    + "VALUES (?, ?, ?, ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, type);
            st.setString(2, code);
            st.setInt(3, (accountID == 0 ? getLastAccountID() : accountID));

            Date now = new Date();
            Timestamp timestamp = new Timestamp(now.getTime());
            st.setTimestamp(4, timestamp);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO -> insertCodeMail(): " + e);
        }
    }

    public int getLastAccountID() {
        try {
            String sql = "SELECT `account`.`accountID`\n"
                    + "FROM `account`\n"
                    + "ORDER BY `account`.`accountID` DESC\n"
                    + "LIMIT 1;";

            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO -> getCodeMailLast():");
        }
        return 0;
    }

    public String getCodeMailLast(int accountID) {
        try {
            String sql = "SELECT `codesendmail`.`code`\n"
                    + "FROM `codesendmail`\n"
                    + "WHERE  `codesendmail`.`accountID` = ? and  TIMESTAMPDIFF(MINUTE, `codesendmail`.`createDate`, NOW()) < 5\n"
                    + "ORDER BY `codesendmail`.`createDate` DESC\n"
                    + "LIMIT 1;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO -> getCodeMailLast():");
        }
        return "";
    }
    
    public int getAccountIdByCodeSendMail(String codeSendMail){
        try {
            String sql = "select accountID from codesendmail where code = ?";

            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO -> getCodeMailLast():");
        }
        return 0;
    }

}
