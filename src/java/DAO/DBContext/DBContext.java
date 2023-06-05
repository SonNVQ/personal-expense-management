package DAO.DBContext;

import java.sql.Connection;

/**
 *
 * @author nguyenson
 */
public interface DBContext {

    public Connection getConnection() throws Exception;
}
