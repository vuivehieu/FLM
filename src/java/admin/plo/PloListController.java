/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.plo;

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
import model.PLO;

/**
 *
 * @author phanh
 */
@WebServlet(name = "PloListController", urlPatterns = {"/ploList"})
public class PloListController extends HttpServlet {

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
            out.println("<title>Servlet PloListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PloListController at " + request.getContextPath() + "</h1>");
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

        List<PLO> list = dao.getAllPLO();

        if (key != null && !key.isEmpty()) {
            list = dao.getPloByKey(key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (PLO o1, PLO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getPloid() > o2.getPloid()) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getPloid() > o2.getPloid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "curCode":
                    Collections.sort(list, new Comparator<PLO>() {
                        @Override
                        public int compare(PLO o1, PLO o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (Custom.Common.getCurriculumCodeByPLOID(o1.getPloid()).compareTo(Custom.Common.getCurriculumCodeByPLOID(o2.getPloid())) > 0) {
                                    return 1;
                                }
                                return -1;
                            } else {
                                if (Custom.Common.getCurriculumCodeByPLOID(o1.getPloid()).compareTo(Custom.Common.getCurriculumCodeByPLOID(o2.getPloid())) > 0) {
                                    return -1;
                                }
                                return 1;
                            }
                        }
                    });
                    break;
                case "name":
                    Collections.sort(list, (PLO o1, PLO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getPloName().compareToIgnoreCase(o2.getPloName()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getPloName().compareToIgnoreCase(o2.getPloName()) > 0) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "description":
                    Collections.sort(list, (PLO o1, PLO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getPloDescription().compareTo(o2.getPloDescription()) > 0) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getPloDescription().compareTo(o2.getPloDescription()) > 0) {
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

        request.getRequestDispatcher("gui/admin/plo/list.jsp").forward(request, response);
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
        List<PLO> list = dao.getAllPLO();

        if (!key.isEmpty() && key != null) {
            list = dao.getPloByKey(key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (PLO o1, PLO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getPloid() > o2.getPloid()) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getPloid() > o2.getPloid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "name":
                    Collections.sort(list, (PLO o1, PLO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getPloName().compareToIgnoreCase(o2.getPloName()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getPloName().compareToIgnoreCase(o2.getPloName()) > 0) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                case "description":
                    Collections.sort(list, (PLO o1, PLO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getPloDescription().compareTo(o2.getPloDescription()) > 0) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getPloDescription().compareTo(o2.getPloDescription()) > 0) {
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

        int maxVisiblePages = 8;
        int pageRange = maxVisiblePages / 2;
        int startPage = Math.max(page - pageRange, 1);
        int endPage = Math.min(page + pageRange, numberOfPage);

        listByPage = new DAO().listByPage(list, start, end);

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
                    + "             <th width='5%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "id_down" : sortType.equalsIgnoreCase("id_up") ? "id_down" : "id_up") + "')\"  >ID <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("id_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "          <th width='10%'onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "curCode_down" : sortType.equalsIgnoreCase("curCode_up") ? "curCode_down" : "curCode_up") + "')\" >Cur Code <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th width=10% onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "name_down" : sortType.equalsIgnoreCase("name_up") ? "name_down" : "name_up") + "')\"  >PLO Name <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("name_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "description_down" : sortType.equalsIgnoreCase("description_up") ? "description_down" : "description_up") + "')\"  >Description <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("description_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th></th>"
                    + "         </tr>\n"
                    + "     </thead>\n"
                    + "  <tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>\n"
                        + "      <td>" + ((PLO) list.get(i)).getPloid() + "</td>\n"
                        + "      <td>" + Custom.Common.getCurriculumCodeByPLOID(((PLO) list.get(i)).getPloid()) + "</td>\n"
                        + "      <td>\n"
                        + "          <a href='#'>\n"
                        + "              <span class='list-enq-name'>" + ((PLO) list.get(i)).getPloName() + "</span>\n"
                        + "          </a>\n"
                        + "      </td>\n"
                        + "      <td>" + ((PLO) list.get(i)).getPloDescription() + "</td>\n"
                        + "      <td>\n"
                        + "          <a href='ploDetail?ploid=" + ((PLO) list.get(i)).getPloid() + "' class='ad-st-view'>View</a>\n"
                        + "      </td>\n"
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
