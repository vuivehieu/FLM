/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.material;

import DAL.DAO;
import DAL.MaterialDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.Account;
import model.Material;

/**
 *
 * @author phanh
 */
@WebServlet(name = "MaterialDetailController", urlPatterns = {"/materialDetail"})
public class MaterialDetailController extends HttpServlet {

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
            out.println("<title>Servlet MaterialDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MaterialDetailController at " + request.getContextPath() + "</h1>");
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
            String mid_raw = request.getParameter("mid");
            int mid = 0;

            try {
                mid = Integer.parseInt(mid_raw);
            } catch (NumberFormatException e) {
                System.out.println("SessionDetailController -> doGet(): " + e);
            }
            DAO dao = new DAO();

            Material material = dao.getMaterialByMid(mid);
            request.setAttribute("material", material);
        }

        request.getRequestDispatcher("gui/admin/material/detail.jsp").forward(request, response);

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
        String slbid_raw = request.getParameter("slbid");
        int slbid = 0;
        try {
            slbid = Integer.parseInt(slbid_raw);
        } catch (NumberFormatException e) {
            System.out.println("MaterialDetailController -> doPost() -> slbid: " + e);

        }

        String publishedDate = request.getParameter("publishedDate");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String edition = request.getParameter("edition");
        String isbn = request.getParameter("isbn");
        String description = request.getParameter("description");
        String note = request.getParameter("note");
        String active = request.getParameter("isActive");
        String mainMaterial = request.getParameter("isMainMaterial");
        String hardCopy = request.getParameter("isHardCopy");
        String online = request.getParameter("isOnline");

        boolean isActive = "true".equalsIgnoreCase(active);
        boolean isMainMaterial = "true".equalsIgnoreCase(mainMaterial);
        boolean isHardCopy = "true".equalsIgnoreCase(hardCopy);
        boolean isOnline = "true".equalsIgnoreCase(online);

        if (type != null) {
            MaterialDAO materialDAO = new MaterialDAO();
            String message = "";

            switch (type) {
                case "update":

                    String mid_raw = request.getParameter("mid");
                    int mid = 0;
                    try {
                        mid = Integer.parseInt(mid_raw);
                    } catch (NumberFormatException e) {
                        System.out.println("MaterialDetailController -> doPost() -> mid: " + e);
                    }
                    Material material = dao.getMaterialByMid(mid);

                    material.setPublishedDate(Date.valueOf(publishedDate));
                    material.setSlbid(slbid);
                    material.setAuthor(author);
                    material.setPublisher(publisher);
                    material.setEdition(edition);
                    material.setISBN(isbn);
                    material.setDescription(description);
                    material.setNote(note);
                    material.setIsActive(isActive);
                    material.setIsHardCopy(isHardCopy);
                    material.setIsMainMaterial(isMainMaterial);
                    material.setIsOnline(isOnline);

                    materialDAO.update(material);

                    message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                            + "                <div class='toast-header'>"
                            + "                    <strong class='mr-auto'>Update Material Success</strong>"
                            + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                            + "                        <span aria-hidden='true'>&times;</span>"
                            + "                    </button>"
                            + "                </div>"
                            + "                <div class='toast-body'>"
                            + "                    Success !"
                            + "                </div>"
                            + "            </div>";

                    request.getSession().setAttribute("message", message);

                    response.sendRedirect("materialDetail?mid=" + mid);

                    break;

                case "add":

                    Account a = (Account) request.getSession().getAttribute("account");

                    Material newMaterial = new Material(0, slbid, description, author, publisher, Date.valueOf(publishedDate), edition, isbn, isMainMaterial, isHardCopy, isOnline, isActive, note, a.getAccountID());

                    materialDAO.add(newMaterial);

                    message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                            + "                <div class='toast-header'>"
                            + "                    <strong class='mr-auto'>Add Material Success</strong>"
                            + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                            + "                        <span aria-hidden='true'>&times;</span>"
                            + "                    </button>"
                            + "                </div>"
                            + "                <div class='toast-body'>"
                            + "                    Success !"
                            + "                </div>"
                            + "            </div>";

                    request.getSession().setAttribute("message", message);

                    response.sendRedirect("materialList");

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
