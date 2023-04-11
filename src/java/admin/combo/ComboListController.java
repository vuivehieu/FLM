/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.combo;

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
import model.Combo;

/**
 *
 * @author phanh
 */
@WebServlet(name = "ComboListController", urlPatterns = {"/comboList"})
public class ComboListController extends HttpServlet {

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
            out.println("<title>Servlet ComboListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComboListController at " + request.getContextPath() + "</h1>");
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

        List<Combo> list = dao.getAllCombo();

        if (key != null && !key.isEmpty()) {
            list = dao.getComboByKey(key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (Combo o1, Combo o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getCid() > o2.getCid()) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getCid() > o2.getCid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "tag":
                    Collections.sort(list, (Combo o1, Combo o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getTag().compareToIgnoreCase(o2.getTag()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getTag().compareToIgnoreCase(o2.getTag()) > 0) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "name":
                    Collections.sort(list, (Combo o1, Combo o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getComboName_EN().compareToIgnoreCase(o2.getComboName_EN()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getComboName_EN().compareToIgnoreCase(o2.getComboName_EN()) > 0) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "date":
                    Collections.sort(list, (Combo o1, Combo o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getCreateDate().compareTo(o2.getCreateDate()) > 0) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getCreateDate().compareTo(o2.getCreateDate()) > 0) {
                            return -1;
                        }
                        return 1;
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
        listByPage = new DAO().listByPage(list, start, end);

        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("key", key);
        request.setAttribute("size", size);
        request.setAttribute("page", page);
        request.setAttribute("list", listByPage);
        request.setAttribute("start", start);
        request.setAttribute("sort", sortType);

        request.getRequestDispatcher("gui/admin/combo/list.jsp").forward(request, response);
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
        List<Combo> list = dao.getAllCombo();

        if (!key.isEmpty() && key != null) {
            list = dao.getComboByKey(key);
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
        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, new Comparator<Combo>() {
                        @Override
                        public int compare(Combo o1, Combo o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (o1.getCid() > o2.getCid()) {
                                    return 1;
                                }
                                return -1;
                            }

                            if (o1.getCid() > o2.getCid()) {
                                return -1;
                            }
                            return 1;
                        }
                    });
                    break;
                case "curCode":
                    Collections.sort(list, new Comparator<Combo>() {
                        @Override
                        public int compare(Combo o1, Combo o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (Custom.Common.getCurriculumCodeByComboID(o1.getCid()).compareTo(Custom.Common.getCurriculumCodeByComboID(o2.getCid())) > 0) {
                                    return 1;
                                }
                                return -1;
                            } else {
                                if (Custom.Common.getCurriculumCodeByComboID(o1.getCid()).compareTo(Custom.Common.getCurriculumCodeByComboID(o2.getCid())) > 0) {
                                    return -1;
                                }
                                return 1;
                            }
                        }
                    });
                    break;
                case "tag":
                    Collections.sort(list, new Comparator<Combo>() {
                        @Override
                        public int compare(Combo o1, Combo o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (o1.getTag().compareToIgnoreCase(o2.getTag()) > 0) {
                                    return 1;
                                }
                                return -1;
                            }
                            if (o1.getTag().compareToIgnoreCase(o2.getTag()) > 0) {
                                return -1;
                            }
                            return 1;
                        }
                    }
                    );
                    break;
                case "name":
                    Collections.sort(list, new Comparator<Combo>() {
                        @Override
                        public int compare(Combo o1, Combo o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (o1.getComboName_EN().compareToIgnoreCase(o2.getComboName_EN()) > 0) {
                                    return 1;
                                }
                                return -1;
                            }
                            if (o1.getComboName_EN().compareToIgnoreCase(o2.getComboName_EN()) > 0) {
                                return -1;
                            }
                            return 1;
                        }
                    }
                    );
                    break;
                case "date":
                    Collections.sort(list, new Comparator<Combo>() {
                        @Override
                        public int compare(Combo o1, Combo o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (o1.getCreateDate().compareTo(o2.getCreateDate()) > 0) {
                                    return 1;
                                }
                                return -1;
                            }

                            if (o1.getCreateDate().compareTo(o2.getCreateDate()) > 0) {
                                return -1;
                            }
                            return 1;
                        }
                    }
                    );
                    break;

                default:
                    throw new AssertionError();
            }
        }

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
                    + "          <th width='10%'onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "curCode_down" : sortType.equalsIgnoreCase("curCode_up") ? "curCode_down" : "curCode_up") + "')\" >Cur Code <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "          <th width='10%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "tag_down" : sortType.equalsIgnoreCase("tag_up") ? "tag_down" : "tag_up") + "')\">Combo Tag <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("tag_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "          <th  onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "name_down" : sortType.equalsIgnoreCase("name_up") ? "name_down" : "name_up") + "')\">Name <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("name_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "          <th width='10%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "date_down" : sortType.equalsIgnoreCase("date_up") ? "date_down" : "date_up") + "')\" >CreateDate <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("date_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "          <th width='7%'>Status <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "          <th></th>\n"
                    + "      </tr>\n"
                    + "  </thead>\n"
                    + "  <tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>\n"
                        + "      <td>" + ((Combo) list.get(i)).getCid() + "</td>\n"
                        + "      <td>" + Custom.Common.getCurriculumCodeByComboID(((Combo) list.get(i)).getCid()) + "</td>\n"
                        + "      <td>" + ((Combo) list.get(i)).getTag() + "</td>\n"
                        + "      <td>\n"
                        + "          <a href='#'>\n"
                        + "             <span class='list-enq-name'>" + ((Combo) list.get(i)).getComboName_EN() + "</span>\n"
                        + "             <span class='list-enq-city'>" + ((Combo) list.get(i)).getComboName_VI() + "</span>\n"
                        + "          </a>\n"
                        + "      </td>\n"
                        + "      <td>" + Custom.Common.getDateFormat("dd/MM/yyyy", ((Combo) list.get(i)).getCreateDate()) + "</td>\n"
                        + "      <td>\n"
                        + "          <span class='label " + (((Combo) list.get(i)).isIsActive() ? "label-success" : "label-danger") + "'>" + (((Combo) list.get(i)).isIsActive() ? "Active" : "Block") + "</span>\n"
                        + "       </td>\n"
                        + "       <td>\n"
                        + "          <a href='comboDetail?cid=" + (((Combo) list.get(i)).getCid()) + "' class='ad-st-view'>View</a>\n"
                        + "        </td>\n"
                        + "</tr>\n";
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
