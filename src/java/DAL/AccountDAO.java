package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Role;
import model.Student;
import model.Curriculum;
import model.Lecturer;

public class AccountDAO extends DBContext {

    public void resetPass(int accountID, String password) {
        try {
            String sql = "UPDATE `swp391_se1632_g2`.`account`\n"
                    + "SET\n"
                    + "`password` = ?"
                    + "WHERE `accountID` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setInt(2, accountID);

        } catch (SQLException e) {
            System.out.println("AccountDAO -> resetPass(): " + e);
        }
    }

    public Account getAccountByAccountID(int id) {
        try {
            String sql = "`account`.`accountID`,\n"
                    + "   `account`.`userName`,\n"
                    + "   `account`.`password`,\n"
                    + "   `account`.`displayName`,\n"
                    + "   `account`.`email`,\n"
                    + "   `account`.`avatar`,\n"
                    + "   `account`.`isBlock`,\n"
                    + "   `account`.`status`,\n"
                    + "   `account`.`createDate`,\n"
                    + "   `account`.`rid`,\n"
                    + "   `role`.`rname`\n"
                    + "                   FROM `account` inner join `role`\n"
                    + "                   ON `account`.`rid` = `role`.`rid` inner join `codesendmail`\n"
                    + "                   ON `account`.`accountID` = `codesendmail`.`accountID`\n"
                    + "                   WHERE `account`.`accountID` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Account account;

                Role role = new Role(rs.getInt("rid"), rs.getString("rname"));

                if (role.getRname().equalsIgnoreCase("student")) {
                    account = getStudentByUserName(rs.getString("userName"), role);
                } else if (role.getRname().equalsIgnoreCase("teacher")) {
                    account = getLecturerByUserName(rs.getString("userName"), role);
                } else {
                    account = new Account(rs.getInt("accountID"), rs.getString("userName"), rs.getString("password"),
                            rs.getString("displayName"), rs.getString("email"), rs.getString("avatar"), rs.getBoolean("isBlock"),
                            rs.getInt("status"), rs.getDate("createDate"), role);
                }

                return account;
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> getAccountByAccountID(): " + e);

        }

        return null;

    }

