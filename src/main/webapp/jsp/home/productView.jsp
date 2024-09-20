<%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 13/09/2024
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.ecommerce.model.mo.Product" %>

<%
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Home";
    Product product = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body>

<main>
    <h1></h1>
    <% if(product != null) { %>
    <span class="name"><%= product.getName() %></span>
    <br/>
    <span class="price"><%= product.getPrice() %></span>

    <span class="description"><%= product.getDescription() %></span><br/><br/>
    <% } else { %>
    <h2>NOT FOUND</h2>
    <% } %>

</main>

</body>
</html>
