/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.prerequisite;


import DAL.PrerequisiteDAO;
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
@WebServlet(name="PreRequisiteListController", urlPatterns={"/preRequisiteList"})
public class PreRequisiteListController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrerequisiteDAO prerequisiteDAO = new PrerequisiteDAO();
        req.setAttribute("preRequisites", prerequisiteDAO.findAllPreRequisites());
        req.getRequestDispatcher("gui/admin/prerequisite/list.jsp").forward(req, resp);
    }
}
