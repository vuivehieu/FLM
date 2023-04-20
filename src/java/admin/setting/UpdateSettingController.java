/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.setting;

import DAL.RoleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Role;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateSettingController", urlPatterns = {"/update-setting"})
public class UpdateSettingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminAddUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAddUserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        RoleDAO roleDao = new RoleDAO();
        int id = Integer.parseInt(request.getParameter("updateId"));
        String name = request.getParameter("updateName");
        String description = request.getParameter("updateDescription");
        int display = Integer.parseInt(request.getParameter("updateDisplayOrder"));
        int status = Integer.parseInt(request.getParameter("updateStatus"));
        Role r = new Role();
        r.setRid(id);
        r.setDescription(description);
        r.setDisplayOrder(display);
        r.setRname(name);
        r.setStatus(status);
        r.setType("User Role");
        roleDao.updateRole(r);
        int roleId = 0;
        String page ="";
        Cookie[] cookies = request.getCookies();
                if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("userRole")){
                    roleId = Integer.parseInt(cookie.getValue());
                }
            }
        }
                if(roleId == id){
                    if(status == 0){
                        page = "/logout";
                    }
                }else{
                        page = "/admin-settings";
                    }
        response.sendRedirect(request.getContextPath() + page);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
