/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.syllabus;

import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Account;
import model.Syllabus;

/**
 *
 * @author phanh
 */
@WebServlet(name = "SyllabusListController", urlPatterns = {"/syllabusList"})
public class SyllabusListController extends HttpServlet {

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
            out.println("<title>Servlet SyllabusListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SyllabusListController at " + request.getContextPath() + "</h1>");
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
        DAO dao = new DAO();

        Account a = (Account) request.getSession().getAttribute("account");

        List<Syllabus> list = dao.getSyllabusByAccountID(a.getAccountID());

        String key = request.getParameter("keySearch");

        if (key != null && !key.isEmpty()) {
            list = dao.getSyllabusByKetAndAccountID(key, a.getAccountID());
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (Syllabus o1, Syllabus o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getSlbid() > o2.getSlbid()) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getSlbid() > o2.getSlbid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "code":
                    Collections.sort(list, (Syllabus o1, Syllabus o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getSubjectCode().compareToIgnoreCase(o2.getSubjectCode()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getSubjectCode().compareToIgnoreCase(o2.getSubjectCode()) > 0) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "name":
                    Collections.sort(list, (Syllabus o1, Syllabus o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getSlbName_EN().compareToIgnoreCase(o2.getSlbName_EN()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getSlbName_EN().compareToIgnoreCase(o2.getSlbName_EN()) > 0) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
//                case "date":
//                    Collections.sort(list, (Syllabus o1, Syllabus o2) -> {
//                        if (sort[1].equalsIgnoreCase("up")) {
//                            if (o1.getCreateDate().compareTo(o2.getCreateDate()) > 0) {
//                                return 1;
//                            }
//                            return -1;
//                        }
//
//                        if (o1.getCreateDate().compareTo(o2.getCreateDate()) > 0) {
//                            return -1;
//                        }
//                        return 1;
//                    });
//                    break;

                default:
                    throw new AssertionError();
            }
        }

        String xpage = request.getParameter("page");
        List<?> listByPage = new ArrayList<>();

        int page, numberPerPage = 10;
        int size;
        if (list.isEmpty()) {
            size = 0;
        } else {
            size = list.size();
        }
        int numberOfPage = ((size % numberPerPage == 0) ? (size / numberPerPage) : (size / numberPerPage + 1));
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberPerPage;
        end = Math.min(page * numberPerPage, size);
        listByPage = new DAO().listByPage(list, start, end);

        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("key", key);
        request.setAttribute("size", size);
        request.setAttribute("page", page);
        request.setAttribute("list", listByPage);
        request.setAttribute("start", start);
        request.setAttribute("sort", sortType);

        request.getRequestDispatcher("gui/admin/syllabus/list.jsp").forward(request, response);
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

        Account a = (Account) request.getSession().getAttribute("account");

        List<Syllabus> list = dao.getSyllabusByAccountID(a.getAccountID());

        String key = request.getParameter("keySearch");

        if (key != null && !key.isEmpty()) {
            list = dao.getSyllabusByKetAndAccountID(key, a.getAccountID());
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (Syllabus o1, Syllabus o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getSlbid() > o2.getSlbid()) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getSlbid() > o2.getSlbid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "code":
                    Collections.sort(list, (Syllabus o1, Syllabus o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getSubjectCode().compareToIgnoreCase(o2.getSubjectCode()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getSubjectCode().compareToIgnoreCase(o2.getSubjectCode()) > 0) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "name":
                    Collections.sort(list, (Syllabus o1, Syllabus o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getSlbName_EN().compareToIgnoreCase(o2.getSlbName_EN()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getSlbName_EN().compareToIgnoreCase(o2.getSlbName_EN()) > 0) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
//                case "date":
//                    Collections.sort(list, (Syllabus o1, Syllabus o2) -> {
//                        if (sort[1].equalsIgnoreCase("up")) {
//                            if (o1.getCreateDate().compareTo(o2.getCreateDate()) > 0) {
//                                return 1;
//                            }
//                            return -1;
//                        }
//
//                        if (o1.getCreateDate().compareTo(o2.getCreateDate()) > 0) {
//                            return -1;
//                        }
//                        return 1;
//                    });
//                    break;

                default:
                    throw new AssertionError();
            }
        }

        String xpage = request.getParameter("page");
        List<?> listByPage = new ArrayList<>();

        int page, numberPerPage = 10;
        int size;
        if (list.isEmpty()) {
            size = 0;
        } else {
            size = list.size();
        }
        int numberOfPage = ((size % numberPerPage == 0) ? (size / numberPerPage) : (size / numberPerPage + 1));
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberPerPage;
        end = Math.min(page * numberPerPage, size);
        listByPage = new DAO().listByPage(list, start, end);

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(getResponse(listByPage, key, page, numberOfPage, sortType));
    }

    public String getResponse(List<?> list, String key, int page, int numberOfPage, String sortType) {
        String response = "";

        if (list.isEmpty()) {
            response = "<h3 class='text-center text-danger'>Not Found '" + key + "'.</h3>";
        } else {
            response += "<table id='' class='table table-hover' style='width:100%'>\n"
                    + "      <thead>\n"
                    + "          <tr>\n"
                    + "          <th width='5%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "id_down" : sortType.equalsIgnoreCase("id_up") ? "id_down" : "id_up") + "')\"  >ID <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("id_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "          <th width='15%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "code_down" : sortType.equalsIgnoreCase("code_up") ? "code_down" : "code_up") + "')\">Subject Code<span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("code_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "          <th onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "name_down" : sortType.equalsIgnoreCase("name_up") ? "name_down" : "name_up") + "')\">Syllabus Name <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("name_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    //                    + "          <th width='15%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "date_down" : sortType.equalsIgnoreCase("date_up") ? "date_down" : "date_up") + "')\" >CreateDate <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("date_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n" + "          <th >DecisionNo</th>\n"
                    + "          <th>DecisionNo</th>\n"
                    + "          <th>Active</th>\n"
                    + "          <th>Approved</th>\n"
                    + "          <th></th>\n"
                    + "      </tr>"
                    + "  </thead>\n"
                    + "  <tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>\n"
                        + "      <td>" + ((Syllabus) list.get(i)).getSlbid() + "</td>\n"
                        + "      <td>" + ((Syllabus) list.get(i)).getSubjectCode() + "</td>\n"
                        + "      <td>\n"
                        + "          <a href='#'>\n"
                        + "              <span class='list-enq-name'>" + ((Syllabus) list.get(i)).getSlbName_EN() + "</span>\n"
                        + "              <span class='list-enq-city'>" + ((Syllabus) list.get(i)).getSlbName_VI() + "</span>\n"
                        + "          </a>\n"
                        + "      </td>\n"
                        + "      <td>" + ((Syllabus) list.get(i)).getDecision().getDecisionNo() + "</td>\n"
                        + "      <td><span class='fa " + (((Syllabus) list.get(i)).isIsActive() ? "fa-check text-success" : "fa-times text-danger") + "'></span></td>\n"
                        + "      <td><span class='fa " + (((Syllabus) list.get(i)).isIsApproved() ? "fa-check text-success" : "fa-times text-danger") + "'></span></td>\n"
                        + "      <td><a href='syllabusDetail?slbid=" + ((Syllabus) list.get(i)).getSlbid() + "'>Edit</a></td>\n"
                        + "  </tr>";
            }

            response += "  </tbody>\n"
                    + "</table>\n"
                    + "<div class='pg-pagina'>\n"
                    + "  <ul class='pagination'>\n"
                    + "      <li class='" + (page == 1 ? "disabled" : "waves-effect") + "'><a onclick=\"changePage('" + (page - 1) + "', '" + sortType + "')\">Pre</a></li>\n";
            for (int i = 1; i <= numberOfPage; i++) {
                response += "<li class='" + (page == i ? "active" : "waves-effect") + "'><a onclick=\"changePage('" + (i) + "', '" + sortType + "')\">" + (i) + "</a>"
                        + "</li>\n";
            }
            response += "      <li class='" + (page == numberOfPage ? "disabled" : "waves-effect") + "'><a onclick=\"changePage('" + (page + 1) + "', '" + sortType + "')\">Next</a></li>\n"
                    + "  </ul>\n"
                    + "</div>";
        }

        return response;
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
