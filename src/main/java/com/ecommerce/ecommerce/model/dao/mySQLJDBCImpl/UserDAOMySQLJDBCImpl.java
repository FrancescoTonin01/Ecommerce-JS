package com.ecommerce.ecommerce.model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ecommerce.ecommerce.model.mo.User;
import com.ecommerce.ecommerce.model.dao.UserDAO;

public class UserDAOMySQLJDBCImpl implements UserDAO{

    private final String COUNTER_ID = "userId";  
    Connection conn;
  
    public UserDAOMySQLJDBCImpl(Connection conn) {
      this.conn = conn;
    }

  public User getUserByEmail(String email) {

    PreparedStatement ps;
    User user = null;

    try {

      String sql
              = " SELECT * "
              + "   FROM users "
              + " WHERE "
              + "   email = ?";

      ps = conn.prepareStatement(sql);
      ps.setNString(1, email);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {
        user = read(resultSet);
      }
      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return user;

  }
  User read(ResultSet rs) throws SQLException {
    User user = new User();
    user.setId(rs.getInt("id"));
    user.setUsername(rs.getString("username"));
    user.setEmail(rs.getString("email"));
    user.setPassword(rs.getString("password"));
    user.setRole(rs.getString("role"));
    user.setCreated_at(rs.getDate("created_at"));
    user.setUpdated_at(rs.getDate("updated_at"));
    return user;
  }

@Override
public User create(int id, String username, String email, String password, String role, Date created_at,
        Date updated_at) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
}

@Override
public User getUserById(int id) {
    PreparedStatement ps;
    User user = null;

    try {
        String sql = 
            "SELECT * FROM users WHERE id = ?";

        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            user = read(resultSet);
        }
        resultSet.close();
        ps.close();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return user;
}

@Override
public User updateUser(User user) {
    PreparedStatement ps;

    try {
        String sql = 
            "UPDATE users SET " +
            "username = ?, " +
            "email = ?, " +
            "password = ?, " +
            "role = ?, " +
            "updated_at = ? " +
            "WHERE id = ?";

        ps = conn.prepareStatement(sql);
        int i = 1;
        ps.setString(i++, user.getUsername());
        ps.setString(i++, user.getEmail());
        ps.setString(i++, user.getPassword());
        ps.setString(i++, user.getRole());
        ps.setDate(i++, new java.sql.Date(System.currentTimeMillis())); // Imposta la data di aggiornamento
        ps.setInt(i++, user.getId());

        int result = ps.executeUpdate();
        
        if (result != 1) {
            throw new RuntimeException("Errore durante l'aggiornamento dell'utente");
        }

        ps.close();

        // Recupera l'utente aggiornato dal database
        return user;

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

@Override
public void updateUserLocal(User user) {
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
}


@Override
public User getUserByUsername(String username) {
    PreparedStatement ps;
    User user = null;

    try {

        String sql
                = " SELECT * "
                + "   FROM users "
                + " WHERE "
                + "   username = ?";

        ps = conn.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            user = read(resultSet);
        }
        resultSet.close();
        ps.close();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return user;
}

@Override
public List<User> getAllUsers() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
}

@Override
public List<User> getUsersByRole(String role) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUsersByRole'");
}

@Override
public boolean authenticateUser(String username, String password) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'authenticateUser'");
}

@Override
public void updateLastLoginDate(int userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateLastLoginDate'");
}

@Override
public boolean isEmailUnique(String email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isEmailUnique'");
}

@Override
public List<User> searchUsers(String keyword) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchUsers'");
}

@Override
public User findLoggedUser() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findLoggedUser'");
}


@Override
public void delete(User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
}
}
