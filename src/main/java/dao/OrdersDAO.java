package dao;

import model.Seller;
import model.Orders;
import model.Book;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDAO extends AbstractDAO<Orders>{
    public static final String SELECT_BOOK_FOR_ORDER = "SELECT book_name, price FROM book JOIN orders USING(book_id) WHERE order_id = ?";
    public static final String SELECT_SELLER_FOR_ORDER = "SELECT seller_name, gender FROM seller JOIN orders USING(seller_id) WHERE order_id = ?";
    private BookDAO bookDAO;
    private SellerDAO sellerDAO;

    public OrdersDAO(DataSource dataSource) {
        super(dataSource,
                "SELECT * FROM orders ORDER BY order_id",
                "SELECT * FROM orders WHERE order_id=?",
                "INSERT INTO orders (book_id, seller_id, purchase_volume, date_and_time) VALUES(?,?,?,?)",
                "UPDATE orders SET book_id=?, seller_id=?, purchase_volume=?, date_and_time=?  WHERE order_id = ?",
                "DELETE FROM orders WHERE order_id = ?");
    }

    @Override
    public Orders readObject(ResultSet rs) throws SQLException {
        Orders ord = new Orders();
        ord.setId(rs.getInt("order_id"));
        ord.setPurchaseVolume(rs.getInt("purchase_volume"));
        ord.setDate(rs.getTimestamp("date_and_time"));

        ord.setBook(bookDAO.list());
        ord.setSeller(sellerDAO.list());

        return ord;
    }

    @Override
    public int save(Orders obj) throws SQLException {
        return super.save(obj);
    }

    @Override
    protected int writeObject(Orders obj, PreparedStatement ps, int idx) throws SQLException {
        ps.setInt(idx++, obj.getPurchaseVolume());
        ps.setTimestamp(idx++, obj.getDate());
        return idx;
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }
    public BookDAO getBookDao() {
        return bookDAO;
    }
    public void setBookDao(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    public SellerDAO getSellerDao() {
        return sellerDAO;
    }
    public void setSellerDao(SellerDAO sellerDAO) {
        this.sellerDAO = sellerDAO;
    }
}

