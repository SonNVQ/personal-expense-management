package DAO.Impl;

import DAO.DBContext.DBContext;
import DAO.UserDAO;
import Models.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyenson
 */
public class UserDAOImpl implements UserDAO {

    private final DBContext dBContext;

    public UserDAOImpl(DBContext dBContext) {
        this.dBContext = dBContext;
    }

    private static final String INSERT_QUERY = "INSERT INTO users VALUES (?, ?, ?, ?, ?, DEFAULT)";

    @Override
    public User save(User obj) {
        try ( Connection conn = dBContext.getConnection();
                 PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setNString(1, obj.getUsername());
            statement.setNString(2, obj.getPassword());
            statement.setNString(3, obj.getFirstName());
            statement.setNString(4, obj.getLastName());
            statement.setDate(5, (Date) obj.getDob());
            int affectedRow = statement.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setId(rs.getInt(1));
                        return obj;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static final String FIND_ALL_QUERY = "SELECT id, username, first_name, last_name, dob, created_time FROM users";

    @Override
    public List<User> findAll() {
        try ( Connection conn = dBContext.getConnection();
                 PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
                 ResultSet rs = statement.executeQuery()) {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = User.builder()
                        .id(rs.getInt("id"))
                        .username(rs.getNString("username"))
                        .firstName(rs.getNString("first_name"))
                        .lastName(rs.getNString("last_name"))
                        .dob(rs.getDate("dob"))
                        .created_time(rs.getTime("created_time"))
                        .build();
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static final String FIND_BY_ID_QUERY = "SELECT id, username, first_name, last_name, dob, created_time FROM users WHERE id = ?";

    @Override
    public User findByID(Integer id) {
        try ( Connection conn = dBContext.getConnection();
                 PreparedStatement statement = conn.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setInt(1, id);
            try ( ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = User.builder()
                            .id(rs.getInt("id"))
                            .username(rs.getNString("username"))
                            .firstName(rs.getNString("first_name"))
                            .lastName(rs.getNString("last_name"))
                            .dob(rs.getDate("dob"))
                            .created_time(rs.getTime("created_time"))
                            .build();
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static final String UPDATE_INFO_QUERY = "UPDATE users SET username = ?, first_name = ?, last_name = ?, dob = ? WHERE id = ?";

    @Override
    public User update(User obj) {
        try ( Connection conn = dBContext.getConnection();
                 PreparedStatement statement = conn.prepareStatement(UPDATE_INFO_QUERY)) {
            statement.setNString(1, obj.getUsername());
            statement.setNString(2, obj.getFirstName());
            statement.setNString(3, obj.getLastName());
            statement.setDate(4, (Date) obj.getDob());
            statement.setInt(5, obj.getId());
            int affectedRow = statement.executeUpdate();
            if (affectedRow > 0) {
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";

    @Override
    public User delete(User obj) {
        try ( Connection conn = dBContext.getConnection();
                 PreparedStatement statement = conn.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, obj.getId());
            int affectedRow = statement.executeUpdate();
            if (affectedRow > 0) {
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE id = ?";

    @Override
    public User updatePassword(User user, String newPassword) {
        try ( Connection conn = dBContext.getConnection();
                 PreparedStatement statement = conn.prepareStatement(UPDATE_PASSWORD_QUERY)) {
            statement.setNString(1, newPassword);
            statement.setInt(2, user.getId());
            int affectedRow = statement.executeUpdate();
            if (affectedRow > 0) {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
