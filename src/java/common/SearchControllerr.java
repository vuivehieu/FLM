package common;

import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Curriculum;
import model.PreRequisite;
import model.Syllabus;

@WebServlet(name = "SearchControllerr", urlPatterns = {"/search"})
public class SearchControllerr extends HttpServlet {

    private final static List<Syllabus> listSyllabus = new DAO().getAllSyllabus();
    private final static List<Curriculum> listCurriculum = new DAO().getAllCurriculum();
    private final static List<PreRequisite> listPreReq = new DAO().getAllPreRequisite();

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
            out.println("<title>Servlet SearchControllerr</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchControllerr at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("type");
        String key = request.getParameter("keysearch");
        String filter = request.getParameter("filter");
        String url = "";

        url = (type == null
                ? "gui/common/error" : type.equalsIgnoreCase("curriculum")
                ? "gui/common/curriculum/curriculum.jsp" : type.equalsIgnoreCase("syllabus")
                ? "gui/common/subject/allSyllabus.jsp" : type.equalsIgnoreCase("preRequisite")
                ? "gui/common/preRequisite/preRequisite.jsp" : type.equalsIgnoreCase("corollary")
                ? "gui/common/preRequisite/corollary.jsp" : "gui/common/error");

        if (key != null) {

            List<?> list = new ArrayList<>();
            List<?> listByPage = new ArrayList<>();

            switch (type) {
                case "curriculum":
                    list = searchCurriculum(key, filter);
                    break;
                case "syllabus":
                    list = searchSyllabus(key, filter);
                    break;
                case "preRequisite":
                    list = searchPreRequisite(key, filter);
                    break;
                case "corollary":
                    list = searchCorollary(key, filter);
                    break;
            }

            String xpage = request.getParameter("page");

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
        }
        request.setAttribute("type", type);

        request.getRequestDispatcher(url).forward(request, response);

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
        String type = request.getParameter("type");
        String key = request.getParameter("keysearch");
        String filter = request.getParameter("filter");
        String page = request.getParameter("page");

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(getResponse(type, key, filter, page));

    }

    private String getResponse(String type, String key,String filter, String xpage) {
        List<?> list = new ArrayList<>();
        List<?> listByPage = new ArrayList<>();

        switch (type) {
            case "curriculum":
                list = searchCurriculum(key, filter);
                break;
            case "syllabus":
                list = searchSyllabus(key, filter);
                break;
            case "preRequisite":
                list = searchPreRequisite(key, filter);
                break;
            case "corollary":
                list = searchCorollary(key, filter);
                break;
        }

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

        switch (type) {
            case "curriculum":
                return getResponseCurriculum(listByPage, key, type,filter, size, page, numberOfPage, start, startPage, endPage);
            case "syllabus":
                return getResponseSyllabus(listByPage, key, type, size, page, numberOfPage, startPage, endPage);
            case "preRequisite":
                return getResponsePreRequisite(list, key, size);
            case "corollary":
                return getResponseCorollary(list, key, size);
        }
        return "";

    }

