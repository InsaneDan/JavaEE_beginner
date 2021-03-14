<%--<%@ page import="ru.geekbrains.persist.ProductRepository" %>
<%@ page import="ru.geekbrains.persist.Product" %>
<%@ page import="java.util.List" %>--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
    <title>${param.header}</title>
</head>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <c:url value="/" var="pageMain"/>
    <a class="navbar-brand" href="${pageMain}">Главная</a>

    <c:url value="/catalog" var="pageCatalog"/>
    <a class="navbar-brand" href="${pageCatalog}">Каталог</a>

    <c:url value="/product" var="pageProduct"/>
    <a class="navbar-brand" href="${pageProduct}">Продукт</a>

    <c:url value="/cart" var="pageCart"/>
    <a class="navbar-brand" href="${mainPage}">Корзина</a>

    <c:url value="/order" var="pageOrder"/>
    <a class="navbar-brand" href="${mainPage}">Заказ</a>

    <c:url value="/user" var="pageUser"/>
    <a class="navbar-brand" href="${mainPage}">Пользователь</a>

</nav>