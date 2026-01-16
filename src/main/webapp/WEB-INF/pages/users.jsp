<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>

    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
        <a href="${pageContext.request.contextPath}/AddUser"
           class="btn btn-primary btn-lg">
            Add User
        </a>
    </c:if>

    <c:if test="${empty users}">
        <p>No users found.</p>
    </c:if>

    <c:if test="${not empty users}">
        <div class="container text-center">
            <c:forEach var="u" items="${users}">
                <div class="row">
                    <div class="col">
                            ${u.username}
                    </div>
                    <div class="col">
                            ${u.email}
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</t:pageTemplate>
