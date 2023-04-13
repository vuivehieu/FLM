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
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Account;
import org.json.JSONObject;

/**
 *
 * @author phanh
 */
@WebServlet(name = "ComfirmEmailController", urlPatterns = {"/comfirmEmail"})
public class ComfirmEmailController extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AccountDAO dao = new AccountDAO();
        
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        // Chuyển đổi chuỗi JSON thành đối tượng JSONObject
        JSONObject jsonObj = new JSONObject(sb.toString());
        // Lấy giá trị của thuộc tính "email"
         String email = jsonObj.getString("email");
         
        // xac nhận email chưa được đăng ký lần nào
        boolean checkEmail = dao.checkEmail(email);
        if(checkEmail){
            String codeSendMail = this.sendMail(request, response, email);
        
            // respon
            Map<String, String> options = new LinkedHashMap<>();
            options.put("code", codeSendMail);
            String json = new Gson().toJson(options);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }else {
             // respon
            Map<String, String> options = new LinkedHashMap<>();
            options.put("error", "The Email was registered");
            String json = new Gson().toJson(options);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
         
        
    }
    
    private String sendMail(HttpServletRequest request, HttpServletResponse response, String userEmail) throws ServletException, IOException {

        // Tạo mã xác thực duy nhất
        Random rand = new Random();
        String uuid = String.valueOf(rand.nextInt(90000) + 10000);

        final String from = "dothang4477@gmail.com";
        final String password = "gwmcckowmcnvazur";
        String host = "smtp.gmail.com";
        int port = 587;
        String to = userEmail;
        String subject = "Email verification";
        String content = "ma code cua ban la:" + uuid + " hay nhap vao o input de kich hoat";

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
            
            return uuid;

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
