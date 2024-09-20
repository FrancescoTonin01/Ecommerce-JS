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

public class UserController {

    private UserController() {}

    public static void editProfileView(HttpServletRequest request, HttpServletResponse response) {
        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        User loggedUser = null;

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
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("loggedOn", true);
                request.setAttribute("viewUrl", "user/view");
                request.setAttribute("menuActiveLink", "EditProfile");
            } else {
                request.setAttribute("applicationMessage", "Utente non autenticato. Effettua il login.");
                request.setAttribute("viewUrl", "login/view");
                request.setAttribute("menuActiveLink", "Login");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            applicationMessage = "Si è verificato un errore. Riprova più tardi.";
        } finally {
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }

        request.setAttribute("applicationMessage", applicationMessage);
    }

    public static void updateProfile(HttpServletRequest request, HttpServletResponse response) {
        DAOFactory daoFactory = null;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL, null);
            daoFactory.beginTransaction();

            UserDAO userDAO = daoFactory.getUserDAO();
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = userDAO.getUserById(userId);

            user.setUsername(request.getParameter("username"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user = userDAO.updateUser(user);

            daoFactory.commitTransaction();

            request.setAttribute("loggedOn", true);
            request.setAttribute("loggedUser", user);
            request.setAttribute("applicationMessage", "Profilo aggiornato con successo!");
            request.setAttribute("viewUrl", "user/view");
            request.setAttribute("menuActiveLink", "EditProfile");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }
    }
}
