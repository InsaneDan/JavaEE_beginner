<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<jsp:include page="header.jsp">
    <jsp:param name="header" value="Category form"/>
</jsp:include>

<body>

<c:import url="navbar.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/catalog/*" var="categorySubmitUrl"/>
            <form action="${categorySubmitUrl}" method="post">
                <input type="hidden" id="id" name="id" value="${category.id}">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${category.name}" placeholder="New category name">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" class="form-control" id="description" name="description" value="${category.description}" placeholder="Category description">
                </div>

                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-outline-primary">Submit</button>
                    <c:url value="/catalog/" var="catalogUrl"/>
                    <a class="btn btn-outline-primary" href="${catalogUrl}">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="scripts.jsp"/>

</body>
</html>