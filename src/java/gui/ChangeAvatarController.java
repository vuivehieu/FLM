/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gui;

import DAL.AccountDAO;
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
import model.Account;

/**
 *
 * @author phanh
 */
@MultipartConfig
@WebServlet(name = "ChangeAvatarController", urlPatterns = {"/changeAvatar"})
public class ChangeAvatarController extends HttpServlet {

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
            out.println("<title>Servlet ChangeAvatarController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeAvatarController at " + request.getContextPath() + "</h1>");
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

        request.getRequestDispatcher("gui/common/changeAvatar.jsp").forward(request, response);
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

        Account a = (Account) request.getSession().getAttribute("account");

        Part file = request.getPart("image");
        String fileName = file.getSubmittedFileName();
        String[] fields = fileName.split("\\.");
        String convert = a.getAccountID() + "." + fields[1];
        String avatar = "";
        if (!fileName.isEmpty()) {
            String uploadPath = "C:/Users/phanh/OneDrive/Máy tính/Template-SWP391/G2/SWP391-G2/web/images/avatar/" + convert;

            try {
                FileOutputStream fos = new FileOutputStream(uploadPath);
                InputStream is = file.getInputStream();

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();

                avatar = "images/avatar/" + convert;

            } catch (IOException e) {
                System.out.println(e);
            }
        }

        AccountDAO dao = new AccountDAO();

        dao.changeAvatar(a.getAccountID(), avatar);

        String message = "<div class='toast fade' style='background-color: green; color: white' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                + "                <div class='toast-header'>"
                + "                    <strong class='mr-auto'>Update Avatar Success !</strong>"
                + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                + "                        <span aria-hidden='true'>&times;</span>"
                + "                    </button>"
                + "                </div>"
                + "                <div class='toast-body'>"
                + "                    Success !"
                + "                </div>"
                + "            </div>";

        request.getSession().setAttribute("message", message);

        response.sendRedirect("home");

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