    private String getResponseCurriculum(List<?> list, String key, String type,String filter, int size, int page, int numberOfPage, int start, int startPage, int endPage) {
        String response = "";
        if (list.isEmpty()) {
            response += "<div class='row'><h5 class='text-center' style='color: red'>Not Found</h3></div>";
        } else {
            response = "<div class='row'>"
                    + "<h5 style='margin-bottom: 2rem;'>" + size + " Curriculum(s) found</h5>"
                    + "<table class=\"table align-middle mb-0 bg-white\">"
                    + "     <thead class=\"bg-dark\">"
                    + "         <tr style=\" background-color: rgb(185, 182, 182); \">"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">No</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">Curriculum Code</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">Name</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">Description</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">Decision No MM/dd/yyyy</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">Total Credit</th>"
                    + "         </tr>"
                    + "     </thead>"
                    + "<tbody>";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>"
                        + "     <td style=\" vertical-align: middle;\">" + (start + i + 1) + "</td>"
                        + "     <td style=\" vertical-align: middle;\">" + ((Curriculum) list.get(i)).getCurCode() + "</td>"
                        + "     <td style=\" vertical-align: middle;\"><a href='curriculumDetails?curid=" + ((Curriculum) list.get(i)).getCurid() + "'>"
                        + "             <p class=\"fw-normal mb-1 fw-bold mb-1\">" + ((Curriculum) list.get(i)).getCurName_EN() + "</p>"
                        + "             <p class=\"fw-normal mb-1 text-muted mb-0\">" + ((Curriculum) list.get(i)).getCurName_VI() + "</p>"   
                        + "         </a>"
                        + "     </td>"
                        + "     <td class='descripCur'>" + ((Curriculum) list.get(i)).getDescription() + "</td>"
                        + "     <td style=\" vertical-align: middle; text-align: center; \"><a href='decision?decisionNo=" + ((Curriculum) list.get(i)).getDecision().getDecisionNo() + "'>"
                        + "             <p class=\"fw-normal mb-1 fw-bold mb-1 \">" + ((Curriculum) list.get(i)).getDecision().getDecisionNo() + "</p>"
                        + "             <p class=\"fw-normal mb-1 text-muted mb-0 \">" + ((Curriculum) list.get(i)).getDecision().getApprovedDate() + "</p>" 
                        + "         </a>"
                        + "     </td>"
                        + "     <td style=\" vertical-align: middle; text-align: center; \">" + ((Curriculum) list.get(i)).getTotalCredit() + "</td>"
                        + "</tr>";
            }

            response += "</tbody></table></div>"
                    + "<div class='pg-pagina'>\n"
                    + "                        <ul class='pagination' style=\"display: flex; justify-content: center;\">\n"
                    + "                            <li class='page-item " + (page == 1 ? "disabled" : "waves-effect") + "'>\n"
                    + "                                <a onclick=\"changePage('" + (page - 1) + "')\" class=\"page-link\" style=\"cursor: pointer; \"><span aria-hidden=\"true\">&laquo;</span></a>\n"
                    + "                            </li>\n";
            
            if (startPage > 1) {
                response += "<li class='waves-effect'><a style=\"cursor: pointer; \" onclick=\"changePage('" + (page - 1) + "') \">...</a></li>\n";
            }
            
            for (int i = startPage; i <= endPage; i++) {
                response += "<li class='page-item " + (page == i ? "active" : "waves-effect") + "'>\n"
                        + "     <a style=\"cursor: pointer; \" class=\"page-link\" onclick=\"changePage('" + (i) + "')\">" + i + "<span class=\"sr-only\">(current)</span></a>\n"
                        + "</li>\n";
            }
            if (endPage < numberOfPage) {
                response += "<li class='waves-effect'><a style=\"cursor: pointer; \" onclick=\"changePage('" + (page + 1) + "')\">...</a></li>\n";
            }
            response += "<li class='page-item " + (page == numberOfPage ? "disabled" : "waves-effect") + "'>\n"
                    + "     <a style=\"cursor: pointer; \" class=\"page-link\" onclick=\"changePage('" + (page + 1) + "')\" style=\" margin-left: 1rem; \"><span aria-hidden=\"true\">&raquo;</span></a>\n"
                    + "</li>\n"
                    + "</ul>\n"
                    + "</div>";
        }
        return response;
    }

    private String getResponseSyllabus(List<?> list, String key, String type, int size, int page, int numberOfPage, int startPage, int endPage) {

        String response = "";
        if (list.isEmpty()) {
            response += "<div class='row'><h3 class='text-center' style='color: red'>Not Found</h3></div>";
        } else {
            response = "<div class='row'><h5 style='margin-bottom: 2rem;'>" + size + " Syllabus(es) found</h5>"
                    + "<table class=\"table align-middle mb-0 bg-white\">"
                    + "<thead class=\"bg-dark\">"
                    + "<tr style=\" background-color: rgb(185, 182, 182); \">"
                    + "<th style=\"  text-align: center; vertical-align: middle; \">Syllabus ID</th>"
                    + "<th style=\"  text-align: center; vertical-align: middle;\">Subject Code</th>"
                    + "<th style=\"  text-align: center; vertical-align: middle;\">Subject Name</th>"
                    + "<th style=\"  text-align: center; vertical-align: middle;\">Syllabus Name</th>"
                    + "<th style=\"  text-align: center; vertical-align: middle;\">IsActive</th>"
                    + "<th style=\"  text-align: center; vertical-align: middle;\">IsApproved</th"
                    + "><th style=\"  text-align: center; vertical-align: middle;\">DecisionNo MM/dd/yyyy</th>"
                    + "</tr></thead>"
                    + "<tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>"
                        + "<td style=\" text-align: center; vertical-align: middle; \">" + ((Syllabus) list.get(i)).getSlbid() + "</td>"
                        + "<td style=\" text-align: center; vertical-align: middle; \">" + ((Syllabus) list.get(i)).getSubjectCode() + "</td>"
                        + "<td style=\" text-align: center; vertical-align: middle; \">" + new DAO().getSubjectNameENBySubjectCode(((Syllabus) list.get(i)).getSubjectCode()) + "</td>"
                        + "<td style=\" text-align: center; vertical-align: middle; \"><a href='syllabusDetails?subjectCode=" + ((Syllabus) list.get(i)).getSubjectCode() + "&slbid=" + ((Syllabus) list.get(i)).getSlbid() + "'>"
                        + "     <span class='list-enq-name'>" + ((Syllabus) list.get(i)).getSlbName_EN() + "</span>\n"
                        + "     <span class='list-enq-city'>" + ((Syllabus) list.get(i)).getSlbName_VI() + "</span>"
                        + "     </a>"
                        + "</td>"
                        + "<td class='text-center' style=\" text-align: center; vertical-align: middle; \"><i class='fa " + (((Syllabus) list.get(i)).isIsActive() ? "fa-check text-success" : "fa-times text-danger") + "'></i></td>"
                        + "<td class='text-center' style=\" text-align: center; vertical-align: middle; \"><i class='fa " + (((Syllabus) list.get(i)).isIsApproved() ? "fa-check text-success" : "fa-times text-danger") + "'></i></td>"
                        + "<td style=\" text-align: center; vertical-align: middle; \"><a href='decision?decisionNo=" + ((Syllabus) list.get(i)).getDecision().getDecisionNo() + "'>"
                        + "     <span class='list-enq-name'>" + ((Syllabus) list.get(i)).getDecision().getDecisionNo() + "</span>\n"
                        + "     <span class='list-enq-city'>" + Custom.Common.getDateFormat("MM/dd/yyyy", ((Syllabus) list.get(i)).getDecision().getApprovedDate()) + "</span>\n"
                        + "     </a>"
                        + "</td>"
                        + "</tr>";
            }

            response += "</tbody></table></div>"
                    + "<div class='pg-pagina'>\n"
                    + "                        <ul class='pagination' style=\"display: flex; justify-content: center;\">\n"
                    + "                            <li class='page-item " + (page == 1 ? "disabled" : "waves-effect") + "'>\n"
                    + "                                <a style=\"cursor: pointer; \" class=\"page-link\" onclick=\"changePage('" + (page - 1) + "')\" style=\" margin-right: 1rem;\"><span aria-hidden=\"true\">&laquo;</span></a>\n"
                    + "                            </li>\n";
            if (startPage > 1) {
                response += "<li class='waves-effect'><a style=\"cursor: pointer; \" onclick=\"changePage('" + (page - 1) + "')\">...</a></li>\n";
            }
            for (int i = startPage; i <= endPage; i++) {
                response += "<li class='page-item " + (page == i ? "active" : "waves-effect") + "'>\n"
                        + "     <a style=\"cursor: pointer; \" class=\"page-link\" onclick=\"changePage('" + (i) + "')\">" + i + "</a>\n"
                        + "</li>\n";
            }
            if (endPage < numberOfPage) {
                response += "<li class='waves-effect'><a style=\"cursor: pointer; \" onclick=\"changePage('" + (page + 1) + "')\">...</a></li>\n";
            }
            response += "<li class='page-item " + (page == numberOfPage ? "disabled" : "waves-effect") + "'>\n"
                    + "     <a style=\"cursor: pointer; \" class=\"page-link\" onclick=\"changePage('" + (page + 1) + "')\" style=\" margin-left: 1rem; \"><span aria-hidden=\"true\">&raquo;</span></a>\n"
                    + "</li>\n"
                    + "</ul>\n"
                    + "</div>";
        }

        return response;
    }

    private String getResponsePreRequisite(List<?> list, String key, int size) {
        String response = "";
        if (list.isEmpty()) {
            response = "<div class='row text-center' style='color: red'>"
                    + "<h3>Subject code <span style='color: black; font-size: unset; font-weight: bold'>" + key + "</span> does not exist or has no syllabus</h3>"
                    + "</div>";
        } else {
            response = "<div class='row'>"
                    + "<h5 style='margin-bottom: 2rem;'> All " + size + " Syllabus(es)</h5>"
                    + "<table class=\"table align-middle mb-0 bg-white\">"
                    + "     <thead class=\"bg-dark\">"
                    + "         <tr style=\" background-color: rgb(185, 182, 182); \">"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">No</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">Syllabus ID</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">Subject Name</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">Syllabus Name</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">DecisionNo MM/dd/yyyy</th>"
                    + "             <th style=\"  vertical-align: middle; text-align: center; \">All subjects need to learn before</th>"
                    + "         </tr>"
                    + "     </thead>"
                    + "<tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>"
                        + "     <td style=\"text-align: center; \">" + (i + 1) + "</td>"
                        + "     <td style=\" text-align: center;\">" + ((Syllabus) list.get(i)).getSlbid() + "</td>"
                        + "     <td style=\" text-align: center;\">" + ((Syllabus) list.get(i)).getSubjectCode() + "</td>"
                        + "     <td style=\"text-align: center; \"><a href='syllabusDetails?subjectCode=" + ((Syllabus) list.get(i)).getSubjectCode() + "&slbid=" + ((Syllabus) list.get(i)).getSlbid() + "'>"
//                        + "             <span class='list-enq-name'>" + ((Syllabus) list.get(i)).getSlbName_EN() + "</span>"
//                        + "             <span class='list-enq-city'>" + ((Syllabus) list.get(i)).getSlbName_VI() + "</span>"
                         + "             <p class=\"fw-normal mb-1 fw-bold mb-1\">" + ((Syllabus) list.get(i)).getSlbName_EN() + "</p>"
                        + "             <p class=\"fw-normal mb-1 text-muted mb-0\">" + ((Syllabus) list.get(i)).getSlbName_VI() + "</p>"  
                        + "         </a>"
                        + "     </td>"
                        + "     <td style=\" text-align: center; \"><a href='decision?decisionNo=" + ((Syllabus) list.get(i)).getDecision().getDecisionNo() + "'>"
                        + "             <p class=\"fw-normal mb-1 fw-bold mb-1 \">" + ((Syllabus) list.get(i)).getDecision().getDecisionNo() + "</p>"
                        + "             <p class=\"fw-normal mb-1 text-muted mb-0 \">" + Custom.Common.getDateFormat("MM/dd/yyyy", ((Syllabus) list.get(i)).getDecision().getApprovedDate()) + "</p>" 
                        + "         </a>"
                        + "     </td>" + getPreRequisite(list, ((Syllabus) list.get(i)).getSubjectCode()) + ""
                        + "</tr>";
            }
            response += "</tbody></table></div>";
        }
        return response;
    }

    private String getResponseCorollary(List<?> list, String key, int size) {
        String response = "";
        if (list.isEmpty()) {
            response = "<div class='row text-center' style='color: red'>"
                    + "     <h3>Subject code <span style='color: black; font-size: unset; font-weight: bold'>" + key + "</span> does not exist or has no syllabus</h3>"
                    + "</div>";
        } else {
            response = "<div class='row'>"
                    + "     <h5 style='margin-bottom: 2rem;'> All " + size + " Syllabus(es)</h5>"
                    + "<table class=\"table align-middle mb-0 bg-white\">"
                    + "     <thead class=\"bg-dark\">"
                    + "             <tr style=\" background-color: rgb(185, 182, 182); \">"
                    + "                 <th style=\"  vertical-align: middle; text-align: center; \">No</th>"
                    + "                 <th style=\"  vertical-align: middle; text-align: center; \">Syllabus ID</th>"
                    + "                 <th style=\"  vertical-align: middle; text-align: center; \">Subject Code</th>"
                    + "                 <th style=\"  vertical-align: middle; text-align: center; \">Syllabus Name</th>"
                    + "                 <th style=\"  vertical-align: middle; text-align: center; \">DecisionNo MM/dd/yyyy</th>"
                    + "                 <th style=\"  vertical-align: middle; text-align: center; \">All subjects learn after</th>"
                    + "             </tr>"
                    + "         </thead>"
                    + "         <tbody>\n";
            for (int i = 0; i < list.size(); i++) {
                response += "<tr>"
                        + "<td style=\" text-align: center; vertical-align: middle; \">" + (i + 1) + "</td>"
                        + "<td style=\" text-align: center; vertical-align: middle; \">" + ((Syllabus) list.get(i)).getSlbid() + "</td>"
                        + "<td style=\" text-align: center; vertical-align: middle; \">" + ((Syllabus) list.get(i)).getSubjectCode() + "</td>"
                        + "     <td style=\" text-align: center; vertical-align: middle; \"><a href='syllabusDetails?subjectCode=" + ((Syllabus) list.get(i)).getSubjectCode() + "&slbid=" + ((Syllabus) list.get(i)).getSlbid() + "'>"
                        + "             <span class='list-enq-name'>" + ((Syllabus) list.get(i)).getSlbName_EN() + " " + ((Syllabus) list.get(i)).getSlbName_VI() + "</span>"
                        + "             <span class='list-enq-city'>" + ((Syllabus) list.get(i)).getSlbName_VI() + "</span>"
                        + "         </a>"
                        + "         <a href='syllabusDetails?subjectCode=" + ((Syllabus) list.get(i)).getSubjectCode() + "&slbid=" + ((Syllabus) list.get(i)).getSlbid() + "'></a>"
                        + "     </td>"
                        + "     <td style=\" text-align: center; vertical-align: middle; \"><a href='decision?decisionNo=" + ((Syllabus) list.get(i)).getDecision().getDecisionNo() + "'>"
                        + "             <span class='list-enq-name'>" + ((Syllabus) list.get(i)).getDecision().getDecisionNo() + "</span>"
                        + "             <span class='list-enq-city'>" + Custom.Common.getDateFormat("MM/dd/yyyy", ((Syllabus) list.get(i)).getDecision().getApprovedDate()) + "</span>"
                        + "         </a>"
                        + "     </td>"
                        + getCorollary(((Syllabus) list.get(i)).getSubjectCode())
                        + "</tr>";
            }
            response += "</tbody></table></div>";
        }
        return response;
    }

    private List<Curriculum> searchCurriculum(String key, String filter) {
        List<Curriculum> result = new ArrayList<>();
        for (Curriculum item : listCurriculum) {
//            if (item.getCurCode().toLowerCase().contains(key.toLowerCase())
//                    || item.getCurName_EN().toLowerCase().contains(key.toLowerCase())
//                    || item.getCurName_VI().toLowerCase().contains(key.toLowerCase())) {
//                result.add(item);
//            }

            if(filter.toLowerCase().contains("curcode")){
                if (item.getCurCode().toLowerCase().contains(key.toLowerCase())){
                    result.add(item);
                }
            }
            
            if(filter.toLowerCase().contains("curname")){
                if (item.getCurName_EN().toLowerCase().contains(key.toLowerCase())
                   || item.getCurName_VI().toLowerCase().contains(key.toLowerCase())){
                    result.add(item);
                }
            }
        }
        return result;
    }

    private List<Syllabus> searchSyllabus(String key, String filter) {
        List<Syllabus> result = new ArrayList<>();
        for (Syllabus item : listSyllabus) {
//            if (item.getSubjectCode().toLowerCase().contains(key.toLowerCase())
//                    || item.getSlbName_EN().toLowerCase().contains(key.toLowerCase())
//                    || item.getSlbName_VI().toLowerCase().contains(key.toLowerCase())) {
//                result.add(item);
//            }
            
            if(filter.toLowerCase().contains("subjectcode")){
                if (item.getSubjectCode().toLowerCase().contains(key.toLowerCase())){
                    result.add(item);
                }
            }
            
            if(filter.toLowerCase().contains("syllabusname")){
                if (item.getSlbName_EN().toLowerCase().contains(key.toLowerCase())
                    || item.getSlbName_VI().toLowerCase().contains(key.toLowerCase())){
                    result.add(item);
                }
            }
            
        }
        return result;
    }

    private List<Syllabus> searchPreRequisite(String key, String filter) {
        List<Syllabus> result = new ArrayList<>();
        for (Syllabus item : listSyllabus) {
//            if (item.getSubjectCode().toLowerCase().equalsIgnoreCase(key.toLowerCase())
//                    || item.getSlbName_EN().toLowerCase().equalsIgnoreCase(key.toLowerCase())
//                    || item.getSlbName_VI().toLowerCase().equalsIgnoreCase(key.toLowerCase())) {
//                result.add(item);
//            }
            
            if(filter.toLowerCase().contains("subjectname")){
                if (item.getSubjectCode().toLowerCase().contains(key.toLowerCase())){
                    result.add(item);
                }
            }
            
            if(filter.toLowerCase().contains("syllabusname")){
                if (item.getSlbName_EN().toLowerCase().contains(key.toLowerCase())
                    || item.getSlbName_VI().toLowerCase().contains(key.toLowerCase())){
                    result.add(item);
                }
            }
        }
        return result;
    }

    private List<Syllabus> searchCorollary(String key,String filter) {
        List<Syllabus> result = new ArrayList<>();
        for (Syllabus item : listSyllabus) {
//            if (item.getSubjectCode().toLowerCase().equalsIgnoreCase(key.toLowerCase())
//                    || item.getSlbName_EN().toLowerCase().equalsIgnoreCase(key.toLowerCase())
//                    || item.getSlbName_VI().toLowerCase().equalsIgnoreCase(key.toLowerCase())) {
//                result.add(item);
//            }
            
            if(filter.toLowerCase().contains("subjectcode")){
                if (item.getSubjectCode().toLowerCase().contains(key.toLowerCase())){
                    result.add(item);
                }
            }
            
            if(filter.toLowerCase().contains("syllabusname")){
                if (item.getSlbName_EN().toLowerCase().contains(key.toLowerCase())
                    || item.getSlbName_VI().toLowerCase().contains(key.toLowerCase())){
                    result.add(item);
                }
            }
        }
        return result;
    }

    private String getCorollary(String subjectCode) {
        DAO dao = new DAO();
        String result = "";
        List<PreRequisite> list = add(subjectCode);
        if (list.isEmpty()) {
            result += "<td><span>This subject has no corollary </span><ul>";
        } else {
            result += "<td><span>This subject is the pre-requisite of: </span><ul>";
            for (PreRequisite item : add(subjectCode)) {
                result += "<li>+ " + subjectCode + ", " + dao.getSubjectCodeBySlbid(item.getSlbid()) + "</li>";
            }
            result += "</ul></td>";
        }

        return result;
    }

    private static List<PreRequisite> add(String subjectCode) {
        List<PreRequisite> list = new ArrayList<>();

        for (PreRequisite item : listPreReq) {

            if (item.getSubjectCode() != null && item.getSubjectCode().equalsIgnoreCase(subjectCode)) {
                list.add(item);
            }
        }
        return list;
    }

    private List<PreRequisite> addListPreRequisite(List<?> list, List<PreRequisite> preRequisite) {
        List<Syllabus> syllabus;

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < ((Syllabus) list.get(i)).getPreReq().size(); j++) {
                preRequisite.add(((Syllabus) list.get(i)).getPreReq().get(j));
                syllabus = getSyllabusBySubjectCode(((Syllabus) list.get(i)).getPreReq().get(j).getSubjectCode());
                preRequisite = addListPreRequisite(syllabus, preRequisite);
            }
        }

        return preRequisite;
    }

    private List<Syllabus> getSyllabusBySubjectCode(String subjectCode) {
        List<Syllabus> result = new ArrayList<>();

        for (Syllabus item : listSyllabus) {
            if (item.getSubjectCode().equalsIgnoreCase(subjectCode)) {
                result.add(item);
            }
        }
        return result;
    }

    private String getPreRequisite(List<?> list, String subjectCode) {
        DAO dao = new DAO();
        List<PreRequisite> preReq = addListPreRequisite(list, new ArrayList<>());

        String preRequisite = "<td>" + subjectCode + ": ";
        for (int i = 0; i < preReq.size(); i++) {
            if (preReq.get(i).getSubjectCode() == null && preReq.size() == 1) {
                preRequisite += "( No pre-Requisite)";
            } else {

                if (i == 0 && preReq.get(i).getSubjectCode() != null) {
                    preRequisite += preReq.get(i).getSubjectCode();
                } else if (i != 0 && preReq.get(i).getSubjectCode() != null) {
                    preRequisite += ", " + preReq.get(i).getSubjectCode();
                }

            }

        }
        preRequisite += "<ul>";
        for (int i = 0; i < preReq.size(); i++) {
            String code = dao.getSubjectCodeBySlbid(preReq.get(i).getSlbid());
            if (!code.equalsIgnoreCase(subjectCode)) {
                preRequisite += "<li> + " + code + ": " + ("".equals(preReq.get(i).getPreReqCode()) ? "( No pre-Requisite)" : preReq.get(i).getPreReqCode()) + "</li>";
            }
        }

        return preRequisite + "</ul></td>";
    }

}
