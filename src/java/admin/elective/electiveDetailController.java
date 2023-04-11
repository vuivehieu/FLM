/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.elective;

import DAL.ComboDAO;
import DAL.DAO;
import DAL.ElectiveDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Elective;
import model.Subject;

/**
 *
 * @author phanh
 */
@WebServlet(name = "electiveDetailController", urlPatterns = {"/electiveDetail"})
public class electiveDetailController extends HttpServlet {

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
            out.println("<title>Servlet electiveDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet electiveDetailController at " + request.getContextPath() + "</h1>");
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

        DAO dao = new DAO();

        String eid_raw = request.getParameter("eid");
        int eid = 0;
        if (eid_raw != null) {

            try {
                eid = Integer.parseInt(eid_raw);

            } catch (NumberFormatException e) {
                System.out.println("ElectiveDetailControlelr -> doGet(): " + e);
            }

            Elective elective = dao.getElectiveByEid(eid);

            request.setAttribute("elective", elective);
        }

        String type = request.getParameter("type");
        if (type != null && !type.isEmpty()) {
            request.setAttribute("type", type);
            switch (type) {
                case "delete":
                    String subjectCode = request.getParameter("subjectCode");
                    ElectiveDAO eldao = new ElectiveDAO();
                    eldao.deleteSubject(eid, subjectCode);

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

                    response.sendRedirect("electiveDetail?eid=" + eid);
                    break;
                case "add":
                    request.getRequestDispatcher("gui/admin/elective/detail.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } else {

            request.getRequestDispatcher("gui/admin/elective/detail.jsp").forward(request, response);
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
        String eid_raw = request.getParameter("eid");
        int eid = 0;
        try {
            eid = Integer.parseInt(eid_raw);

        } catch (NumberFormatException e) {
            System.out.println("ElectiveDetailControlelr -> doGet(): " + e);
        }

        String type = request.getParameter("type");
        DAO dao = new DAO();

        ElectiveDAO edao = new ElectiveDAO();

        if (type != null && !type.isEmpty()) {
            String message = "";

            if (!type.equalsIgnoreCase("addSubject")) {
                String tag = request.getParameter("tag");
                String name = request.getParameter("name");
                String subjectName_EN = request.getParameter("subjectNameEN");
                String subjectName_VI = request.getParameter("subjectNameVI");
                String note = request.getParameter("note");

                String active = request.getParameter("isActive");
                boolean isActive = active.equalsIgnoreCase("true");

                switch (type) {
                    case "add":

                        List<Subject> subject = new ArrayList<>();

                        Elective elective = new Elective(eid, name, tag, subjectName_EN, subjectName_VI, note, Custom.Common.getCurrentDate(), isActive, subject);

                        edao.add(elective);

                        message = "<div class='toast fade' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Add Elective Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("electiveList");

                        break;
                    case "update":
                        Elective e = dao.getElectiveByEid(eid);
                        e.setTag(tag);
                        e.setEname(name);
                        e.setIsActive(isActive);
                        e.setNote(note);
                        e.setSubjectName_EN(subjectName_EN);
                        e.setSubjectName_VI(subjectName_VI);

                        edao.update(e);

                        message = "<div class='toast fade' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Update Elective Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("electiveDetail?eid=" + eid);
                        break;

                    default:
                        throw new AssertionError();
                }
            } else {
                String[] select = request.getParameterValues("selectSubject");

                if (select != null && select.length > 0) {
                    edao.addSubject(select, eid);
                }

                message = "<div class='toast fade' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                        + "                <div class='toast-header'>"
                        + "                    <strong class='mr-auto'>Update Elective Success</strong>"
                        + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                        + "                        <span aria-hidden='true'>&times;</span>"
                        + "                    </button>"
                        + "                </div>"
                        + "                <div class='toast-body'>"
                        + "                    Success !"
                        + "                </div>"
                        + "            </div>";

                request.getSession().setAttribute("message", message);

                response.sendRedirect("electiveDetail?eid=" + eid);
            }
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
