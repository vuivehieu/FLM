/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package admin.question;

import DAL.DAO;
import DAL.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Question;

/**
 *
 * @author phanh
 */
@WebServlet(name="QuestionDetailController", urlPatterns={"/questionDetail"})
public class QuestionDetailController extends HttpServlet {
   
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
            out.println("<title>Servlet QuestionDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionDetailController at " + request.getContextPath () + "</h1>");
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
        String type = request.getParameter("type");

        if (type != null) {
            request.setAttribute("type", type);

        } else {
            String qid_raw = request.getParameter("qid");
            int qid = 0;

            try {
                qid = Integer.parseInt(qid_raw);
            } catch (NumberFormatException e) {
                System.out.println("SessionDetailController -> doGet(): " + e);
            }
            DAO dao = new DAO();

            Question question = dao.getQuestionByQid(qid);
            request.setAttribute("question", question);
        }

        request.getRequestDispatcher("gui/admin/question/detail.jsp").forward(request, response);
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
        DAO dao = new DAO();

        String type = request.getParameter("type");
        String sesid_raw = request.getParameter("sesid");
        int sesid = 0;
        try {
            sesid = Integer.parseInt(sesid_raw);
        } catch (NumberFormatException e) {
            System.out.println("QuestionDetailController -> doPost() -> sesid: " + e);

        }
        
        System.out.println(sesid + ", " + sesid_raw);
        String qname = request.getParameter("qname");
        String details = request.getParameter("details");
        String answer = request.getParameter("answer");
        String active = request.getParameter("isActive");
        boolean isActive = "true".equalsIgnoreCase(active);
        
        
        if (type != null) {
            QuestionDAO quesDAO = new QuestionDAO();
            String message = "";

            switch (type) {
                case "update":

                    String qid_raw = request.getParameter("qid");
                    int qid = 0;
                    try {
                        qid = Integer.parseInt(qid_raw);
                    } catch (NumberFormatException e) {
                        System.out.println("QuestionDetailController -> doPost() -> qid: " + e);
                    }
                    Question question = dao.getQuestionByQid(qid);
                    System.out.println("qid: "+ qid);

                    if (quesDAO.checkQNameExist(sesid, qname) && !question.getQname().equals(qname)) {
                        message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Update Question Failed</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Failed !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);
                        request.setAttribute("question", question);
                        request.setAttribute("error", "Question Name was Exist In session ID: " + question.getSesid()+ " !");
                        request.getRequestDispatcher("gui/admin/question/detail.jsp").forward(request, response);

                    } else {
                        question.setSesid(sesid);
                        question.setQname(qname);
                        question.setDetails(details);
                        question.setAnswer(answer);
                        question.setIsActive(isActive);

                        quesDAO.update(question);

                        message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Update Question Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("questionDetail?qid=" + qid);
                    }

                    break;

                case "add":

                    if (!quesDAO.checkQNameExist(sesid, qname)) {
                        Account a = (Account) request.getSession().getAttribute("account");

                        Question newQuestion = new Question(0, sesid, qname, details, answer, isActive, a.getAccountID());
                        
                        quesDAO.add(newQuestion);

                        message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Add Question Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("questionList");
                    } else {
                        message = "<div class='toast fade' style='background-color: red; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Add Quesstion Faild</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Failed !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        request.setAttribute("sesid", sesid);
                        request.setAttribute("qname", qname);
                        request.setAttribute("details", details);
                        request.setAttribute("isActive", isActive);
                        request.setAttribute("answer", answer);
                        

                        request.setAttribute("error", "Question Name was Exist In session ID: " + sesid+ " !");
                        request.getRequestDispatcher("gui/admin/question/detail.jsp").forward(request, response);

                    }

                    break;

                default:
                    throw new AssertionError();
            }
        } 
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
