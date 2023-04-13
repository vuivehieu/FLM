/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.curriculum;

import DAL.CurriculumDAO;
import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
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
@WebServlet(name="CurriculumListController", urlPatterns={"/curriculumList"})
public class CurriculumListController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurriculumDAO curriculumDAO = new CurriculumDAO();
        req.setAttribute("curriculums", curriculumDAO.findAllCurriculum());
        req.getRequestDispatcher("gui/admin/curriculum/list.jsp").forward(req, resp);
    }
    
}
