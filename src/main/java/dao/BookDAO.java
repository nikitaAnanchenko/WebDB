package dao;


import javax.sql.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.Book;

public class BookDAO implements DAO<Book>{
    private DataSource dataSource;
    public BookDAO (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Book get(int id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement
                        ("SELECT * FROM book WHERE book_id = ?")
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Book obj = new Book();
                obj.setId(rs.getInt("book_id"));
                obj.setBookName(rs.getString("book_name"));
                obj.setAuthor(rs.getString("author"));
                obj.setPrice(rs.getInt("price"));
                obj.setDate(rs.getDate("release_date"));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Book> list() {
        List<Book> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("SELECT * FROM book ORDER BY book_name");
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                Book obj = new Book();
                obj.setId(rs.getInt("book_id"));
                obj.setBookName(rs.getString("book_name"));
                obj.setAuthor(rs.getString("author"));
                obj.setPrice(rs.getInt("price"));
                obj.setDate(rs.getDate("release_date"));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public int save(Book obj) throws SQLException {
        if(obj.getId() == 0)
            return insert(obj);
        else
            return update(obj);
    }
    private int insert(Book obj) throws SQLException {
        int id = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("INSERT INTO book (book_name, author, price, release_date) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, obj.getBookName());
            preparedStatement.setString(2, obj.getAuthor());
            preparedStatement.setInt(3, obj.getPrice());
            preparedStatement.setDate(4,obj.getDate());
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if (res !=null && res.next()) {
                id = res.getInt(1);
                res.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private int update(Book obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("UPDATE book SET book_name = ? author = ?, price = ?, release_date = ? WHERE book_id = ?")
        ) {
            preparedStatement.setString(1, obj.getBookName());
            preparedStatement.setString(2, obj.getAuthor());
            preparedStatement.setInt(3, obj.getPrice());
            preparedStatement.setDate(4,obj.getDate());
			preparedStatement.setInt(5, obj.getId())
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj.getId();
    }
    @Override
    public void delete(int id) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("DELETE FROM book WHERE book_id = ?");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
