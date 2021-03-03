<%--<%@ page import="ru.geekbrains.persist.ProductRepository" %>
<%@ page import="ru.geekbrains.persist.Product" %>
<%@ page import="java.util.List" %>--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<jsp:include page="header.jsp">
    <jsp:param name="header" value="Product list"/>
</jsp:include>

<body>

<c:import url="navbar.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/product/new" var="productNewUrl" />
            <a class="btn btn-primary" href="${productNewUrl}">Добавить продукт</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${requestScope.products}">
<%--                <% for (Product product : (List<Product>) request.getAttribute("products")) { %>--%>
                <tr>
                    <th scope="row">
                        <c:out value="${product.id}"/>
<%--                        <%= product.getId() %>--%>
                    </th>
                    <td>
                        <c:out value="${product.name}"/>
<%--                        <%= product.getName() %>--%>
                    </td>
                    <td>
                        <c:out value="${product.description}"/>
<%--                        <%= product.getDescription() %>--%>
                    </td>
                    <td>$
                        <c:out value="${product.price}"/>
<%--                        <%= product.getPrice() %>--%>
                    </td>
                    <td>
                        <c:url value="/product/edit" var="productEditUrl">
                            <c:param name="id" value="${product.id}"/>
                        </c:url>
                        <a class="btn btn-success" href="${productEditUrl}"><i class="fas fa-edit"></i></a>
                        <c:url value="/product/delete" var="productDeleteUrl">
                            <c:param name="id" value="${product.id}"/>
                        </c:url>
                        <a class="btn btn-danger" href="${productDeleteUrl}"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>
<%--                <% } %>--%>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<c:import url="scripts.jsp"/>

</body>
</html>