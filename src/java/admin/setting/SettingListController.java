/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.setting;

import DAL.AccountDAO;
import DAL.RoleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.PaginationModel;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="SettingListController", urlPatterns={"/admin-settings"})
public class SettingListController extends HttpServlet {
   
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
            out.println("<title>Servlet AdminAllUserController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAllUserController at " + request.getContextPath () + "</h1>");
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
        int pageNo = 1;
        int pageSize = 5;
        String search = "";
        String filterType = "User Role";
        int filterStatus = 3;
        if(request.getParameter("pageNo")!=null){
                   if(Integer.parseInt(request.getParameter("pageNo"))!=0){
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        } 
        }
        if(request.getParameter("filterType")!=null){
                if(!request.getParameter("filterType").equals("User Role")||!request.getParameter("filterType").equals("")){
            filterType = request.getParameter("filterType");
        }
        }
        if(request.getParameter("filterStatus")!=null){
                    if(!(request.getParameter("filterStatus").equals(""))||Integer.parseInt(request.getParameter("filterStatus"))!=3){
            filterStatus = Integer.parseInt(request.getParameter("filterStatus"));
        }
        }
        if(request.getParameter("search")!=null){
                  if(!request.getParameter("search").equals("")){
            search = request.getParameter("search");
        }  
        }
        PaginationModel paginationModel = new PaginationModel(pageNo,pageSize, search, filterStatus,filterType, true);
        RoleDAO roleDAO = new RoleDAO();
        request.setAttribute("totalPages", roleDAO.countAllSettingByPageAndFilter(paginationModel));
        request.setAttribute("pagination", paginationModel);
        request.setAttribute("roles", roleDAO.getAllRole());
        request.setAttribute("search", paginationModel.getSearch());
        request.setAttribute("filterStatus", paginationModel.getFilterStatus());
        request.setAttribute("filterType", paginationModel.getFilterType());
        request.setAttribute("list", roleDAO.getAllSettingByPageAndFilter(paginationModel));
        request.getRequestDispatcher("gui/admin/setting/list.jsp").forward(request, response);
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
        processRequest(request, response);
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
