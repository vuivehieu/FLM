/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.decision;

import DAL.DAO;
import DAL.DecisionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import model.Decision;

/**
 *
 * @author phanh
 */
@MultipartConfig
@WebServlet(name = "DecisonDetail", urlPatterns = {"/decisionDetail"})
public class DecisonDetail extends HttpServlet {

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
            out.println("<title>Servlet DecisonDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DecisonDetail at " + request.getContextPath() + "</h1>");
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
            String decisionNo = request.getParameter("decisionNo");

            DAO dao = new DAO();

            Decision decision = dao.getDecisionByDecisionNo(decisionNo);
            request.setAttribute("decision", decision);
        }

        request.getRequestDispatcher("gui/admin/decisionManager/detail.jsp").forward(request, response);
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

        String approvedDate = request.getParameter("approvedDate");
        String decisionName = request.getParameter("decisionName");
        String note = request.getParameter("note");

        String active = request.getParameter("isActive");
        boolean isActive = "true".equalsIgnoreCase(active);

        Part file = request.getPart("fileName");
        String fileName = file.getSubmittedFileName();
        String decisionFileName = "";
        if (!fileName.isEmpty()) {
            String uploadPath = "C:/Users/phanh/OneDrive/Máy tính/Template-SWP391/G2/SWP391-G2/web/decisionFileName/" + fileName;

            try {
                FileOutputStream fos = new FileOutputStream(uploadPath);
                InputStream is = file.getInputStream();

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();

                decisionFileName = "decisionFileName/" + fileName;

            } catch (IOException e) {
                System.out.println("sessionDetailController -> Update Download error: " + e);
            }

        }

        if (type != null) {
            DecisionDAO decisionDAO = new DecisionDAO();
            String message = "";
            String decisionNo = request.getParameter("decisionNo");

            switch (type) {
                case "update":

                    Decision decision = dao.getDecisionByDecisionNo(decisionNo);

                    if (decisionDAO.checkExits(decisionNo) && decision.getDecisionNo().equalsIgnoreCase(decisionNo)) {
                        message = "<div class='toast fade' style='background-color: red; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Update Decision Failed !</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Failed !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);
                        request.setAttribute("decision", decision);
                        request.setAttribute("error", "Decision No was Exist !");
                        request.getRequestDispatcher("gui/admin/decisionManager/detail.jsp").forward(request, response);

                    } else {
                        decision.setDecisionName(decisionName);
                        decision.setNote(note);
                        decision.setApprovedDate(Date.valueOf(approvedDate));
                        decision.setFileName(decisionFileName);
                        decision.setIsActive(isActive);

                        decisionDAO.update(decision);

                        message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Update Session Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("decisionList");
                    }

                    break;

                case "add":

                    if (!decisionDAO.checkExits(decisionNo)) {

                        Decision newDecision = new Decision(decisionNo, decisionName, Date.valueOf(approvedDate), note, Custom.Common.getCurrentDate(), fileName, isActive);

                        decisionDAO.add(newDecision);

                        message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Add Decision Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("decisionList");
                    } else {
                        message = "<div class='toast fade' style='background-color: red; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Add Decision Faild</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Failed !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        request.setAttribute("decisionName", decisionName);
                        request.setAttribute("note", note);
                        request.setAttribute("decisionNo", decisionNo);
                        request.setAttribute("isActive", isActive);
                        request.setAttribute("approvedDate", approvedDate);

                        request.setAttribute("error", "SessionNo was Exist In Syllabus !");
                        request.getRequestDispatcher("gui/admin/decisionManager/detail.jsp").forward(request, response);
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
