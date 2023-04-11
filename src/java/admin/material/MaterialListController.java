/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.material;

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
import model.Material;

@WebServlet(name = "MaterialListController", urlPatterns = {"/materialList"})
public class MaterialListController extends HttpServlet {

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
            out.println("<title>Servlet MaterialListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MaterialListController at " + request.getContextPath() + "</h1>");
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

        List<Material> list = dao.getMaterialByAccountID(a.getAccountID());

        String key = request.getParameter("keySearch");

        if (key != null && !key.isEmpty()) {
            list = dao.getMaterialByAccountIDAndKey(a.getAccountID(), key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (Material o1, Material o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getMid() > o2.getMid()) {
                                return 1;
                            }
                            return -1;
                        }

                        if (o1.getMid() > o2.getMid()) {
                            return -1;
                        }
                        return 1;
                    });
                    break;
                    case "publisher":
                    Collections.sort(list, (Material o1, Material o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getPublisher().compareTo(o2.getPublisher()) > 0) {
                                return 1;
                            }
                            return -1;
                        } else {
                            if (o1.getPublisher().compareTo(o2.getPublisher()) > 0) {
                                return -1;
                            }
                            return 1;
                        }
                    });
                    break;

                case "author":
                    Collections.sort(list, (Material o1, Material o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getAuthor().compareToIgnoreCase(o2.getAuthor()) > 0) {
                                return 1;
                            }
                            return -1;
                        }
                        if (o1.getAuthor().compareToIgnoreCase(o2.getAuthor()) > 0) {
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

        request.getRequestDispatcher("gui/admin/material/list.jsp").forward(request, response);
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

        List<Material> list = dao.getMaterialByAccountID(a.getAccountID());

        String key = request.getParameter("keySearch");

        if (key != null && !key.isEmpty()) {
            list = dao.getMaterialByAccountIDAndKey(a.getAccountID(), key);
        }

        String sortType = request.getParameter("sort");

        sortType = (sortType != null ? sortType : "");
        if (sortType != null && !sortType.isEmpty()) {
            String[] sort = sortType.split("_");
            switch (sort[0]) {
                case "id":
                    Collections.sort(list, (Material o1, Material o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getMid() > o2.getMid()) {
                                return 1;
                            }
                            return -1;
                        } else {
                            if (o1.getMid() > o2.getMid()) {
                                return -1;
                            }
                            return 1;
                        }

                    });
                    break;
                case "publisher":
                    Collections.sort(list, (Material o1, Material o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getPublisher().compareTo(o2.getPublisher()) > 0) {
                                return 1;
                            }
                            return -1;
                        } else {
                            if (o1.getPublisher().compareTo(o2.getPublisher()) > 0) {
                                return -1;
                            }
                            return 1;
                        }
                    });
                    break;
                case "author":
                    Collections.sort(list, (Material o1, Material o2) -> {
                        if (sort[1].equalsIgnoreCase("up")) {
                            if (o1.getAuthor().compareToIgnoreCase(o2.getAuthor()) > 0) {
                                return 1;
                            }
                            return -1;
                        } else {
                            if (o1.getAuthor().compareToIgnoreCase(o2.getAuthor()) > 0) {
                                return -1;
                            }
                            return 1;
                        }

                    });
                    break;

//                default:
//                    throw new AssertionError();
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
                    + "             <th width='10%' onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "id_down" : sortType.equalsIgnoreCase("id_up") ? "id_down" : "id_up") + "')\"  >MID <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("id_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "author_down" : sortType.equalsIgnoreCase("author_up") ? "author_down" : "author_up") + "')\"  >Author <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("author_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th onclick=\"sortByType('" + (sortType.equalsIgnoreCase("") ? "publisher_down" : sortType.equalsIgnoreCase("publisher_up") ? "publisher_down" : "publisher_up") + "')\">Publisher <span class='fa " + (sortType.equalsIgnoreCase("") ? "fa-angle-up" : sortType.equalsIgnoreCase("publishers_up") ? "fa-angle-up" : "fa-angle-down") + "'></span></th>\n"
                    + "             <th>Active</th>\n"
                    + "             <th></th>"
                    + "         </tr>\n"
                    + "     </thead>\n"
                    + "  <tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>\n"
                        + "      <td>" + ((Material) list.get(i)).getMid() + "</td>\n"
                        + "      <td>" + ((Material) list.get(i)).getAuthor() + "</td>\n"
                        + "      <td>" + ((Material) list.get(i)).getPublisher() + "</td>\n"
                        + "      <td><a href='materialDetail?mid=" + ((Material) list.get(i)).getMid() + "'>Edit</a></td>\n"
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
