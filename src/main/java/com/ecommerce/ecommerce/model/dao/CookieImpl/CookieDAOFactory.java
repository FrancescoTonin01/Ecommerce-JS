package com.ecommerce.ecommerce.model.dao.CookieImpl;

import com.ecommerce.ecommerce.model.dao.UserDAO;
import com.ecommerce.ecommerce.model.dao.DAOFactory;
import com.ecommerce.ecommerce.model.dao.ProductDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public class CookieDAOFactory extends DAOFactory {
    private Map factoryParameters;

    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieDAOFactory(Map factoryParameters) {
        this.factoryParameters=factoryParameters;
    }

    @Override
    public void beginTransaction() {

        try {
            this.request=(HttpServletRequest) factoryParameters.get("request");
            this.response=(HttpServletResponse) factoryParameters.get("response");;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void commitTransaction() {}

    @Override
    public void rollbackTransaction() {}

    @Override
    public void closeTransaction() {}

    public UserDAO getUserDAO() {
        return new UserDAOCookieImpl(request, response);
    }

    public ProductDAO getProductDAO() {
        return null;
    }
}
