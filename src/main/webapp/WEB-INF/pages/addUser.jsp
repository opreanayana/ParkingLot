<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add User">

    <h1>Add User</h1>

    <form class="needs-validation"
          novalidate
          method="POST"
          action="${pageContext.request.contextPath}/AddUser">

        <div class="row">
            <div class="col-md-6">
                <label>Username</label>
                <input type="text" name="username" class="form-control" required>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <label>Email</label>
                <input type="email" name="email" class="form-control" required>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <label>Password</label>
                <input type="password" name="password" class="form-control" required>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <label>Groups</label>
                <select name="user_groups" class="form-select" multiple>
                    <c:forEach var="group" items="${userGroups}">
                        <option value="${group}">${group}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <br>

        <button type="submit" class="btn btn-primary btn-lg">
            Save
        </button>

    </form>

</t:pageTemplate>
