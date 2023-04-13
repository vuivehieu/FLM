/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package common;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AccountGoogle;
import org.json.JSONObject;
import static xin.zhuyao.httputil.HttpUtils.get;
import static xin.zhuyao.httputil.HttpUtils.post;

/**
 *
 * @author phanh
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/loginGoogle"})
public class LoginGoogleController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final String CLIENT_ID = "5268197056-r6agg8pmul30d9pjd6f1ug2vsregk1h5.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8080/SWP391-G2/home";
    private static final String SCOPE = "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email";

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
//        String code = request.getParameter("code");
//
//        if (code == null || code.isEmpty()) {
//            response.sendRedirect("home");
//        } else {
//            response.setContentType("text/html;charset=UTF-8");
//
//            String accessToken = GoogleUtils.getToken(code);
//            AccountGoogle accountGoogle = GoogleUtils.getUserInfo(accessToken);
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
//
//        }


        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            String loginURL = "https://accounts.google.com/o/oauth2/auth?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI
                    + "&scope=" + SCOPE + "&response_type=code";
            response.sendRedirect(loginURL);
        } else {
            String tokenURL = "https://accounts.google.com/o/oauth2/token";
            String payload = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&redirect_uri="
                    + REDIRECT_URI + "&grant_type=authorization_code" + "&code=" + code;
            String json = post(tokenURL, payload);
//             post("https://accounts.google.com/o/oauth2/token", params);
            JSONObject jsonObject = new JSONObject(json);
            String accessToken = jsonObject.getString("access_token");
            String profileURL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
            json = get(profileURL);
            jsonObject = new JSONObject(json);
            String email = jsonObject.getString("email");
            String name = jsonObject.getString("name");
            // Do something with email and name
            response.sendRedirect("welcome.jsp");
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
