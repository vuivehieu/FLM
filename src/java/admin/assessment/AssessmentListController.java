/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.assessment;

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
import java.util.Comparator;
import java.util.List;
import model.Account;
import model.Assessment;

/**
 *
 * @author phanh
 */
@WebServlet(name = "AssessmentListController", urlPatterns = {"/assessmentList"})
public class AssessmentListController extends HttpServlet {

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
            out.println("<title>Servlet AssessmentListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AssessmentListController at " + request.getContextPath() + "</h1>");
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
        String key = request.getParameter("keySearch");

        DAO dao = new DAO();

        Account a = (Account) request.getSession().getAttribute("account");

        List<Assessment> list = dao.getAssessmentByAccountID(a.getAccountID());

        if (key != null && !key.isEmpty()) {
            list = dao.getAssessmentByAccountIDAndKey(a.getAccountID(), key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
//        if (sortType != null && !sortType.isEmpty()) {
//            String[] sort = sortType.split("_");
//            switch (sort[0]) {
//                case "id":
//                    Collections.sort(list, new Comparator<Assessment>() {
//                        @Override
//                        public int compare(PO o1, PO o2) {
//                            if (sort[1].equalsIgnoreCase("up")) {
//                                if (o1.getPoid() > o2.getPoid()) {
//                                    return 1;
//                                }
//                                return -1;
//                            }
//
//                            if (o1.getPoid() > o2.getPoid()) {
//                                return -1;
//                            }
//                            return 1;
//                        }
//                    });
//                    break;
//                case "name":
//                    Collections.sort(list, new Comparator<PO>() {
//                        @Override
//                        public int compare(PO o1, PO o2) {
//                            if (sort[1].equalsIgnoreCase("up")) {
//                                if (o1.getPoName().compareToIgnoreCase(o2.getPoName()) > 0) {
//                                    return 1;
//                                }
//                                return -1;
//                            }
//                            if (o1.getPoName().compareToIgnoreCase(o2.getPoName()) > 0) {
//                                return -1;
//                            }
//                            return 1;
//                        }
//                    }
//                    );
//                    break;
//                case "description":
//                    Collections.sort(list, new Comparator<PO>() {
//                        @Override
//                        public int compare(PO o1, PO o2) {
//                            if (sort[1].equalsIgnoreCase("up")) {
//                                if (o1.getPoDescription().compareTo(o2.getPoDescription()) > 0) {
//                                    return 1;
//                                }
//                                return -1;
//                            }
//
//                            if (o1.getPoDescription().compareTo(o2.getPoDescription()) > 0) {
//                                return -1;
//                            }
//                            return 1;
//                        }
//                    }
//                    );
//                    break;
//
//                default:
//                    throw new AssertionError();
//            }
//        }

        String xpage = request.getParameter("page");
        List<?> listByPage = new ArrayList<>();

        int page, numberPerPage = 6;
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
        listByPage = dao.listByPage(list, start, end);

        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("key", key);
        request.setAttribute("size", size);
        request.setAttribute("page", page);
        request.setAttribute("list", listByPage);
        request.setAttribute("start", start);
        request.setAttribute("sort", sortType);

        request.getRequestDispatcher("gui/admin/assessment/list.jsp").forward(request, response);
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
        String key = request.getParameter("keySearch");

        DAO dao = new DAO();

        Account a = (Account) request.getSession().getAttribute("account");

        List<Assessment> list = dao.getAssessmentByAccountID(a.getAccountID());

        if (key != null && !key.isEmpty()) {
            list = dao.getAssessmentByAccountIDAndKey(a.getAccountID(), key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, new Comparator<Assessment>() {
                        @Override
                        public int compare(Assessment o1, Assessment o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (o1.getAssid() > o2.getAssid()) {
                                    return 1;
                                }
                                return -1;
                            } else {
                                if (o1.getAssid() > o2.getAssid()) {
                                    return -1;
                                }
                                return 1;
                            }
                        }
                    });
                    break;
                case "syllabusName":
                    Collections.sort(list, new Comparator<Assessment>() {
                        @Override
                        public int compare(Assessment o1, Assessment o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (Custom.Common.getSyllabusNameENByID(o1.getSlbid()).compareTo(Custom.Common.getSyllabusNameENByID(o2.getSlbid())) > 0) {
                                    return 1;
                                }
                                return -1;
                            }
                            if (Custom.Common.getSyllabusNameENByID(o1.getSlbid()).compareTo(Custom.Common.getSyllabusNameENByID(o2.getSlbid())) > 0) {
                                return -1;
                            }
                            return 1;
                        }
                    }
                    );
                    break;
                case "category":
                    Collections.sort(list, new Comparator<Assessment>() {
                        @Override
                        public int compare(Assessment o1, Assessment o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (o1.getCategory().compareTo(o2.getCategory()) > 0) {
                                    return 1;
                                }
                                return -1;
                            } else {
                                if (o1.getCategory().compareTo(o2.getCategory()) > 0) {
                                    return -1;
                                }
                                return 1;
                            }
                        }
                    });
                    break;

                case "type":
                    Collections.sort(list, new Comparator<Assessment>() {
                        @Override
                        public int compare(Assessment o1, Assessment o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (o1.getType().compareTo(o2.getType()) > 0) {
                                    return 1;
                                }
                                return -1;
                            } else {
                                if (o1.getType().compareTo(o2.getType()) > 0) {
                                    return -1;
                                }
                                return 1;
                            }
                        }
                    });
                    break;

                default:
                    throw new AssertionError();
            }
        }

        String xpage = request.getParameter("page");
        List<?> listByPage = new ArrayList<>();

        int page, numberPerPage = 6;
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
        listByPage = dao.listByPage(list, start, end);

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
                    + "             <th width='5%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "id_down" : sortType.equalsIgnoreCase("id_up") ? "id_down" : "id_up") + "')\"  >ID <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("id_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "syllabusName_down" : sortType.equalsIgnoreCase("syllabusName_up") ? "syllabusName_down" : "syllabusName_up") + "')\" >Syl Name <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("syllabusName_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "category_down" : sortType.equalsIgnoreCase("category_up") ? "category_down" : "category_up") + "')\"  >Category <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("category_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "type_down" : sortType.equalsIgnoreCase("type_up") ? "type_down" : "type_up") + "')\"  >Type <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("type_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th width='7%'>Active <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>"
                    + "             <th> </th>"
                    + "         </tr>\n"
                    + "     </thead>\n"
                    + "  <tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>\n"
                        + "      <td>" + ((Assessment) list.get(i)).getAssid() + "</td>\n"
                        + "      <td>" + Custom.Common.getSyllabusNameENByID(((Assessment) list.get(i)).getSlbid()) + "</td>\n"
                        + "      <td> " + ((Assessment) list.get(i)).getCategory() + "</td>\n"
                        + "      <td>" + ((Assessment) list.get(i)).getType() + "</td>\n"
                        + "      <td><span class='fa " + (((Assessment) list.get(i)).isIsActive() ? "fa-check text-success" : "fa-times text-danger") + "'></span></td>\n"
                        + "      <td>\n"
                        + "          <a href='assessmentDetail?assid=" + ((Assessment) list.get(i)).getAssid() + "'>View</a>\n"
                        + "      </td>\n"
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
