package DAO;

import Models.User;

/**
 *
 * @author nguyenson
 */
public interface UserDAO extends CrudDAO<User, Integer>{
    public User updatePassword(User user, String newPassword);
}
