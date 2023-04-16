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
import model.PaginationModel;

public class AccountDAO extends DBContext {

    public void resetPass(int accountID, String password) {
        try {
            String sql = "UPDATE `swp391_bl5_g6`.`account`\n"
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

    public Account getAccountByAccountID1(int id) throws SQLException {
        try {
            String sql = "select * from account where accountID = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Role role = new Role(rs.getInt("rid"), "Guest");
                Account account = new Account(rs.getInt("accountID"), rs.getString("userName"), rs.getString("password"),
                        rs.getString("displayName"), rs.getString("email"), rs.getString("avatar"), rs.getBoolean("isBlock"),
                        rs.getInt("status"), rs.getDate("createDate"), role);

                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
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
                    + "FROM `swp391_bl5_g6`.`account` inner join `swp391_bl5_g6`.`role`\n"
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

    public List<Account> getAllAccountByPageAndFilter(PaginationModel pagination) {

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
                    + "FROM `swp391_bl5_g6`.`account` inner join `swp391_bl5_g6`.`role`\n"
                    + "ON `account`.`rid` = `role`.`rid`";
            if (pagination.getFilterRole() != 0 || pagination.getFilterStatus() != 3 || !pagination.getSearch().equals("")) {
                sql += " AND";
                if (pagination.getFilterRole() != 0) {
                    sql += " role.rid = ?";
                }
                if (pagination.getFilterStatus() != 3) {
                    if (pagination.getFilterRole() != 0) {
                        sql += " AND";
                    }
                    sql += " account.status = ?";
                }
                if (!pagination.getSearch().equals("")) {
                    if (pagination.getFilterRole() != 0 || pagination.getFilterStatus() != 3) {
                        sql += " AND";
                    }
                    sql += " (account.userName LIKE ? OR account.email LIKE ? OR account.displayName LIKE ?)";
                }
            }
            sql += " LIMIT " + (pagination.getPageNo() - 1) * pagination.getPageSize() + "," + pagination.getPageSize() + ";";
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 1;
            if (pagination.getFilterRole() != 0) {
                st.setInt(i++, pagination.getFilterRole());
            }
            if (pagination.getFilterStatus() != 3) {
                st.setInt(i++, pagination.getFilterStatus());
            }
            if (!pagination.getSearch().equals("")) {
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
            }
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Account account;
                String userName = rs.getString("userName");

                Role role = new Role(rs.getInt("rid"), rs.getString("rname"));
                    account = new Account(rs.getInt("accountID"), rs.getString("userName"), rs.getString("password"),
                            rs.getString("displayName"), rs.getString("email"), rs.getString("avatar"), rs.getBoolean("isBlock"),
                            rs.getInt("status"), rs.getDate("createDate"), role);


                list.add(account);
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> getAllAccountByPageAndFilter(): " + e);

        }
        return list;
    }

    public int countAllAccountByPageAndFilter(PaginationModel pagination) {

        int count = 0;
        try {
            String sql = "SELECT COUNT(*) as count FROM `swp391_bl5_g6`.`account` INNER JOIN `swp391_bl5_g6`.`role`\n"
                    + "ON `account`.`rid` = `role`.`rid`";
            if (pagination.getFilterRole() != 0 || pagination.getFilterStatus() != 3 || !pagination.getSearch().equals("")) {
                sql += " WHERE";
                if (pagination.getFilterRole() != 0) {
                    sql += " `role`.`rid` = ?";
                }
                if (pagination.getFilterStatus() != 3) {
                    if (pagination.getFilterRole() != 0) {
                        sql += " AND";
                    }
                    sql += " `account`.`status` = ?";
                }
                if (!pagination.getSearch().equals("")) {
                    if (pagination.getFilterRole() != 0 || pagination.getFilterStatus() != 3) {
                        sql += " AND";
                    }
                    sql += " (`account`.`userName` LIKE ? OR `account`.`email` LIKE ? OR `account`.`displayName` LIKE ?)";
                }
            }
            sql += ";";
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 1;
            if (pagination.getFilterRole() != 0) {
                st.setInt(i++, pagination.getFilterRole());
            }
            if (pagination.getFilterStatus() != 3) {
                st.setInt(i++, pagination.getFilterStatus());
            }
            if (!pagination.getSearch().equals("")) {
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
            }
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("AccountDAO -> countAllAccount(): " + e);
        }
        return (int) Math.ceil((double) count / pagination.getPageSize());
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
                    + "FROM `swp391_bl5_g6`.`account` inner join `swp391_bl5_g6`.`role`\n"
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
                    + " FROM `swp391_bl5_g6`.`account` inner join `swp391_bl5_g6`.`role`\n"
                    + " ON `account`.`rid` = `role`.`rid`inner join `swp391_bl5_g6`.`student`\n"
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
                    + "    `role`.`rname`,\n"
                    + "    `role`.`status` as rolestatus \n"
                    + "FROM `swp391_bl5_g6`.`account` inner join `swp391_bl5_g6`.`role`\n"
                    + "ON `account`.`rid` = `role`.`rid`\n"
                    + "WHERE `account`.`userName` = ? and `account`.`password` = ? and (`account`.`status`!=4 OR `account`.`status`!=0);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, passwordConverted);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Account account;

                Role role = new Role(rs.getInt("rid"), rs.getString("rname"));
                role.setStatus(rs.getInt("rolestatus"));
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
                    + "FROM `swp391_bl5_g6`.`account`"
                    + "WHERE `account`.`userName` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result += rs.getString("userName");
            }

            String sql1 = "SELECT `account`.`email`\n"
                    + "FROM `swp391_bl5_g6`.`account`"
                    + "WHERE `account`.`email` = ?;";

            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, email);
            ResultSet rs1 = st1.executeQuery();
            if (rs1.next()) {
                result += rs1.getString("email");
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> checkRegister(): " + e);
        }

        return result.equalsIgnoreCase("") ? "OK" : result;
    }

    public boolean checkRegisterEdit(String userName, String email) {
        String result = "";
        try {

            String sql = "SELECT `account`.`userName`\n"
                    + "FROM `swp391_bl5_g6`.`account`"
                    + "WHERE `account`.`userName` = ? ;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result += rs.getString("userName");
            }

            String sql1 = "SELECT `account`.`email`\n"
                    + "FROM `swp391_bl5_g6`.`account`"
                    + "WHERE `account`.`email` = ?;";

            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, email);
            ResultSet rs1 = st1.executeQuery();
            if (rs1.next()) {
                result += rs1.getString("email");
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> checkRegister(): " + e);
        }

        return result.equalsIgnoreCase("");
    }

