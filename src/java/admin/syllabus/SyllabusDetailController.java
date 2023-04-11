/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.syllabus;

import DAL.DAO;
import DAL.SyllabusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Decision;
import model.Syllabus;

/**
 *
 * @author phanh
 */
@WebServlet(name = "SyllabusDetailController", urlPatterns = {"/syllabusDetail"})
public class SyllabusDetailController extends HttpServlet {

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
            out.println("<title>Servlet SyllabusDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SyllabusDetailController at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("type");

        if (type != null) {
            request.setAttribute("type", type);

        } else {
            String slbid_raw = request.getParameter("slbid");
            int slbid = 0;

            try {
                slbid = Integer.parseInt(slbid_raw);
            } catch (NumberFormatException e) {
                System.out.println("SyllabusDetailController -> doGet(): " + e);
            }
            DAO dao = new DAO();

            Syllabus syllabus = dao.getSyllabusBySlbID(slbid);
            request.setAttribute("syllabus", syllabus);
        }

        request.getRequestDispatcher("gui/admin/syllabus/detail.jsp").forward(request, response);
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
        String type = request.getParameter("type");
        String code = request.getParameter("subjectCode");
        String slbName_EN = request.getParameter("slbName_EN");
        String slbName_VI = request.getParameter("slbName_VI");
        String degreeLevel = request.getParameter("degreeLevel");
        String timeAllocation = request.getParameter("timeAllocation");
        String Active = request.getParameter("isActive");
        boolean isActive = "true".equalsIgnoreCase(Active);
        String Approved = request.getParameter("isApproved");
        boolean isApproved = "true".equalsIgnoreCase(Approved);

        String studentTask = request.getParameter("studentTask");
        String tool = request.getParameter("tool");
        String note = request.getParameter("note");
        String description = request.getParameter("description");
        String decisionNo = request.getParameter("decisionNo");
        String minToPass_raw = request.getParameter("minToPass");
        int minToPass = 0;
        try {
            minToPass = Integer.parseInt(minToPass_raw);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        DAO dao = new DAO();
        SyllabusDAO syllabusDAO = new SyllabusDAO();

        if (type != null) {
            String message = "";
            switch (type) {
                case "update":
                    String slbid_raw = request.getParameter("slbid");
                    int slbid = 0;

                    try {
                        slbid = Integer.parseInt(slbid_raw);
                    } catch (NumberFormatException e) {
                        System.out.println("SyllabusDetailController -> doPost(): " + e);
                    }

                    Syllabus syl = dao.getSyllabusBySlbID(slbid);
                    syl.setSubjectCode(code);
                    syl.setSlbName_EN(slbName_EN);
                    syl.setSlbName_VI(slbName_VI);
                    syl.setDegreeLevel(degreeLevel);
                    syl.setTimeAllocation(timeAllocation);
                    syl.setIsActive(isActive);
                    syl.setIsApproved(isApproved);
                    syl.setStudentTask(studentTask);
                    syl.setTool(tool);
                    syl.setNote(note);
                    syl.setDescription(description);
                    syl.getDecision().setDecisionNo(decisionNo);
                    syl.setMinAvgMarkToPass(minToPass);
                    syllabusDAO.update(syl);
                    
                    message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                            + "                <div class='toast-header'>"
                            + "                    <strong class='mr-auto'>Update Syllabus Success</strong>"
                            + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                            + "                        <span aria-hidden='true'>&times;</span>"
                            + "                    </button>"
                            + "                </div>"
                            + "                <div class='toast-body'>"
                            + "                    Success !"
                            + "                </div>"
                            + "            </div>";

                    request.getSession().setAttribute("message", message);

                    response.sendRedirect("syllabusDetail?slbid=" + slbid);
                    break;
                    
                case "add":
                    Account a = (Account)request.getSession().getAttribute("account");
                    Decision decicion = dao.getDecisionByDecisionNo(decisionNo);
                    Syllabus newSyllabus = new Syllabus(0, code, slbName_EN, slbName_VI, degreeLevel, timeAllocation, description, studentTask, tool, isApproved, isActive
                            , Custom.Common.getCurrentDate(), note, minToPass, decicion
                            , new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
                            , new ArrayList<>(), new ArrayList<>(), a.getAccountID());
                    
                    syllabusDAO.add(newSyllabus);
                    
                     message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                            + "                <div class='toast-header'>"
                            + "                    <strong class='mr-auto'>Add Syllabus Success</strong>"
                            + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                            + "                        <span aria-hidden='true'>&times;</span>"
                            + "                    </button>"
                            + "                </div>"
                            + "                <div class='toast-body'>"
                            + "                    Success !"
                            + "                </div>"
                            + "            </div>";

                    request.getSession().setAttribute("message", message);

                    response.sendRedirect("syllabusList");
                    break;

                default:
                    throw new AssertionError();
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
