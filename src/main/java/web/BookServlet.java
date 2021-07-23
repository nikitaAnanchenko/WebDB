package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import model.Book;
import dao.BookDAO;

@WebServlet(name = "BookServlet", urlPatterns = {"/book","/book/edit"})
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDAO = (BookDAO) this.getServletContext().getAttribute("bookDAO");
        if (request.getParameter("id") == null) {
            List<Book> books = bookDAO.list();
            request.setAttribute("books", books);
            this.getServletContext().getRequestDispatcher("/Books.jsp").forward(request, response);
            //response.sendError(404);
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookDAO.get(id);
        if (book == null) {
            response.sendError(404);
            return;
        }
        request.setAttribute("book", book);
        boolean edit = request.getRequestURI().endsWith("edit");
        String route = edit ? "/bookEdit.jsp" : "/book.jsp";
        this.getServletContext().getRequestDispatcher(route).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDAO = (BookDAO) this.getServletContext().getAttribute("bookDAO");
        Book book;
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            book = bookDAO.get(id);
        } else {
            book = new Book();
        }
        if (book == null) {
            response.sendError(404);
            return;
        }
        String bookName = request.getParameter("bookName");
        if (bookName != null) {
            book.setBookName(bookName);
        }
        String author = request.getParameter("author");
        if (author != null) {
            book.setAuthor(author);
        }
        String price = request.getParameter("price");
        if (price != null) {
            book.setPrice(Integer.parseInt(price));
        }
        String releaseDate = request.getParameter("releaseDate");
        if (releaseDate  != null) {
            book.setDate(Date.valueOf(releaseDate));
        }

        try {
            bookDAO.save(book);
        } catch(Exception ex) {
            log("Failed to save", ex);
            response.sendError(500, "Failed to save " + ex.getMessage());
        }
        response.sendRedirect( request.getContextPath() + "/book");
    }
}
