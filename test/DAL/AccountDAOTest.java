/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAL;

import java.util.List;
import model.Account;
import model.Role;
import model.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phanh
 */
public class AccountDAOTest {
    
    public AccountDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllAccount method, of class AccountDAO.
     */
    @Test
    public void testGetAllAccount() {
        System.out.println("getAllAccount");
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = null;
        List<Account> result = instance.getAllAccount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountByUserName method, of class AccountDAO.
     */
    @Test
    public void testGetAccountByUserName() {
        System.out.println("getAccountByUserName");
        String userName = "";
        AccountDAO instance = new AccountDAO();
        Account expResult = null;
        Account result = instance.getAccountByUserName(userName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentByUserName method, of class AccountDAO.
     */
    @Test
    public void testGetStudentByUserName() {
        System.out.println("getStudentByUserName");
        String userName = "";
        Role role = null;
        AccountDAO instance = new AccountDAO();
        Student expResult = null;
        Student result = instance.getStudentByUserName(userName, role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkLogin method, of class AccountDAO.
     */
    @Test
    public void testCheckLogin() {
        System.out.println("checkLogin");
        String userName = "admin";
        String password = "123";
        AccountDAO instance = new AccountDAO();
        Account expResult = null;
        Account result = instance.checkLogin(userName, password);
        assertNotEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRegister method, of class AccountDAO.
     */
    @Test
    public void testCheckRegister() {
        System.out.println("checkRegister");
        String userName = "";
        String email = "";
        AccountDAO instance = new AccountDAO();
        String expResult = "";
        String result = instance.checkRegister(userName, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class AccountDAO.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        Account a = null;
        AccountDAO instance = new AccountDAO();
        instance.register(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePassword method, of class AccountDAO.
     */
    @Test
    public void testChangePassword() {
        System.out.println("changePassword");
        String newPassword = "";
        Account a = null;
        AccountDAO instance = new AccountDAO();
        instance.changePassword(newPassword, a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLecturerByUserName method, of class AccountDAO.
     */
    @Test
    public void testGetLecturerByUserName() {
        System.out.println("getLecturerByUserName");
        String userName = "";
        Role role = null;
        AccountDAO instance = new AccountDAO();
        Account expResult = null;
        Account result = instance.getLecturerByUserName(userName, role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProfile method, of class AccountDAO.
     */
    @Test
    public void testUpdateProfile() {
        System.out.println("updateProfile");
        Account a = null;
        AccountDAO instance = new AccountDAO();
        instance.updateProfile(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStatus method, of class AccountDAO.
     */
    @Test
    public void testUpdateStatus() {
        System.out.println("updateStatus");
        Account a = null;
        AccountDAO instance = new AccountDAO();
        instance.updateStatus(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
