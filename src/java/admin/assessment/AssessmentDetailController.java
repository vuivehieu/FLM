/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.assessment;

import DAL.AssessmentDAO;
import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Assessment;

/**
 *
 * @author phanh
 */
@WebServlet(name = "AssessmentDetailController", urlPatterns = {"/assessmentDetail"})
public class AssessmentDetailController extends HttpServlet {

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
            out.println("<title>Servlet AssessmentDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AssessmentDetailController at " + request.getContextPath() + "</h1>");
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
            String assid_raw = request.getParameter("assid");
            int assid = 0;

            try {
                assid = Integer.parseInt(assid_raw);
            } catch (NumberFormatException e) {
                System.out.println("SessionDetailController -> doGet(): " + e);
            }
            DAO dao = new DAO();

            Assessment ass = dao.getAssessmentByassID(assid);
            request.setAttribute("ass", ass);
        }

        request.getRequestDispatcher("gui/admin/assessment/detail.jsp").forward(request, response);
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
        DAO dao = new DAO();

        String type = request.getParameter("type");

        String category = request.getParameter("category");
        String typeAss = request.getParameter("typeAss");
        String weight_raw = request.getParameter("weight");
        float weight = 0;
        try {

            weight = Float.parseFloat(weight_raw);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        String active = request.getParameter("isActive");
        boolean isActive = "true".equalsIgnoreCase(active);
        String part = request.getParameter("part");
        String duration = request.getParameter("duration");
        String questionType = request.getParameter("questionType");
        String noQuestion = request.getParameter("noQuestion");

        String knowledge_Skill = request.getParameter("knowledge_Skill");
        String gradingGuide = request.getParameter("gradingGuide");
        String note = request.getParameter("note");

        String slbid_raw = request.getParameter("slbid");
        int slbid = 0;

        try {
            slbid = Integer.parseInt(slbid_raw);

        } catch (NumberFormatException e) {
            System.out.println("SessionDetailController -> doPost(): " + e);
        }

        if (type != null) {
            AssessmentDAO assDAO = new AssessmentDAO();
            String message = "";

            switch (type) {
                case "update":
                    String assid_raw = request.getParameter("assid");
                    int assid = 0;
                    try {
                        assid = Integer.parseInt(assid_raw);
                    } catch (NumberFormatException e) {
                        System.out.println("SessionDetailController -> doPost(): " + e);
                    }
                    Assessment ass = dao.getAssessmentByassID(assid);

                    if (assDAO.checkCategoryExistInSyllabus(slbid, category) && ass.getCategory().equalsIgnoreCase(category)) {
                        message = "<div class='toast fade' style='background-color: red; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Update Assessment Failed !</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Failed !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);
                        request.setAttribute("ass", ass);
                        request.setAttribute("error", "Assessment was Exist In Syllabus !");
                        request.getRequestDispatcher("gui/admin/assessment/detail.jsp").forward(request, response);

                    } else {

                        ass.setCategory(category);
                        ass.setType(typeAss);
                        ass.setWeight(weight);
                        ass.setPart(Integer.parseInt(part));
                        ass.setDuration(duration);
                        ass.setQuestionType(questionType);
                        ass.setNoQuestion(noQuestion);
                        ass.setKnowledgeSkill(knowledge_Skill);
                        ass.setGradingGuide(gradingGuide);
                        ass.setIsActive(isActive);
                        ass.setNote(note);

                        assDAO.update(ass);

                        message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Update Assessment Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("assessmentDetail?assid=" + assid);
                    }

                    break;

                case "add":

                    if (!assDAO.checkCategoryExistInSyllabus(slbid, category)) {
                        Account a = (Account) request.getSession().getAttribute("account");

                        Assessment newAdd = new Assessment(0, slbid, category, typeAss, Integer.parseInt(part), weight, noQuestion, duration,
                                 questionType, noQuestion, knowledge_Skill, gradingGuide, note,
                                 isActive, new ArrayList<>(), a.getAccountID());

                        assDAO.add(newAdd);

                        message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Add Assessment Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("assessmentList");
                    } else {
                        message = "<div class='toast fade' style='background-color: red; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Add Assessment Faild</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Failed !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        request.setAttribute("category", category);
                        request.setAttribute("typeAss", typeAss);
                        request.setAttribute("weight", weight);
                        request.setAttribute("isActive", isActive);
                        request.setAttribute("duration", duration);
                        request.setAttribute("questionType", questionType);
                        request.setAttribute("noQuestion", noQuestion);
                        request.setAttribute("knowledge_Skill", knowledge_Skill);
                        request.setAttribute("gradingGuide", gradingGuide);
                        request.setAttribute("note", note);
                        request.setAttribute("slbid", slbid);

                        request.setAttribute("error", "Assessment was Exist In Syllabus !");
                        request.getRequestDispatcher("gui/admin/assessment/detail.jsp").forward(request, response);
                    }

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
