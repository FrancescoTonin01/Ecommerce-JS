<%@ page import="com.ecommerce.ecommerce.model.mo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/header.inc.jsp" %>

<%
    menuActiveLink = "EditProfile";
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Profilo - E-commerce</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <main class="container">
        <h2>Modifica il tuo profilo</h2>

        <% if (applicationMessage != null) { %>
            <div class="alert alert-info"><%= applicationMessage %></div>
        <% } %>

        <% if (loggedUser != null) { %>
            <form action="Dispatcher" method="post">
                <input type="hidden" name="controllerAction" value="UserController.updateProfile">
                <input type="hidden" name="userId" value="<%= loggedUser.getId() %>">

                <div class="form-group">
                    <label for="username">Nome utente:</label>
                    <input type="text" id="username" name="username" class="form-control" value="<%= loggedUser.getUsername() %>" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" value="<%= loggedUser.getEmail() %>" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" class="form-control" value="<%= loggedUser.getPassword() %>" required>
                </div>

                <button type="submit" class="btn btn-primary">Aggiorna</button>
            </form>
        <% } else { %>
            <p>Utente non autenticato. <a href="Dispatcher?controllerAction=Login.loginView">Effettua il login</a></p>
        <% } %>
    </main>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>