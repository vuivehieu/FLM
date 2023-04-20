/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package admin.user;

import DAL.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import model.Account;
import model.Role;

/**
 *
 * @author phanh
 */
@WebServlet(name="AdminAddUserController", urlPatterns={"/admin-adduser"})
public class AdminAddUserController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("gui/admin/user/add.jsp").forward(request, response);
    } 

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
        String userName = request.getParameter("inputUsername");
        String fullName = request.getParameter("inputFullName");
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
//        String avatar = request.getParameter("inputAvatar");
        
        Part file = request.getPart("inputAvatar");
        String fileName = file.getSubmittedFileName();
        String[] fields = fileName.split("\\.");
        String convert = "." + fields[1];
        String avatar = "";
        if (!fileName.isEmpty()) {
//            String uploadPath = "C:/Users/phanh/OneDrive/Máy tính/Template-SWP391/G2/SWP391-G2/web/images/avatar/" + convert;
            String uploadPath = "C:/Users/PC/Desktop/FPT/FLM/web/images/avatar" + convert;

            try {
                FileOutputStream fos = new FileOutputStream(uploadPath);
                InputStream is = file.getInputStream();

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();

                avatar = "images/avatar/" + convert;

            } catch (IOException e) {
                System.out.println(e);
            }
        }
        
        int role = Integer.parseInt(request.getParameter("inputRole"));
        int status = Integer.parseInt(request.getParameter("inputStatus"));
        Account a = new Account();
        a.setDisplayName(fullName);
        a.setEmail(email);
        a.setPassword(Custom.ConvertMD5.convertPassToMD5(password));
        a.setIsBlock(false);
        a.setAvatar(avatar);
        a.setStatus(status);
        a.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        a.setRole(new Role(role, null));
        a.setUserName(userName);
        if (a.getAvatar().equals("")) {
            a.setAvatar("images/avatar/default.png");
        }
                if(accountDAO.checkByUsernameAndEmail(a.getUserName(), a.getEmail())){
                            accountDAO.addUser(a);
                }
        response.sendRedirect(request.getContextPath() + "/admin-alluser");
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
