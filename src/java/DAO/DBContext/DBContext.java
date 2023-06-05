package DAO.DBContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author nguyenson
 */
public interface DBContext {

    public Connection getConnection() throws SQLException;
}
