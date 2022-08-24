<%@ page import="edu.school21.cinema.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% User u = (User) request.getSession().getAttribute("UserAttributes"); %>

<html>
<head>
    <title>Main page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            justify-content: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
            <c:set var="user" value="<%= u == null %>"/>
            <c:choose>
                <c:when test="${user == true}">
                    <div class="px-4 py-5 my-5 text-center">
                        <h1 class="display-6 mb-4">You're not authorized</h1>
                        <div class="col-lg-6 mt-4 mx-auto">
                            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                                <a class="btn btn-primary btn-lg px-4 gap-3" href="signIn" role="button">Sign&nbsp;In</a>
                                <a class="btn btn-primary btn-lg px-4 gap-3" href="signUp" role="button">Sign&nbsp;Up</a>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="px-4 py-5 my-5 text-center">
                        <h1 class="display-6 mb-4">You're already authorized</h1>
                        <div class="col-lg-6 mt-4 mx-auto">
                            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                                <a class="btn btn-primary btn-lg px-4 gap-3" href="profile" role="button">To&nbsp;Profile</a>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>