    public boolean checkByUsernameAndEmail(String username, String email) {
        int result = 0;
        try {
            String sql1 = "select COUNT(*) as count from account where email = ? or username=?";

            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, email);
            st1.setString(2, username);
            ResultSet rs1 = st1.executeQuery();
            if (rs1.next()) {
                result += rs1.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("AccountDAO -> checkRegister(): " + e);
        }
        return result == 0;
    }

    public boolean checkEmail(String email) {
        String result = "";
        try {
            String sql1 = "select * from account where email = ? and (status = 1 or status = 0)";

            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, email);
            ResultSet rs1 = st1.executeQuery();
            if (rs1.next()) {
                result += rs1.getString("email");
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> checkRegister(): " + e);
        }

        return result.equalsIgnoreCase("");
    }

    public boolean checkEmailForgot(String email) {
        String result = "";
        try {
            String sql1 = "select * from account where email = ? and status = 1";

            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, email);
            ResultSet rs1 = st1.executeQuery();
            if (rs1.next()) {
                result += rs1.getString("email");
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> checkRegister(): " + e);
        }
        boolean check = !result.equalsIgnoreCase("");
        return check;
    }

    public Account findAccountByEmail(String email) {
        Account account;
        try {
            String sql1 = "select * from account where email = ? and status = 1";

            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, email);
            ResultSet rs = st1.executeQuery();
            while (rs.next()) {
                int rid = rs.getInt("rid");
                //find role by role id => gan vao account
                Role role = this.findRoleByRoleId(rid);

                account = new Account(rs.getInt("accountID"), rs.getString("userName"), rs.getString("password"),
                        rs.getString("displayName"), rs.getString("email"), rs.getString("avatar"), rs.getBoolean("isBlock"),
                        rs.getInt("status"), rs.getDate("createDate"), role);
                return account;
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> checkRegister(): " + e);
        }

        return null;
    }

    public Role findRoleByRoleId(int roleId) {
        Role role;
        try {
            String sql = "select * from role where rid = ?";

            PreparedStatement st1 = connection.prepareStatement(sql);
            st1.setInt(1, roleId);
            ResultSet rs = st1.executeQuery();
            while (rs.next()) {
                role = new Role(rs.getInt("rid"), rs.getString("rname"));
                return role;
            }

        } catch (SQLException e) {
            System.out.println("AccountDAO -> checkRegister(): " + e);
        }

        return null;
    }

    public int register(Account a) {
        try {

            String sql = "INSERT INTO `swp391_bl5_g6`.`account`\n"
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
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("AccountDAO -> register(): " + e);

        }
        return 0;
    }

    public int addUser(Account a) {
        try {

            String sql = "INSERT INTO `swp391_bl5_g6`.`account`\n"
                    + "(`userName`, `password`, `displayName`, `email`, `avatar`, `isBlock`, `status`, `createDate`, `rid`)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUserName());
            st.setString(2, a.getPassword());
            st.setString(3, a.getDisplayName());
            st.setString(4, a.getEmail());
            st.setString(5, a.getAvatar());
            st.setBoolean(6, a.isIsBlock());
            st.setInt(7, a.getStatus());
            st.setDate(8, a.getCreateDate());
            st.setInt(9, a.getRole().getRid());   // role = 1 => guest
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("AccountDAO -> addUser(): " + e);

        }
        return 0;
    }

    public int changePassword(String newPassword, Account a) {
        try {

            String sql = "UPDATE `swp391_bl5_g6`.`account` SET `password` = ?\n"
                    + "WHERE `userName` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Custom.ConvertMD5.convertPassToMD5(newPassword));
            st.setString(2, a.getUserName());

            return st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("AccountDAO -> changePassword(): " + e);

        }
        return 0;
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
                    + " FROM `swp391_bl5_g6`.`account` inner join `swp391_bl5_g6`.`role`\n"
                    + " ON `account`.`rid` = `role`.`rid` inner join `swp391_bl5_g6`.`lecturer`\n"
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

            String sql = "UPDATE `swp391_bl5_g6`.`account`\n"
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

            String sql = "UPDATE `swp391_bl5_g6`.`account`\n"
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

    public void updateRole(int role, String username) {
        try {

            String sql = "UPDATE `swp391_bl5_g6`.`account`\n"
                    + "SET\n"
                    + "`rid` = ?\n"
                    + "WHERE `userName` = ?;";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, role);
            st.setString(2, username);

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

    public void deleteUser(int id) {
        try {

            String sql = "Update account set status = 4 where accountID = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(" AccountDAO -> deleteUser(): " + e);

        }
    }
}
