/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.clo;

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
import model.LO;
import model.PLO;

/**
 *
 * @author phanh
 */
@WebServlet(name = "CLOListController", urlPatterns = {"/cloList"})
public class CLOListController extends HttpServlet {

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
            out.println("<title>Servlet CLOListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CLOListController at " + request.getContextPath() + "</h1>");
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

        List<LO> list = dao.getAllLO();

        if (key != null && !key.isEmpty()) {
            list = dao.GetLoByKey(key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (LO o1, LO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getLoid() > o2.getLoid()) {
                                return 1;
                            }
                            return -1;
                        } else {
                            if (o1.getLoid() > o2.getLoid()) {
                                return -1;
                            }
                            return 1;
                        }

                    });
                    break;
                case "syllabusName":
                    Collections.sort(list, new Comparator<LO>() {
                        @Override
                        public int compare(LO o1, LO o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (Custom.Common.getSyllabusNameENByID(o1.getSlbid()).compareTo(Custom.Common.getSyllabusNameENByID(o2.getSlbid())) > 0) {
                                    return 1;
                                }
                                return -1;
                            } else {
                                if (Custom.Common.getSyllabusNameENByID(o1.getSlbid()).compareTo(Custom.Common.getSyllabusNameENByID(o2.getSlbid())) > 0) {
                                    return -1;
                                }
                                return 1;
                            }
                        }
                    });
                    break;
                case "name":
                    Collections.sort(list, (LO o1, LO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getCloName().compareToIgnoreCase(o2.getCloName()) > 0) {
                                return 1;
                            }
                            return -1;
                        } else {
                            if (o1.getCloName().compareToIgnoreCase(o2.getCloName()) > 0) {
                                return -1;
                            }
                            return 1;
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
        request.getRequestDispatcher("gui/admin/clo/list.jsp").forward(request, response);
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

        List<LO> list = dao.getAllLO();

        if (key != null && !key.isEmpty()) {
            list = dao.GetLoByKey(key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (LO o1, LO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getLoid() > o2.getLoid()) {
                                return 1;
                            }
                            return -1;
                        } else {
                            if (o1.getLoid() > o2.getLoid()) {
                                return -1;
                            }
                            return 1;
                        }

                    });
                    break;
                case "syllabusName":
                    Collections.sort(list, new Comparator<LO>() {
                        @Override
                        public int compare(LO o1, LO o2) {
                            if (sort[1].equalsIgnoreCase("up")) {
                                if (Custom.Common.getSyllabusNameENByID(o1.getSlbid()).compareTo(Custom.Common.getSyllabusNameENByID(o2.getSlbid())) > 0) {
                                    return 1;
                                }
                                return -1;
                            } else {
                                if (Custom.Common.getSyllabusNameENByID(o1.getSlbid()).compareTo(Custom.Common.getSyllabusNameENByID(o2.getSlbid())) > 0) {
                                    return -1;
                                }
                                return 1;
                            }
                        }
                    });
                    break;
                case "name":
                    Collections.sort(list, (LO o1, LO o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getCloName().compareToIgnoreCase(o2.getCloName()) > 0) {
                                return 1;
                            }
                            return -1;
                        } else {
                            if (o1.getCloName().compareToIgnoreCase(o2.getCloName()) > 0) {
                                return -1;
                            }
                            return 1;
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
                    + "             <th width='5%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "id_down" : sortType.equalsIgnoreCase("id_up") ? "id_down" : "id_up") + "')\"  >ID <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("id_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "syllabusName_down" : sortType.equalsIgnoreCase("syllabusName_up") ? "syllabusName_down" : "syllabusName_up") + "')\" >Syllabus Name <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("syllabusName_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th width=10% onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "name_down" : sortType.equalsIgnoreCase("name_up") ? "name_down" : "name_up") + "')\"  >PLO Name <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("name_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th >Actie <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("description_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th></th>"
                    + "         </tr>\n"
                    + "     </thead>\n"
                    + "  <tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>\n"
                        + "      <td>" + ((LO) list.get(i)).getLoid()+ "</td>\n"
                        + "      <td>" + Custom.Common.getSyllabusNameENByID(((LO) list.get(i)).getSlbid()) + "</td>\n"
                        + "      <td> <span class='list-enq-name'>" + ((LO) list.get(i)).getCloDetail()+ "</span></td>\n"
                        + "      <td><span class='fa " + (((LO) list.get(i)).isIsActive() ? "fa-check text-success" : "fa-times text-danger") + "'></span></td>\n"
                        + "      <td>\n"
                        + "          <a href='cloDetail?loid=" + ((LO) list.get(i)).getLoid()+ "' >View</a>\n"
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
