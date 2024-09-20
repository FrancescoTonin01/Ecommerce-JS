<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/header.inc.jsp" %>

<%
    menuActiveLink = "Login";
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - E-commerce</title>
    <!-- Aggiungi i link ai file CSS di Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <main class="container">
        <h2>Accedi al tuo account</h2>

        <form action="Dispatcher" method="post">
            <input type="hidden" name="controllerAction" value="Login.logon">

            <div class="form-group">
                <label for="username">Nome utente:</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary">Accedi</button>
        </form>
    </main>

    <!-- Aggiungi i link ai file JavaScript di Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
