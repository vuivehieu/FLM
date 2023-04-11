/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package common;

import DAL.AccountDAO;
import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author phanh
 */
@WebServlet(name = "ComfirmEmailController", urlPatterns = {"/comfirmEmail"})
public class ComfirmEmailController extends HttpServlet {

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
            out.println("<title>Servlet ComfirmEmailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComfirmEmailController at " + request.getContextPath() + "</h1>");
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
        // Lấy mã xác thực từ tham số trên URL
        String verificationCode = request.getParameter("code");
        AccountDAO dao = new AccountDAO();

        // Kiểm tra mã xác thực có hợp lệ hay không
        // Trong ví dụ này, chúng ta chỉ kiểm tra mã xác thực có bằng với một giá trị cứng hay không
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String code = new DAO().getCodeMailLast(account.getAccountID());
        PrintWriter out = response.getWriter();
        if (!code.isEmpty()) {
            if (verificationCode.equalsIgnoreCase(code)) {
                // Xác minh email thành công
                out.println("Email verified successfully!");
                account.setStatus(1);
                dao.updateStatus(account);
            } else {
                // Xác minh email thất bại
                out.println("Invalid verification code!");
            }
        } else {
            out.println("khong co code nao trong khoang 5p gan day");
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
        processRequest(request, response);
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
