/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.combo;

import DAL.ComboDAO;
import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Combo;

/**
 *
 * @author phanh
 */
@WebServlet(name = "ComboDetailController", urlPatterns = {"/comboDetail"})
public class ComboDetailController extends HttpServlet {

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
            out.println("<title>Servlet ComboDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComboDetailController at " + request.getContextPath() + "</h1>");
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
        String cid_raw = request.getParameter("cid");
        int cid = 0;
        try {
            cid = Integer.parseInt(cid_raw);

        } catch (NumberFormatException e) {
            System.out.println("ComboDetailController -> doGet(): " + e);
        }
        DAO dao = new DAO();
        Combo combo = dao.getComboByCID(cid);
        request.setAttribute("combo", combo);

        String type = request.getParameter("type");

        if (type != null && type.equalsIgnoreCase("delete")) {
            String subjectCode = request.getParameter("subjectCode");
            String semester = request.getParameter("semester");
            ComboDAO cbdao = new ComboDAO();
            cbdao.delete(cid, subjectCode, semester);

            String message = "<div class='toast fade' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                    + "                <div class='toast-header'>"
                    + "                    <strong class='mr-auto'>Delete Subject Success</strong>"
                    + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                    + "                        <span aria-hidden='true'>&times;</span>"
                    + "                    </button>"
                    + "                </div>"
                    + "                <div class='toast-body'>"
                    + "                    Success !"
                    + "                </div>"
                    + "            </div>";

            request.getSession().setAttribute("message", message);

            response.sendRedirect("comboDetail?cid=" + cid);
        } else if (type == null) {
            request.getRequestDispatcher("gui/admin/combo/detail.jsp").forward(request, response);

        }

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
        ComboDAO dao = new ComboDAO();
        String type = request.getParameter("type");
        String cid_raw = request.getParameter("cid");
        int cid = 0;
        try {
            cid = Integer.parseInt(cid_raw);

        } catch (NumberFormatException e) {
            System.out.println("ComboDetailController -> doPost(): " + e);
        }

        switch (type) {
            case "update":

                String tag = request.getParameter("tag");
                String comboNameEN = request.getParameter("comboNameEN");
                String comboNameVI = request.getParameter("comboNameVI");
                String note = request.getParameter("note");
                String active = request.getParameter("isActive");
                boolean isActive = active.equalsIgnoreCase("true");
                Combo combo = new DAO().getComboByCID(cid);

                if (tag != null && !tag.isEmpty()) {
                    boolean check = dao.checkTagExist(tag, combo.getTag());

                    if (check) {
                        request.setAttribute("combo", combo);
                        request.setAttribute("error", "Tag Was Exist In DB");
                        request.getRequestDispatcher("gui/admin/combo/detail.jsp").forward(request, response);

                    } else {
                        combo.setTag(tag);
                        combo.setComboName_EN(comboNameEN);
                        combo.setComboName_VI(comboNameVI);
                        combo.setNote(note);
                        combo.setIsActive(isActive);
                        dao.updateCombo(combo);

                        String message = "<div class='toast fade' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Update Combo Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("comboDetail?cid=" + cid);
                    }
                }

                break;

            case "add":

                String[] select = request.getParameterValues("selectSubject");

                if (select != null && select.length > 0) {
                    dao.addSubjectToCombo(select, cid);
                }

                String message = "<div class='toast fade' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                        + "                <div class='toast-header'>"
                        + "                    <strong class='mr-auto'>Update Combo Success</strong>"
                        + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                        + "                        <span aria-hidden='true'>&times;</span>"
                        + "                    </button>"
                        + "                </div>"
                        + "                <div class='toast-body'>"
                        + "                    Success !"
                        + "                </div>"
                        + "            </div>";

                request.getSession().setAttribute("message", message);

                response.sendRedirect("comboDetail?cid=" + cid);

                break;

            default:
                throw new AssertionError();
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
