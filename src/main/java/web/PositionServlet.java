package web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Positions;
import dao.PositionsDAO;

@WebServlet(name = "PositionServlet", urlPatterns = {"/position","/position/edit"})
public class PositionServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PositionsDAO positionDAO = (PositionsDAO) this.getServletContext().getAttribute("positionDAO");
        if (request.getParameter("id") == null) {
            List<Positions> positions = positionDAO.list();
            request.setAttribute("positions", positions);
            this.getServletContext().getRequestDispatcher("/Positions.jsp").forward(request, response);
            response.sendError(404);
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        Positions position = positionDAO.get(id);
        if (position == null) {
            response.sendError(404);
            return;
        }
        request.setAttribute("position", position);
        boolean edit = request.getRequestURI().endsWith("edit");
        String route = edit ? "/positionEdit.jsp" : "/position.jsp";
        this.getServletContext().getRequestDispatcher(route).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PositionsDAO PositionDAO = (PositionsDAO) this.getServletContext().getAttribute("PositionDAO");
        Positions position;
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            position = PositionDAO.get(id);
        } else {
            position = new Positions();
        }
        if (position == null) {
            response.sendError(404);
            return;
        }
        String namePosition = request.getParameter("namePosition");
        if (namePosition != null) {
            position.setNamePosition(namePosition);
        }
        String salary = request.getParameter("salary");
        if (salary != null) {
            position.setSalary(Integer.parseInt(salary));
        }

        try {
            PositionDAO.save(position);
        } catch(Exception ex) {
            log("Failed to save", ex);
            response.sendError(500, "Failed to save " + ex.getMessage());
        }
        response.sendRedirect( request.getContextPath() + "/position");
    }
}

