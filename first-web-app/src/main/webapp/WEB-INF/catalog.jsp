<%--<%@ page import="ru.geekbrains.persist.ProductRepository" %>
<%@ page import="ru.geekbrains.persist.Product" %>
<%@ page import="java.util.List" %>--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<jsp:include page="header.jsp">
    <jsp:param name="header" value="Category list"/>
</jsp:include>

<body>

<c:import url="navbar.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/catalog/new" var="categoryNewUrl" />
            <a class="btn btn-primary" href="${categoryNewUrl}">Добавить категорию</a>
        </div>
        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:forEach var="category" items="${requestScope.categories}">
                    <th scope="row"><c:out value="${category.id}"/></th>
                    <td><c:out value="${category.name}"/></td>
                    <td><c:out value="${category.description}"/></td>
                    <td>
                        <c:url value="/catalog/edit" var="categoryEditUrl">
                            <c:param name="id" value="${category.id}"/>
                        </c:url>
                        <a class="btn btn-outline-success" href="${categoryEditUrl}"><i class="fas fa-edit"></i></a>
                        <c:url value="/catalog/delete" var="categoryDeleteUrl">
                            <c:param name="id" value="${category.id}"/>
                        </c:url>
                        <a class="btn btn-outline-danger" href="${categoryDeleteUrl}"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<c:import url="scripts.jsp"/>

</body>
</html>