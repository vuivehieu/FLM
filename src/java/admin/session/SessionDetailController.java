/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.session;

import DAL.DAO;
import DAL.SessionDAO;
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
import java.util.ArrayList;
import model.Account;
import model.Session;

/**
 *
 * @author phanh
 */
@MultipartConfig

@WebServlet(name = "SessionDetailController", urlPatterns = {"/sessionDetail"})
public class SessionDetailController extends HttpServlet {

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
            out.println("<title>Servlet SessionDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SessionDetailController at " + request.getContextPath() + "</h1>");
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
            String sesid_raw = request.getParameter("sesid");
            int sesid = 0;

            try {
                sesid = Integer.parseInt(sesid_raw);
            } catch (NumberFormatException e) {
                System.out.println("SessionDetailController -> doGet(): " + e);
            }
            DAO dao = new DAO();

            Session session = dao.getSessionBySesid(sesid);
            request.setAttribute("session", session);
        }

        request.getRequestDispatcher("gui/admin/session/detail.jsp").forward(request, response);
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
        String sesNo_raw = request.getParameter("sesNo");
        int sesNo = 0;
        try {
            sesNo = Integer.parseInt(sesNo_raw);
        } catch (NumberFormatException e) {
            System.out.println("SessionDetailController -> doPost(): " + e);

        }
        String topic = request.getParameter("topic");
        String itu = request.getParameter("itu");
        String learningType = request.getParameter("learningType");
        String active = request.getParameter("isActive");
        boolean isActive = "true".equalsIgnoreCase(active);
        String urls = request.getParameter("urls");
        String studentMaterial = request.getParameter("studentMaterial");
        String studentTask = request.getParameter("studentTask");
        String note = request.getParameter("note");

        String slbid_raw = request.getParameter("slbid");
        int slbid = 0;

        try {
            slbid = Integer.parseInt(slbid_raw);

        } catch (NumberFormatException e) {
            System.out.println("SessionDetailController -> doPost(): " + e);
        }

        Part file = request.getPart("download");
        String fileName = file.getSubmittedFileName();
        String download = "";
        if (!fileName.isEmpty()) {
            String uploadPath = "C:/Users/phanh/OneDrive/Máy tính/Template-SWP391/G2/SWP391-G2/web/download/" + fileName;

            try {
                FileOutputStream fos = new FileOutputStream(uploadPath);
                InputStream is = file.getInputStream();

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();

                download = "download/" + fileName;

            } catch (IOException e) {
                System.out.println("sessionDetailController -> Update Download error: " + e);
            }

        }

        if (type != null) {
            SessionDAO sesDAO = new SessionDAO();
            String message = "";

            switch (type) {
                case "update":
                    String sesid_raw = request.getParameter("sesid");
                    int sesid = 0;
                    try {
                        sesid = Integer.parseInt(sesid_raw);
                    } catch (NumberFormatException e) {
                        System.out.println("SessionDetailController -> doPost(): " + e);
                    }
                    Session sesion = dao.getSessionBySesid(sesid);

                    if (sesDAO.checkSesionNoExistInSyllabus(slbid, sesNo) && sesion.getSesNo() != sesNo) {
                        message = "<div class='toast fade' style='background-color: red; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Update Session Failed !</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Failed !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);
                        request.setAttribute("session", sesion);
                        request.setAttribute("error", "SessionNo was Exist In Syllabus !");
                        request.getRequestDispatcher("gui/admin/session/detail.jsp").forward(request, response);

                    } else {
                        sesion.setSlbid(slbid);
                        sesion.setSesNo(sesNo);
                        sesion.setTopic(topic);
                        sesion.setITU(itu);
                        sesion.setLearningTeachingType(learningType);
                        sesion.setIsActive(isActive);
                        sesion.setUrls(urls);
                        sesion.setDowload(download);
                        sesion.setStudentMaterial(studentMaterial);
                        sesion.setStudentTask(studentTask);
                        sesion.setNote(note);

                        sesDAO.update(sesion);

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

                        response.sendRedirect("sessionDetail?sesid=" + sesid);
                    }

                    break;

                case "add":

                    if (!sesDAO.checkSesionNoExistInSyllabus(slbid, sesNo)) {
                        Account a = (Account) request.getSession().getAttribute("account");

                        Session newSession = new Session(0, slbid, sesNo, topic, learningType, itu, studentMaterial, download, studentTask, urls, note, isActive, a.getAccountID(), new ArrayList<>());

                        sesDAO.add(newSession);

                        message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Add Session Success</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Success !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        response.sendRedirect("sessionList");
                    } else {
                        message = "<div class='toast fade' style='background-color: red; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                                + "                <div class='toast-header'>"
                                + "                    <strong class='mr-auto'>Add Session Faild</strong>"
                                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                                + "                        <span aria-hidden='true'>&times;</span>"
                                + "                    </button>"
                                + "                </div>"
                                + "                <div class='toast-body'>"
                                + "                    Failed !"
                                + "                </div>"
                                + "            </div>";

                        request.getSession().setAttribute("message", message);

                        request.setAttribute("topic", topic);
                        request.setAttribute("itu", itu);
                        request.setAttribute("learningType", learningType);
                        request.setAttribute("isActive", isActive);
                        request.setAttribute("urls", urls);
                        request.setAttribute("studentMaterial", studentMaterial);
                        request.setAttribute("studentTask", studentTask);
                        request.setAttribute("note", note);
                        request.setAttribute("sesNo", sesNo);
                        request.setAttribute("type", type);
                        request.setAttribute("slbid", slbid);

                        request.setAttribute("error", "SessionNo was Exist In Syllabus !");
                        request.getRequestDispatcher("gui/admin/session/detail.jsp").forward(request, response);

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
