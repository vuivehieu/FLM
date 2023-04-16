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
import java.io.BufferedReader;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Account;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Role;
import org.json.JSONObject;

/**
 *
 * @author phanh
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        // Chuyển đổi chuỗi JSON thành đối tượng JSONObject
        JSONObject jsonObj = new JSONObject(sb.toString());
        // Lấy giá trị của thuộc tính "email"
         String userName = jsonObj.getString("userName");
         String fullName = jsonObj.getString("fullName");
         String email = jsonObj.getString("email");
         String password = jsonObj.getString("password");
        
        AccountDAO ad = new AccountDAO();
        
        Role r = new Role(1, "Guest");
        Account a = new Account(new DAO().getLastAccountID() + 1, userName, password, fullName, email, "images/avatar/default.png", false, 1, Custom.Common.getCurrentDate(), r);
        
        if(ad.checkRegisterEdit(userName, email)){
            if(ad.register(a) != 0){
            // success
            this.sendMail(request, response);
            Map<String, String> options = new LinkedHashMap<>();
            options.put("messageRegister", "Registration success");
            String json = new Gson().toJson(options);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            }else {
                // response
                Map<String, String> options = new LinkedHashMap<>();
                options.put("error", "Registration failed");
                String json = new Gson().toJson(options);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        }else 
        {
            // response
            Map<String, String> options = new LinkedHashMap<>();
            options.put("error", "Registration failed, User Name already exists, please enter another account");
            String json = new Gson().toJson(options);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        

    }

    private void sendMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminEmail = "dothang4477@gmail.com";

        // Tạo mã xác thực duy nhất
//        String uuid = UUID.randomUUID().toString();
        Random rand = new Random();
        String uuid = String.valueOf(rand.nextInt(90000) + 10000);

        // Lưu mã xác thực trong cơ sở dữ liệu của bạn để sử dụng sau này
        // Thiết lập thông tin email
        final String from = "dothang4477@gmail.com";
        final String password = "gwmcckowmcnvazur";
        String host = "smtp.gmail.com";
        int port = 587;
        String to = adminEmail;
        String subject = "Notification Register";
        String content = "A new user has successfully registered";

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
