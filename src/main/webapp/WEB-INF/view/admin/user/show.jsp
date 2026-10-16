<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <link rel="stylesheet" href="css/test.css">
                <title>Table User</title>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h3>User Details ${user.id}</h3>
                            </div>
                            <hr />
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">User Information</h5>
                                    <p class="card-text"><strong>ID:</strong> ${user.id}</p>
                                    <p class="card-text"><strong>Email:</strong> ${user.email}</p>
                                    <p class="card-text"><strong>Full Name:</strong> ${user.fullName}</p>
                                    <p class="card-text"><strong>Phone Number:</strong> ${user.phone}</p>
                                    <p class="card-text"><strong>Address:</strong> ${user.address}</p>
                                </div>
                            </div>
                            <a href="/admin/users" class="btn btn-secondary mt-3">Back to Users</a>
                        </div>
                    </div>
            </body>

            </html>