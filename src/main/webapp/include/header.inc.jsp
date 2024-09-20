<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ecommerce.ecommerce.model.mo.User" %>
<%
    String menuActiveLink = (String) request.getAttribute("menuActiveLink");
    Boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    User loggedUser = (User) request.getAttribute("loggedUser");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="Dispatcher?controllerAction=Home.view">E-commerce</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item <%= "Home".equals(menuActiveLink) ? "active" : "" %>">
                <a class="nav-link" href="Dispatcher?controllerAction=Home.view">Home</a>
            </li>
            <% if (loggedOn != null && loggedOn) { %>
                <li class="nav-item <%= "EditProfile".equals(menuActiveLink) ? "active" : "" %>">
                    <a class="nav-link" href="Dispatcher?controllerAction=UserController.editProfileView">Modifica i tuoi dati</a>
                </li>
            <% } %>
        </ul>
        <ul class="navbar-nav">
            <% if (loggedOn != null && loggedOn && loggedUser != null) { %>
                <li class="nav-item">
                    <span class="navbar-text">
                        Benvenuto, <%= loggedUser.getUsername() %>!
                    </span>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Dispatcher?controllerAction=Login.logout">Logout</a>
                </li>
            <% } else { %>
                <li class="nav-item <%= "Login".equals(menuActiveLink) ? "active" : "" %>">
                    <a class="nav-link" href="Dispatcher?controllerAction=Login.loginView">Login</a>
                </li>
            <% }%>
        </ul>
    </div>
</nav>

<% if (applicationMessage != null && !applicationMessage.isEmpty()) { %>
    <div class="alert alert-info" role="alert">
        <%= applicationMessage %>
    </div>
<% } %>