    public List<Account> getAllAccount() {

        List<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT `account`.`accountID`,\n"
                    + "    `account`.`userName`,\n"
                    + "    `account`.`password`,\n"
                    + "    `account`.`displayName`,\n"
                    + "    `account`.`email`,\n"
                    + "    `account`.`avatar`,\n"
                    + "    `account`.`isBlock`,\n"
                    + "    `account`.`status`,\n"
                    + "    `account`.`createDate`,\n"
                    + "    `account`.`rid`,\n"
                    + "    `role`.`rname`\n"
                    + "FROM `swp391_se1632_g2`.`account` inner join `swp391_se1632_g2`.`role`\n"
                    + "ON `account`.`rid` = `role`.`rid`;";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Account account;
                String userName = rs.getString("userName");

                Role role = new Role(rs.getInt("rid"), rs.getString("rname"));

                if (role.getRname().equalsIgnoreCase("student")) {
                    account = getStudentByUserName(userName, role);
                } else if (role.getRname().equalsIgnoreCase("teacher")) {
                    account = getLecturerByUserName(userName, role);
                } else {
                    account = new Account(rs.getInt("accountID"), rs.getString("userName"), rs.getString("password"),
                            rs.getString("displayName"), rs.getString("email"), rs.getString("avatar"), rs.getBoolean("isBlock"),
                            rs.getInt("status"), rs.getDate("createDate"), role);
                }

                list.add(account);
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> getAllAccount(): " + e);

        }
        return list;
    }

    public Account getAccountByUserName(String userName) {

        try {
            String sql = "SELECT `account`.`accountID`,\n"
                    + "    `account`.`userName`,\n"
                    + "    `account`.`password`,\n"
                    + "    `account`.`displayName`,\n"
                    + "    `account`.`email`,\n"
                    + "    `account`.`avatar`,\n"
                    + "    `account`.`isBlock`,\n"
                    + "    `account`.`status`,\n"
                    + "    `account`.`createDate`,\n"
                    + "    `account`.`rid`,\n"
                    + "    `role`.`rname`"
                    + "FROM `swp391_se1632_g2`.`account` inner join `swp391_se1632_g2`.`role`\n"
                    + "ON `account`.`rid` = `role`.`rid`\n"
                    + "WHERE `account`.`userName` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Account account;

                Role role = new Role(rs.getInt("rid"), rs.getString("rname"));

                if (role.getRname().equalsIgnoreCase("student")) {
                    account = getStudentByUserName(userName, role);
                } else if (role.getRname().equalsIgnoreCase("teacher")) {
                    account = getLecturerByUserName(userName, role);
                } else {
                    account = new Account(rs.getInt("accountID"), rs.getString("userName"), rs.getString("password"),
                            rs.getString("displayName"), rs.getString("email"), rs.getString("avatar"), rs.getBoolean("isBlock"),
                            rs.getInt("status"), rs.getDate("createDate"), role);
                }

                return account;
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> getAccountByUserName(): " + e);

        }

        return null;
    }

    public Student getStudentByUserName(String userName, Role role) {
        try {
            String sql = "SELECT `account`.`accountID`,\n"
                    + "    `account`.`userName`,\n"
                    + "    `account`.`password`,\n"
                    + "    `account`.`displayName`,\n"
                    + "    `account`.`email`,\n"
                    + "    `account`.`avatar`,\n"
                    + "    `account`.`isBlock`,\n"
                    + "    `account`.`status`,\n"
                    + "    `account`.`createDate`,\n"
                    + "    `account`.`rid`,\n"
                    + "`role`.`rname`,\n"
                    + "`student`.`stid`,\n"
                    + "`student`.`userName`,\n"
                    + "`student`.`curid`,\n"
                    + "`student`.`semester`,\n"
                    + "`student`.`isActive`\n"
                    + " FROM `swp391_se1632_g2`.`account` inner join `swp391_se1632_g2`.`role`\n"
                    + " ON `account`.`rid` = `role`.`rid`inner join `swp391_se1632_g2`.`student`\n"
                    + " ON `account`.`userName` = `student`.`userName`\n"
                    + "WHERE `account`.`userName` = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Curriculum curriculum = new DAO().getCurriculumByCurid(rs.getInt("curid"));

                Student std = new Student(rs.getInt("accountID"), rs.getString("userName"), rs.getString("password"),
                        rs.getString("displayName"), rs.getString("email"), rs.getString("avatar"), rs.getBoolean("isBlock"),
                        rs.getInt("status"), rs.getDate("createDate"), role, rs.getString("stid"), rs.getString("semester"),
                        rs.getBoolean("isActive"), curriculum);
                return std;
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> getStudentByUserName(): " + e);
        }
        return null;
    }

    public Account checkLogin(String userName, String password) {

        try {
            String passwordConverted = Custom.ConvertMD5.convertPassToMD5(password);

            String sql = "SELECT `account`.`accountID`,\n"
                    + "    `account`.`userName`,\n"
                    + "    `account`.`password`,\n"
                    + "    `account`.`displayName`,\n"
                    + "    `account`.`email`,\n"
                    + "    `account`.`avatar`,\n"
                    + "    `account`.`isBlock`,\n"
                    + "    `account`.`status`,\n"
                    + "    `account`.`createDate`,\n"
                    + "    `account`.`rid`,\n"
                    + "    `role`.`rname`\n"
                    + "FROM `swp391_se1632_g2`.`account` inner join `swp391_se1632_g2`.`role`\n"
                    + "ON `account`.`rid` = `role`.`rid`\n"
                    + "WHERE `account`.`userName` = ? and `account`.`password` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, passwordConverted);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Account account;

                Role role = new Role(rs.getInt("rid"), rs.getString("rname"));

                if (role.getRname().equalsIgnoreCase("student")) {
                    account = getStudentByUserName(userName, role);
                } else if (role.getRname().equalsIgnoreCase("teacher")) {
                    account = getLecturerByUserName(userName, role);
                } else {
                    account = new Account(rs.getInt("accountID"), rs.getString("userName"), rs.getString("password"),
                            rs.getString("displayName"), rs.getString("email"), rs.getString("avatar"), rs.getBoolean("isBlock"),
                            rs.getInt("status"), rs.getDate("createDate"), role);
                }

                return account;
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> checkLogin(): " + e);
        }
        return null;
    }

    public String checkRegister(String userName, String email) {
        String result = "";
        try {

            String sql = "SELECT `account`.`userName`\n"
                    + "FROM `swp391_se1632_g2`.`account`"
                    + "WHERE `account`.`userName` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result += "userName";
            }

            String sql1 = "SELECT `account`.`email`\n"
                    + "FROM `swp391_se1632_g2`.`account`"
                    + "WHERE `account`.`email` = ?;";

            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, email);
            ResultSet rs1 = st1.executeQuery();
            if (rs1.next()) {
                result += "email";
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> checkRegister(): " + e);
        }

        return result.equalsIgnoreCase("") ? "OK" : result;
    }

    public void register(Account a) {
        try {

            String sql = "INSERT INTO `swp391_se1632_g2`.`account`\n"
                    + "(`userName`, `password`, `displayName`, `email`, `avatar`, `isBlock`, `status`, `createDate`, `rid`)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUserName());
            st.setString(2, Custom.ConvertMD5.convertPassToMD5(a.getPassword()));
            st.setString(3, a.getDisplayName());
            st.setString(4, a.getEmail());
            st.setString(5, a.getAvatar());
            st.setBoolean(6, a.isIsBlock());
            st.setInt(7, a.getStatus());
            st.setDate(8, a.getCreateDate());
            st.setInt(9, 1);   // role = 1 => guest
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("AccountDAO -> register(): " + e);

        }
    }

    public void changePassword(String newPassword, Account a) {
        try {

            String sql = "UPDATE `swp391_se1632_g2`.`account` SET `password` = ?\n"
                    + "WHERE `userName` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Custom.ConvertMD5.convertPassToMD5(newPassword));
            st.setString(2, a.getUserName());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("AccountDAO -> changePassword(): " + e);

        }
    }

    public Account getLecturerByUserName(String userName, Role role) {
        try {

            String sql = "SELECT `account`.`accountID`,\n"
                    + "    `account`.`userName`,\n"
                    + "    `account`.`password`,\n"
                    + "    `account`.`displayName`,\n"
                    + "    `account`.`email`,\n"
                    + "    `account`.`avatar`,\n"
                    + "    `account`.`isBlock`,\n"
                    + "    `account`.`status`,\n"
                    + "    `account`.`createDate`,\n"
                    + "    `account`.`rid`,\n"
                    + "`role`.`rname`,\n"
                    + "`lecturer`.`lid`\n"
                    + " FROM `swp391_se1632_g2`.`account` inner join `swp391_se1632_g2`.`role`\n"
                    + " ON `account`.`rid` = `role`.`rid` inner join `swp391_se1632_g2`.`lecturer`\n"
                    + " ON `account`.`userName` = `lecturer`.`userName`\n"
                    + "WHERE `account`.`userName` = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                Lecturer let = new Lecturer(rs.getInt("accountID"), rs.getString("userName"), rs.getString("password"),
                        rs.getString("displayName"), rs.getString("email"), rs.getString("avatar"), rs.getBoolean("isBlock"),
                        rs.getInt("status"), rs.getDate("createDate"), role, rs.getInt("lid"), rs.getString("userName"));
                return let;
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> getLecturerByUserName(): " + e);

        }
        return null;
    }

    public void updateProfile(Account a) {
        try {

            String sql = "UPDATE `swp391_se1632_g2`.`account`\n"
                    + "SET\n"
                    + "`displayName` = ?,\n"
                    + "`email` = ?\n"
                    + "WHERE `userName` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getDisplayName());
            st.setString(2, a.getEmail());
            st.setString(3, a.getUserName());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(" AccountDAO -> updateProfile(): " + e);

        }
    }

    public void updateStatus(Account a) {
        try {

            String sql = "UPDATE `swp391_se1632_g2`.`account`\n"
                    + "SET\n"
                    + "`status` = ?\n"
                    + "WHERE `userName` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a.getStatus());
            st.setString(2, a.getUserName());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(" AccountDAO -> updateStatus(): " + e);

        }
    }

    public void changeAvatar(int accountID, String url) {
        try {

            String sql = "UPDATE `account` SET `avatar` = ? WHERE `accountID` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, url);
            st.setInt(2, accountID);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(" AccountDAO -> changeAvatar(): " + e);

        }
    }
}
