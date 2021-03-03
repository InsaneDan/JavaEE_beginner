
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">

    <c:url value="/http-servlet/" var="pageMain"/>
    <a class="navbar-brand" href="${pageMain}">Главная</a>

    <c:url value="/catalog/" var="pageCatalog"/>
    <a class="navbar-brand" href="${pageCatalog}">Каталог</a>

    <c:url value="/product/" var="pageProduct"/>
    <a class="navbar-brand" href="${pageProduct}">Продукт</a>

    <c:url value="/cart/" var="pageCart"/>
    <a class="navbar-brand" href="${pageCart}">Корзина</a>

    <c:url value="/order/" var="pageOrder"/>
    <a class="navbar-brand" href="${pageOrder}">Заказ</a>

    <c:url value="/user/" var="pageOrder"/>
    <a class="navbar-brand" href="${pageOrder}">Пользователи</a>

        </div>
    </div>
<%--    <c:url value="/user" var="pageUser"/>--%>
<%--    <a class="navbar-brand" href="${pageUser}">Пользователь</a>--%>

</nav>