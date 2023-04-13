/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package common;

import DAL.AccountDAO;
import DAL.DAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Account;
import model.AccountGoogle;
import model.Role;
import org.json.JSONObject;

/**
 *
 * @author phanh
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/loginGoogle"})
public class LoginGoogleController extends HttpServlet {
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
            out.println("<title>Servlet LoginGoogleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginGoogleController at " + request.getContextPath() + "</h1>");
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
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            response.sendRedirect("home");
        } else {
            response.setContentType("text/html;charset=UTF-8");

            String accessToken = GoogleUtils.getToken(code);
            AccountGoogle accountGoogle = GoogleUtils.getUserInfo(accessToken);
//            PrintWriter out = response.getWriter();
//
//            out.println("id: " + accountGoogle.getId());
//            out.println("name: " + accountGoogle.getName());
//            out.println("email: " + accountGoogle.getEmail());
//            out.println("verified_email: " + accountGoogle.isVerified_email());
//            out.println("given_name: " + accountGoogle.getGiven_name());
//            out.println("family_name: " + accountGoogle.getFamily_name());
//            out.println("link: " + accountGoogle.getLink());
//            out.println("picture: " + accountGoogle.getPicture());
        String userName = "loginGoogle";
        String fullName = accountGoogle.getName();
        String email = accountGoogle.getEmail();
        String password = "";

        AccountDAO ad = new AccountDAO();
        
        Role r = new Role(1, "Guest");
        
        //true => khong co tk => tao moi, false => da co tk, cho login bang tk cu
        if(ad.checkEmail(email)){
            Account a = new Account(new DAO().getLastAccountID() + 1, userName, password, fullName, email, accountGoogle.getPicture(), false, 1, Custom.Common.getCurrentDate(), r);
            if(ad.register(a) != 0){
                // success
                this.sendMail(request, response);
                // cho login
                HttpSession session = request.getSession();
                if (session.getAttribute("account") == null) {
                                session.setAttribute("account", a);
                }
                response.sendRedirect("home");
            }else {
                request.setAttribute("error", "Đăng nhập bằng google thất bại");
                request.getRequestDispatcher("/gui/common/home.jsp").forward(request, response);
            }
        }else 
        {
            // cho login vơi tk cũ
            // get account by email
            Account b = ad.findAccountByEmail(email);
            
            HttpSession session = request.getSession();
            if (session.getAttribute("account") == null) {
                session.setAttribute("account", b);
            }
            response.sendRedirect("home");
        }

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
    
    private void sendMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminEmail = "dothang4477@gmail.com";

        // Thiết lập thông tin email
        final String from = "dothang4477@gmail.com";
        final String password = "gwmcckowmcnvazur";
        String host = "smtp.gmail.com";
        int port = 587;
        String to = adminEmail;
        String subject = "Notification Register";
        String content = "A new user login with google account successfully";

        // Thiết lập các thuộc tính email
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Tạo phiên gửi email và thiết lập thông tin người gửi
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage và thiết lập các thuộc tính email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            // Gửi email
            Transport.send(message);

            // Chuyển hướng đến trang xác nhận email
        } catch (MessagingException e) {
            throw new RuntimeException("error: " + e);
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
