package admin.combo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAL.ComboDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author phanh
 */
@WebServlet(urlPatterns = {"/comboAdd"})
public class addComboController extends HttpServlet {

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
            out.println("<title>Servlet addComboController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addComboController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("gui/admin/combo/add.jsp").forward(request, response);
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
        ComboDAO dao = new ComboDAO();

        String tag = request.getParameter("tag");
        String comboNameEN = request.getParameter("comboNameEN");
        String comboNameVI = request.getParameter("comboNameVI");
        String note = request.getParameter("note");
        String active = request.getParameter("isActive");
        boolean isActive = active.equalsIgnoreCase("true");

        if (tag != null && !tag.isEmpty()) {

            boolean check = dao.checkTagExist(tag, "");

            if (check) {
                request.setAttribute("tag", tag);
                request.setAttribute("comboNameEN", comboNameEN);
                request.setAttribute("comboNameVI", comboNameVI);
                request.setAttribute("note", note);
                request.setAttribute("isActive", isActive);

                request.setAttribute("error", "Tag Was Exist In DB");
                request.getRequestDispatcher("gui/admin/combo/add.jsp").forward(request, response);

            } else {
                dao.add(tag, comboNameEN, comboNameVI, note, isActive);

                String message = "<div class='toast fade' role='alert' aria-live='assertive' aria-atomic='true' data-delay='3000'>"
                        + "                <div class='toast-header'>"
                        + "                    <strong class='mr-auto'>Add Combo Success</strong>"
                        + "                    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>"
                        + "                        <span aria-hidden='true'>&times;</span>"
                        + "                    </button>"
                        + "                </div>"
                        + "                <div class='toast-body'>"
                        + "                    Success !"
                        + "                </div>"
                        + "            </div>";

                request.getSession().setAttribute("message", message);

                response.sendRedirect("comboList");
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
