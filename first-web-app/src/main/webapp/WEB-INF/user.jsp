<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<jsp:include page="header.jsp">
    <jsp:param name="header" value="User list"/>
</jsp:include>

<body>

<c:import url="navbar.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url var="userNewUrl" value="/user/new" />
            <a class="btn btn-primary" href="${userNewUrl}">New User</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">E-mail</th>
                    <th scope="col">Login</th>
                    <th scope="col">Password</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <th scope="row"><c:out value="${user.id}"/></th>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td>
                        <c:url var="userEditUrl" value="/user/edit" >
                            <c:param name="id" value="${user.id}" />
                        </c:url>
                        <a class="btn btn-success" href="${userEditUrl}"><i class="fas fa-edit"></i></a>
                        <c:url var="userDeleteUrl" value="/user/delete" >
                            <c:param name="id" value="${user.id}" />
                        </c:url>
                        <a class="btn btn-danger" href="${userDeleteUrl}"><i class="far fa-trash-alt"></i></a>
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