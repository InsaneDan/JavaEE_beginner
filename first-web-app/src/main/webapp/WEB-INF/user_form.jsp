<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<jsp:include page="header.jsp">
    <jsp:param name="header" value="User form"/>
</jsp:include>

<body>

<c:import url="navbar.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/user/*" var="userSubmitUrl"/>
            <form action="${userSubmitUrl}" method="post">
                <input type="hidden" id="id" name="id" value="${user.id}">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">First name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}" placeholder="First name">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Last name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}" placeholder="Last name">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="Email">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Login</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="login" name="login" value="${user.login}" placeholder="Login">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" name="password" value="${user.password}" placeholder="Password">
                    </div>
                </div>

                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <c:url value="/user/" var="userUrl"/>
                    <a class="btn btn-outline-primary" href="${userUrl}">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="scripts.jsp"%>

</body>
</html>