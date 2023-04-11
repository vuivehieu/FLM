/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.question;

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
import model.Question;

/**
 *
 * @author phanh
 */
@WebServlet(name = "QuestionListController", urlPatterns = {"/questionList"})
public class QuestionListController extends HttpServlet {

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
            out.println("<title>Servlet QuestionListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionListController at " + request.getContextPath() + "</h1>");
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

        Account a = (Account) request.getSession().getAttribute("account");
        DAO dao = new DAO();

        List<Question> list = dao.getQuestionByAccountID(a.getAccountID());

        String key = request.getParameter("keySearch");

        if (key != null && !key.isEmpty()) {
            list = dao.getQuestionByAccountIDAndKey(a.getAccountID(), key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (Question o1, Question o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getQid() > o2.getQid()) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getQid() > o2.getQid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "sesid":
                    Collections.sort(list, (Question o1, Question o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getSesid() > o2.getSesid()) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getSesid() > o2.getSesid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "name":
                    Collections.sort(list, (Question o1, Question o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getQname().compareToIgnoreCase(o2.getQname()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getQname().compareToIgnoreCase(o2.getQname()) > 0) {
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

        int maxVisiblePages = 8;
        int pageRange = maxVisiblePages / 2;
        int startPage = Math.max(page - pageRange, 1);
        int endPage = Math.min(page + pageRange, numberOfPage);

        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("key", key);
        request.setAttribute("size", size);
        request.setAttribute("page", page);
        request.setAttribute("list", listByPage);
        request.setAttribute("start", start);
        request.setAttribute("sort", sortType);

        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);

        request.getRequestDispatcher("gui/admin/question/list.jsp").forward(request, response);
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
        DAO dao = new DAO();

        List<Question> list = dao.getQuestionByAccountID(a.getAccountID());

        String key = request.getParameter("keySearch");

        if (key != null && !key.isEmpty()) {
            list = dao.getQuestionByAccountIDAndKey(a.getAccountID(), key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (Question o1, Question o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getQid() > o2.getQid()) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getQid() > o2.getQid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "sesid":
                    Collections.sort(list, (Question o1, Question o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getSesid() > o2.getSesid()) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getSesid() > o2.getSesid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "name":
                    Collections.sort(list, (Question o1, Question o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getQname().compareToIgnoreCase(o2.getQname()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getQname().compareToIgnoreCase(o2.getQname()) > 0) {
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

        int maxVisiblePages = 8;
        int pageRange = maxVisiblePages / 2;
        int startPage = Math.max(page - pageRange, 1);
        int endPage = Math.min(page + pageRange, numberOfPage);

        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("key", key);
        request.setAttribute("size", size);
        request.setAttribute("page", page);
        request.setAttribute("list", listByPage);
        request.setAttribute("start", start);
        request.setAttribute("sort", sortType);

        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(getResponse(listByPage, key, page, numberOfPage, sortType, startPage, endPage));
    }

    public String getResponse(List<?> list, String key, int page, int numberOfPage, String sortType, int startPage, int endPage) {
        String response = "";

        if (list.isEmpty()) {
            response = "<h3 class='text-center text-danger'>Not Found '" + key + "'.</h3>";
        } else {
            response += "<table id='' class='table table-hover' style='width:100%'>\n"
                    + "      <thead>\n"
                    + "          <tr>\n"
                    + "             <th width='10%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "id_down" : sortType.equalsIgnoreCase("id_up") ? "id_down" : "id_up") + "')\"  >QID <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("id_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th width='10%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "sesid_down" : sortType.equalsIgnoreCase("sesid_up") ? "sesid_down" : "sesid_up") + "')\"  >SesID <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("sesid_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th  onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "name_down" : sortType.equalsIgnoreCase("name_up") ? "name_down" : "name_up") + "')\"  >Topic <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("name_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th >Author</th>\n"
                    + "             <th>Active</th>\n"
                    + "             <th></th>"
                    + "         </tr>\n"
                    + "     </thead>\n"
                    + "  <tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>\n"
                        + "      <td>" + ((Question) list.get(i)).getQid() + "</td>\n"
                        + "      <td>" + ((Question) list.get(i)).getSesid()+ "</td>\n"
                        + "      <td>" + ((Question) list.get(i)).getQname()+ "</td>\n"
                        + "      <td>" + ((Question) list.get(i)).getAccountID() + "</td>\n"
                        + "      <td><span class='fa " + (((Question) list.get(i)).isIsActive() ? "fa-check text-success" : "fa-times text-danger") + "'></span></td>\n"
                        + "      <td><a href='questionDetail?qid=" + ((Question) list.get(i)).getSesid() + "'>Edit</a></td>\n"
                        + "  </tr>";
            }

            response += "  </tbody>\n"
                    + "</table>\n"
                    + "<div class='pg-pagina'>\n"
                    + "  <ul class='pagination'>\n"
                    + "      <li class='" + (page == 1 ? "disabled" : "waves-effect") + "'><a onclick=\"changePage('" + (page - 1) + "', '" + sortType + "')\">Pre</a></li>\n";
            if (startPage > 1) {
                response += "<li class='waves-effect'><a onclick=\"changePage('" + (page - 1) + "', '" + sortType + "')\">...</a></li>\n";
            }
            for (int i = startPage; i <= endPage; i++) {
                response += "<li class='" + (page == i ? "active" : "waves-effect") + "'><a onclick=\"changePage('" + (i) + "', '" + sortType + "')\">" + (i) + "</a>"
                        + "</li>\n";
            }
            if (endPage < numberOfPage) {
                response += "<li class='waves-effect'><a onclick=\"changePage('" + (page + 1) + "', '" + sortType + "')\">...</a></li>\n";
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
