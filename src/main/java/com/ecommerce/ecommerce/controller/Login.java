package com.ecommerce.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.ecommerce.ecommerce.services.config.Configuration;
import com.ecommerce.ecommerce.services.logService.LogService;

import com.ecommerce.ecommerce.model.dao.DAOFactory;
import com.ecommerce.ecommerce.model.mo.User;
import com.ecommerce.ecommerce.model.dao.UserDAO;

public class Login {

    private Login() {
    }

    public static void logon(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        User loggedUser;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {

            Map<String, Object> sessionFactoryParameters = new HashMap<>();
            sessionFactoryParameters.put("request", request);
            sessionFactoryParameters.put("response", response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL, sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UserDAO sessionUserDAO = sessionDAOFactory.getUserDAO();
            loggedUser = sessionUserDAO.findLoggedUser();

            if (loggedUser != null) {
                // L'utente è già loggato, reindirizzalo alla home page
                request.setAttribute("loggedOn", true);
                request.setAttribute("loggedUser", loggedUser);
                Home.view(request, response); // Modifica qui
                return; // Aggiungi questo return
            } else {
                // Procedi con il login
                daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL, null);
                daoFactory.beginTransaction();

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                UserDAO userDAO = daoFactory.getUserDAO();
                User user = userDAO.getUserByUsername(username);

                if (user == null || !user.getPassword().equals(password)) {
                    applicationMessage = "Username e password errati!";
                    request.setAttribute("viewUrl", "login/view");
                    request.setAttribute("menuActiveLink", "Login");
                } else {
                    loggedUser = sessionUserDAO.create(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole(), user.getCreated_at(), user.getUpdated_at());
                    request.setAttribute("loggedOn", true);
                    request.setAttribute("loggedUser", loggedUser);
                    request.setAttribute("viewUrl", "user/view");
                }

                daoFactory.commitTransaction();
            }

            sessionDAOFactory.commitTransaction();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }
        request.setAttribute("applicationMessage", applicationMessage);
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        DAOFactory sessionDAOFactory= null;

        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters=new HashMap<String,Object>();
            sessionFactoryParameters.put("request",request);
            sessionFactoryParameters.put("response",response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL,sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UserDAO sessionUserDAO = sessionDAOFactory.getUserDAO();
            sessionUserDAO.delete(null);

            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn",false);
            request.setAttribute("loggedUser", null);
            
            // Modifica qui: reindirizza direttamente alla pagina di login invece di chiamare Home.view
            request.setAttribute("viewUrl", "login/view");
            request.setAttribute("menuActiveLink", "Login");
            request.setAttribute("applicationMessage", "Logout effettuato con successo.");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }
    }

    public static void loginView(HttpServletRequest request, HttpServletResponse response) {
        DAOFactory sessionDAOFactory = null;
        User loggedUser = null;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {
            Map<String, Object> sessionFactoryParameters = new HashMap<>();
            sessionFactoryParameters.put("request", request);
            sessionFactoryParameters.put("response", response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL, sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UserDAO sessionUserDAO = sessionDAOFactory.getUserDAO();
            loggedUser = sessionUserDAO.findLoggedUser();

            sessionDAOFactory.commitTransaction();

            if (loggedUser != null) {
                // L'utente è già loggato, reindirizzalo alla home page
                request.setAttribute("loggedOn", true);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("viewUrl", "home/view");
                request.setAttribute("menuActiveLink", "Home");
            } else {
                // L'utente non è loggato, mostra la pagina di login
                request.setAttribute("loggedOn", false);
                request.setAttribute("loggedUser", null);
                request.setAttribute("viewUrl", "login/view");
                request.setAttribute("menuActiveLink", "Login");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Errore nel controller", e);
            applicationMessage = "Si è verificato un errore. Riprova più tardi.";
        } finally {
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }

        request.setAttribute("applicationMessage", applicationMessage);
    }
}