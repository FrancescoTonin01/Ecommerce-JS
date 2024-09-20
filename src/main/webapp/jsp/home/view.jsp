<%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 13/09/2024
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.ecommerce.model.mo.Product" %>
<%@ include file="/include/header.inc.jsp" %>

<%
    menuActiveLink = "Home";
    List<Product> products = (List<Product>) request.getAttribute("products");

    // Debug: stampa il numero di prodotti trovati
    System.out.println("Numero di prodotti trovati: " + (products != null ? products.size() : 0));
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - E-commerce</title>
    <!-- Aggiungi i link ai file CSS di Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <main class="container">
        <h2>Benvenuto nel nostro E-commerce</h2>

        <% if (applicationMessage != null) { %>
            <div class="alert alert-info"><%= applicationMessage %></div>
        <% } %>

        <p>Esplora i nostri prodotti e trova ciò che fa per te!</p>

        <% if (products != null && !products.isEmpty()) { %>
            <h3>I nostri prodotti</h3>
            <ul>
                <% for (Product product : products) { %>
                    <li>
                        <a href="Dispatcher?controllerAction=Home.seeProduct&productId=<%= product.getId() %>">
                            <%= product.getName() %> - €<%= product.getPrice() %>
                        </a>
                    </li>
                <% } %>
            </ul>
        <% } else { %>
            <p>Nessun prodotto disponibile.</p>
        <% } %>
    </main>

    <!-- Aggiungi i link ai file JavaScript di Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function seeProduct(productId) {
            var f = document.productForm;
            f.productId.value = productId;
            f.submit();
        }
    </script>
</body>
</html>

