package web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Seller;
import dao.SellerDAO;

@WebServlet(name = "SellerServlet", urlPatterns = {"/seller","/seller/edit"})
public class SellerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        SellerDAO sellerDAO = (SellerDAO) this.getServletContext().getAttribute("sellerDAO");
        if (request.getParameter("id") == null) {
            List<Seller> sellers = sellerDAO.list();
            request.setAttribute("sellers", sellers);
            this.getServletContext().getRequestDispatcher("/Sellers.jsp").forward(request, response);
            response.sendError(404);
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        Seller seller = sellerDAO.get(id);
        if (seller == null) {
            response.sendError(404);
            return;
        }
        request.setAttribute("seller", seller);
        boolean edit = request.getRequestURI().endsWith("edit");
        String route = edit ? "/sellerEdit.jsp" : "/seller.jsp";
        this.getServletContext().getRequestDispatcher(route).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        SellerDAO sellerDAO = (SellerDAO) this.getServletContext().getAttribute("sellerDAO");
        Seller seller;
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            seller = sellerDAO.get(id);
        } else {
            seller = new Seller();
        }
        if (seller == null) {
            response.sendError(404);
            return;
        }
        String sellerName = request.getParameter("sellerName");
        if (sellerName != null) {
            seller.setSellerName(sellerName);
        }
        String phone = request.getParameter("phone");
        if (phone != null) {
            seller.setPhone(phone);
        }
        String address = request.getParameter("address");
        if (address != null) {
            seller.setAddress(address);
        }
        String gender = request.getParameter("gender");
        if (gender != null) {
            seller.setGender(gender);
        }

        try {
            sellerDAO.save(seller);
        } catch(Exception ex) {
            log("Failed to save", ex);
            response.sendError(500, "Failed to save " + ex.getMessage());
        }
        response.sendRedirect( request.getContextPath() + "/seller");
    }
}
