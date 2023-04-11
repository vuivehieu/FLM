/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.plo;

import DAL.DAO;
import DAL.PLODAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PLO;

/**
 *
 * @author phanh
 */
@WebServlet(name = "PloDetailController", urlPatterns = {"/ploDetail"})
public class PloDetailController extends HttpServlet {

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
            out.println("<title>Servlet PloDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PloDetailController at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ploid_raw = request.getParameter("ploid");
        int ploid = 0;
        try {
            ploid = Integer.parseInt(ploid_raw);
        } catch (NumberFormatException e) {
            System.out.println("PLO Detail Controlelr -> doGet(): " + e);
        }

        DAO dao = new DAO();
        PLO plo = dao.getPLOByID(ploid);

        request.setAttribute("plo", plo);

        request.getRequestDispatcher("gui/admin/plo/detail.jsp").forward(request, response);
    }

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
        String ploid_raw = request.getParameter("ploid");
        int ploid = 0;
        try {
            ploid = Integer.parseInt(ploid_raw);

        } catch (NumberFormatException e) {
            System.out.println("ploDetailController -> doPost(): " + e);
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String active = request.getParameter("isActive");
        boolean isActive = active.equalsIgnoreCase("true");

        PLODAO dao = new PLODAO();
        String message = "";
        PLO plo = new DAO().getPLOByID(ploid);

        if (!dao.checkExist(name, plo.getPloName())) {
            dao.update(name, description, isActive, ploid);

            message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                    + "                <div class='toast-header'>"
                    + "                    <strong class='mr-auto'>Update PLO Success</strong>"
                    + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                    + "                        <span aria-hidden='true'>&times;</span>"
                    + "                    </button>"
                    + "                </div>"
                    + "                <div class='toast-body'>"
                    + "                    Success !"
                    + "                </div>"
                    + "            </div>";

            request.getSession().setAttribute("message", message);

            response.sendRedirect("ploDetail?ploid=" + ploid);

        } else {

            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("active", active);
            request.setAttribute("isActive", isActive);

            request.setAttribute("error", "PO Name Was Exits In Curriculum");

            message = "<div class='toast fade' style='background-color: red; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                    + "                <div class='toast-header'>"
                    + "                    <strong class='mr-auto'>Update PLO Failed</strong>"
                    + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                    + "                        <span aria-hidden='true'>&times;</span>"
                    + "                    </button>"
                    + "                </div>"
                    + "                <div class='toast-body'>"
                    + "                    Failed !"
                    + "                </div>"
                    + "            </div>";

            request.getSession().setAttribute("message", message);

            request.getRequestDispatcher("gui/admin/plo/detail.jsp").forward(request, response);

        }

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
