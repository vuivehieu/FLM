/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.user;

import DAL.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Account;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="AdminUpdateUserController", urlPatterns={"/admin-updateuser"})
public class AdminUpdateUserController extends HttpServlet{

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminAddUserController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAddUserController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        AccountDAO accountDAO = new AccountDAO();
        String userName = request.getParameter("updateUsername");
        int id = Integer.parseInt(request.getParameter("updateId"));
        int uid = 0;
        int roleId = 0;
        Cookie[] cookies = request.getCookies();
                if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("userId")){
                    uid = Integer.parseInt(cookie.getValue());
                }
                if(cookie.getName().equals("userRole")){
                    roleId = Integer.parseInt(cookie.getValue());
                }
            }
        }
        int role = Integer.parseInt(request.getParameter("updateRole"));
        int status = Integer.parseInt(request.getParameter("updateStatus"));
        Account a = new Account();
        a.setStatus(status);
        a.setUserName(userName);
        accountDAO.updateStatus(a);
        accountDAO.updateRole(role, userName);
        String page = "";
        if(id == uid && roleId != role){
            page = "/logout";
        }else{
            page = "/admin-alluser";
        }
        response.sendRedirect(request.getContextPath() + page);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
