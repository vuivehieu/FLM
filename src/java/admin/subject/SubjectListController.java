/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.subject;

import DAL.CurriculumDAO;
import DAL.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author PCM
 */
@WebServlet(name="SubjectListController", urlPatterns={"/subjectList"})
public class SubjectListController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubjectDAO subjectDAO = new SubjectDAO();
        req.setAttribute("subjects", subjectDAO.findAllSubject());
        req.getRequestDispatcher("gui/admin/subject/list.jsp").forward(req, resp);
    }
}
