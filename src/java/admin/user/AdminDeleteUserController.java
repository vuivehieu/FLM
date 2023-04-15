/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.user;

import DAL.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="AdminDeleteUserController", urlPatterns={"/admin-deleteuser"})
public class AdminDeleteUserController extends HttpServlet{
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         AccountDAO accountDAO = new AccountDAO();
         int id = Integer.parseInt(request.getParameter("id"));
         accountDAO.deleteUser(id);
         response.sendRedirect(request.getContextPath() + "/admin-alluser");
    }   
}
