package com.ecommerce.ecommerce.model.dao.CookieImpl;
import com.ecommerce.ecommerce.model.dao.UserDAO;
import com.ecommerce.ecommerce.model.mo.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

public class UserDAOCookieImpl implements UserDAO {
    HttpServletRequest request;
  HttpServletResponse response;

  public UserDAOCookieImpl(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }
  
  @Override
  public User create(int id, String username, String email, String password, String role, Date created_at, Date updated_at) {
    if (username == null || email == null || password == null || role == null) {
        throw new IllegalArgumentException("I campi username, email, password e role non possono essere null");
    }

    User loggedUser = new User();
    loggedUser.setId(id);
    loggedUser.setUsername(username);
    loggedUser.setEmail(email);
    loggedUser.setPassword(password);
    loggedUser.setRole(role);
    loggedUser.setCreated_at(created_at);
    loggedUser.setUpdated_at(updated_at);

    String encodedUser = encode(loggedUser);
    Cookie cookie = new Cookie("loggedUser", encodedUser);
    cookie.setPath("/");
    response.addCookie(cookie);
    System.out.println("Cookie creato: " + encodedUser);
    return loggedUser;
}

  private String encode(User loggedUser) {

    String encodedLoggedUser;
    encodedLoggedUser = loggedUser.getId() + "#" + loggedUser.getUsername() + "#" + loggedUser.getEmail() + "#" + loggedUser.getPassword() + "#" + loggedUser.getRole() + "#" + loggedUser.getCreated_at() + "#" + loggedUser.getUpdated_at();
    System.out.println("User codificato in encode: " + encodedLoggedUser);
    return encodedLoggedUser;

  }

  private User decode(String encodedLoggedUser) {
    if (encodedLoggedUser == null || encodedLoggedUser.isEmpty()) {
        return null;
    }

    String[] values = encodedLoggedUser.split("#");
    if (values.length < 7) {
        System.err.println("Formato cookie non valido: " + encodedLoggedUser);
        return null;
    }

    User loggedUser = new User();
    try {
        loggedUser.setId(Integer.parseInt(values[0]));
        loggedUser.setUsername(values[1]);
        loggedUser.setEmail(values[2]);
        loggedUser.setPassword(values[3]);
        loggedUser.setRole(values[4]);
        loggedUser.setCreated_at(values[5].equals("null") ? null : Date.valueOf(values[5]));
        loggedUser.setUpdated_at(values[6].equals("null") ? null : Date.valueOf(values[6]));
    } catch (IllegalArgumentException e) {
        System.err.println("Errore nella decodifica del cookie: " + e.getMessage());
        return null;
    }

    return loggedUser;
}
  public User findLoggedUser() {
    Cookie[] cookies = request.getCookies();
    System.out.println("Cercando il cookie 'loggedUser'");

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            System.out.println("Cookie trovato: " + cookie.getName() + " = " + cookie.getValue());
            if (cookie.getName().equals("loggedUser")) {
                try {
                    User loggedUser = decode(cookie.getValue());
                    System.out.println("Cookie 'loggedUser' decodificato: " + loggedUser);
                    return loggedUser;
                } catch (Exception e) {
                    System.err.println("Errore nella decodifica del cookie: " + e.getMessage());
                    e.printStackTrace();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    } else {
        System.out.println("Nessun cookie trovato");
    }
    System.out.println("Nessun cookie 'loggedUser' trovato");
    return null;
}

@Override
  public User getUserByEmail(String email) {
    return null;
  }

  @Override
  public void updateUserLocal(User loggedUser) {
    Cookie cookie;
    cookie = new Cookie("loggedUser", encode(loggedUser));
    cookie.setPath("/");
    response.addCookie(cookie);
}
public void delete(User loggedUser) {

    Cookie cookie;
    cookie = new Cookie("loggedUser", "");
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);

  }

@Override
public User getUserByUsername(String username) {
    return null;
}

@Override
public void updateLastLoginDate(int userId) {
}

@Override
public List<User> searchUsers(String query) {
    return null; // Implementazione temporanea
}

@Override
public User getUserById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
}

    @Override
    public User updateUser(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
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
public boolean isEmailUnique(String email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isEmailUnique'");
}



}
