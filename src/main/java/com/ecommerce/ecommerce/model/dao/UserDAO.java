package com.ecommerce.ecommerce.model.dao;

import com.ecommerce.ecommerce.model.mo.User;
import java.util.List;
import java.sql.Date;

public interface UserDAO {

    // Funzioni CRUD di base
    User create(int id, String username, String email, String password, String role, Date created_at, Date updated_at);
    User getUserById(int id);
    User updateUser(User user);
    void updateUserLocal(User user);
    void delete(User user);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getUsersByRole(String role);
    boolean authenticateUser(String username, String password);
    void updateLastLoginDate(int userId);
    boolean isEmailUnique(String email);
    List<User> searchUsers(String keyword);
    User findLoggedUser();
}
