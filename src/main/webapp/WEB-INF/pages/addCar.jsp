<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pageTemplate pageTitle="Add Car">

    <h1>Add Car</h1>

    <form class="needs-validation"
          novalidate
          method="POST"
          action="${pageContext.request.contextPath}/AddCar">

        <div class="row g-3">

            <!-- License plate -->
            <div class="col-12">
                <label for="license_plate" class="form-label">License Plate</label>
                <input type="text"
                       class="form-control"
                       id="license_plate"
                       name="license_plate"
                       required>

                <div class="invalid-feedback">
                    License Plate is required
                </div>
            </div>

            <!-- Parking spot -->
            <div class="col-12">
                <label for="parking_spot" class="form-label">Parking Spot</label>
                <input type="text"
                       class="form-control"
                       id="parking_spot"
                       name="parking_spot"
                       required>

                <div class="invalid-feedback">
                    Parking Spot is required
                </div>
            </div>

            <!-- Owner -->
            <div class="col-12">
                <label for="owner_id" class="form-label">Owner</label>
                <select class="form-select" id="owner_id" name="owner_id" required>
                    <option value="">Choose...</option>

                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}">${user.username}</option>
                    </c:forEach>
                </select>


                <div class="invalid-feedback">
                    Owner is required
                </div>
            </div>

        </div>

        <hr class="my-4">

        <button type="submit" class="btn btn-primary btn-lg">
            Save
        </button>

    </form>

</t:pageTemplate>
