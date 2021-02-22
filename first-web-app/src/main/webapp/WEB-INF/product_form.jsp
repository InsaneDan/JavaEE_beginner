<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<jsp:include page="header.jsp">
    <jsp:param name="header" value="Product form"/>
</jsp:include>

<body>

<c:import url="navbar.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/product/*" var="productSubmitUrl"/>
            <form action="${productSubmitUrl}" method="post">
                <input type="hidden" id="id" name="id" value="${product.id}">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${product.name}" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" class="form-control" id="description" name="description" value="${product.description}" placeholder="Enter description">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="number" class="form-control" id="price" name="price" value="${product.price}" placeholder="Enter price">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>

<c:import url="scripts.jsp"/>

</body>
</html>