package dao;

import javax.sql.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.Seller;

public class SellerDAO implements DAO<Seller> {
    private DataSource dataSource;
    public SellerDAO (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Seller get(int id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement
                        ("SELECT * FROM seller WHERE seller_id = ?")
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Seller obj = new Seller();
                obj.setId(rs.getInt("seller_id"));
                obj.setSellerName(rs.getString("seller_name"));
                obj.setPhone(rs.getString("phone"));
                obj.setAddress(rs.getString("address"));
                obj.setGender(rs.getString("gender"));

                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Seller> list() {
        List<Seller> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("SELECT * FROM seller ORDER BY seller_name");
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                Seller obj = new Seller();
                obj.setId(rs.getInt("seller_id"));
                obj.setSellerName(rs.getString("seller_name"));
                obj.setPhone(rs.getString("phone"));
                obj.setAddress(rs.getString("address"));
                obj.setGender(rs.getString("gender"));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int save(Seller obj) throws SQLException {
        if(obj.getId() == 0)
            return insert(obj);
        else
            return update(obj);
    }
    private int insert(Seller obj) throws SQLException {
        int id = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("INSERT INTO seller (seller_name, phone, address, gender) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, obj.getSellerName());
            preparedStatement.setString(2, obj.getPhone());
            preparedStatement.setString(3, obj.getAddress());
            preparedStatement.setString(4, obj.getGender());
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

    private int update(Seller obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("UPDATE seller SET seller_name = ?, phone = ?, address = ?, gender = ? WHERE seller_id = ?")
        ) {
            preparedStatement.setString(1, obj.getSellerName());
            preparedStatement.setString(2, obj.getPhone());
            preparedStatement.setString(3, obj.getAddress());
            preparedStatement.setString(4, obj.getGender());
            preparedStatement.setInt(5, obj.getId());
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
                        ("DELETE FROM seller WHERE seller_id = ?");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

