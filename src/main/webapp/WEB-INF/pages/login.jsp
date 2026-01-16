<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Login">

    <c:if test="${message != null}">
        <div class="alert alert-warning" role="alert">
                ${message}
        </div>
    </c:if>

    <h1>Sign in</h1>

    <form class="needs-validation" method="POST" action="j_security_check">
        <div class="row g-3">

            <div class="col-12">
                <label for="username" class="form-label">Username</label>
                <input type="text"
                       id="username"
                       name="j_username"
                       class="form-control"
                       placeholder="Username"
                       required
                       autofocus>
            </div>

            <div class="col-12">
                <label for="password" class="form-label">Password</label>
                <input type="password"
                       id="password"
                       name="j_password"
                       class="form-control"
                       placeholder="Password"
                       required>
            </div>

        </div>

        <hr class="my-4">

        <button class="btn btn-primary btn-lg" type="submit">
            Sign in
        </button>
    </form>

</t:pageTemplate>
