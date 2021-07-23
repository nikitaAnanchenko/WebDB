package dao;

import javax.sql.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.Positions;

public class PositionsDAO implements DAO<Positions>{
    private DataSource dataSource;
    public PositionsDAO (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Positions get(int id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement
                        ("SELECT * FROM positions WHERE position_id = ?")
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Positions obj = new Positions();
                obj.setId(rs.getInt("position_id"));
                obj.setNamePosition(rs.getString("name_position"));
                obj.setSalary(rs.getInt("salary"));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Positions> list() {
        List<Positions> list = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("SELECT * FROM positions ORDER BY name_position");
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                Positions obj = new Positions();
                obj.setId(rs.getInt("position_id"));
                obj.setNamePosition(rs.getString("name_position"));
                obj.setSalary(rs.getInt("salary"));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public int save(Positions obj) throws SQLException {
        if(obj.getId() == 0)
            return insert(obj);
        else
            return update(obj);
    }
    private int insert(Positions obj) throws SQLException {
        int id = -1;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("INSERT INTO positions (name_position, salary) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, obj.getNamePosition());
            preparedStatement.setInt(2, obj.getSalary());
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

    private int update(Positions obj) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("UPDATE positions SET name_position = ?, salary = ? WHERE position_id = ?")
        ) {
            preparedStatement.setString(1, obj.getNamePosition());
            preparedStatement.setInt(2, obj.getSalary());
			preparedStatement.setInt(3, obj.getId())
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
                        ("DELETE FROM positions WHERE position_id = ?");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

