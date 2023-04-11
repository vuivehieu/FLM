/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.po;

import DAL.DAO;
import DAL.PODAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PO;

/**
 *
 * @author phanh
 */
@WebServlet(name = "poDetailController", urlPatterns = {"/poDetail"})
public class poDetailController extends HttpServlet {

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
            out.println("<title>Servlet poDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet poDetailController at " + request.getContextPath() + "</h1>");
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
        String poid_raw = request.getParameter("poid");
        int poid = 0;
        try {
            poid = Integer.parseInt(poid_raw);
        } catch (NumberFormatException e) {
            System.out.println("PO Detail Controlelr -> doGet(): " + e);
        }

        DAO dao = new DAO();
        PO po = dao.getPOByID(poid);

        request.setAttribute("po", po);

        request.getRequestDispatcher("gui/admin/po/detail.jsp").forward(request, response);

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
        String poid_raw = request.getParameter("poid");
        int poid = 0;
        try {
            poid = Integer.parseInt(poid_raw);

        } catch (NumberFormatException e) {
            System.out.println("poDetailController -> doPost(): " + e);
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String active = request.getParameter("isActive");
        boolean isActive = active.equalsIgnoreCase("true");

        PODAO dao = new PODAO();
        String message = "";

        PO po = new DAO().getPOByID(poid);

        if (!dao.checkExist(name, po.getPoName())) {
            dao.update(name, description, isActive, poid);

            message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                    + "                <div class='toast-header'>"
                    + "                    <strong class='mr-auto'>Update PO Success</strong>"
                    + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                    + "                        <span aria-hidden='true'>&times;</span>"
                    + "                    </button>"
                    + "                </div>"
                    + "                <div class='toast-body'>"
                    + "                    Success !"
                    + "                </div>"
                    + "            </div>";

            request.getSession().setAttribute("message", message);

            response.sendRedirect("poDetail?poid=" + poid);
        } else {

            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("active", active);
            request.setAttribute("isActive", isActive);

            request.setAttribute("errorUpdate", "PO Name Was Exits In Curriculum");

            message = "<div class='toast fade' style='background-color: red; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                    + "                <div class='toast-header'>"
                    + "                    <strong class='mr-auto'>Update PO Failed</strong>"
                    + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                    + "                        <span aria-hidden='true'>&times;</span>"
                    + "                    </button>"
                    + "                </div>"
                    + "                <div class='toast-body'>"
                    + "                    Failed !"
                    + "                </div>"
                    + "            </div>";
            request.getSession().setAttribute("message", message);

            request.getRequestDispatcher("gui/admin/po/detail.jsp").forward(request, response);

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
