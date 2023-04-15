/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package admin.user;

import DAL.AccountDAO;
import DAL.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PaginationModel;

/**
 *
 * @author phanh
 */
@WebServlet(name="AdminAllUserController", urlPatterns={"/admin-alluser"})
public class AdminAllUserController extends HttpServlet {
   
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
        int filterRole = 0;
        int filterStatus = 3;
        if(request.getParameter("pageNo")!=null){
                   if(Integer.parseInt(request.getParameter("pageNo"))!=0){
            pageNo = Integer.parseInt(request.getParameter("pageNo"));
        } 
        }
        if(request.getParameter("filterRole")!=null){
                if(!request.getParameter("filterRole").equals("")||Integer.parseInt(request.getParameter("filterRole"))!=0){
            filterRole = Integer.parseInt(request.getParameter("filterRole"));
        }
        }
        if(request.getParameter("filterStatus")!=null){
                    if(!request.getParameter("filterStatus").equals("")||Integer.parseInt(request.getParameter("filterStatus"))!=3){
            filterStatus = Integer.parseInt(request.getParameter("filterStatus"));
        }
        }
        if(request.getParameter("search")!=null){
                  if(!request.getParameter("search").equals("")){
            search = request.getParameter("search");
        }  
        }
        PaginationModel paginationModel = new PaginationModel(pageNo,pageSize, search, filterRole, filterStatus);
        AccountDAO accountDAO = new AccountDAO();
        RoleDAO roleDAO = new RoleDAO();
        request.setAttribute("totalPages", accountDAO.countAllAccountByPageAndFilter(paginationModel));
        request.setAttribute("pagination", paginationModel);
        request.setAttribute("roles", roleDAO.getAllRole());
        request.setAttribute("search", paginationModel.getSearch());
        request.setAttribute("filterStatus", paginationModel.getFilterStatus());
        request.setAttribute("filterRole", paginationModel.getFilterRole());
        request.setAttribute("list", accountDAO.getAllAccountByPageAndFilter(paginationModel));
        System.out.print(accountDAO.countAllAccountByPageAndFilter(paginationModel));
        System.out.print(accountDAO.getAllAccountByPageAndFilter(paginationModel).size());
        request.getRequestDispatcher("gui/admin/user/allUser.jsp").forward(request, response);
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
