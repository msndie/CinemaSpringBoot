<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.school21.cinema.models.Session" %>
<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="edu.school21.cinema.models.Image" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Session> sessionList = (List<Session>) request.getSession().getAttribute("SessionAttributes"); %>
<% ArrayList<Image> imageList = (ArrayList<Image>) request.getSession().getAttribute("Images"); %>
<% User u = (User) request.getSession().getAttribute("UserAttributes"); %>

<!DOCTYPE html>
<html lang="en">

<head>
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
            align-items: flex-start;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .avatar_img {
            width: 200px;
            height: 200px;
            object-fit: cover;
            border-radius: 50%;
        }

        .card-table {
            max-height: 50%;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
        }
    </style>
    <title>Profile</title>
</head>

<body>
    <div class="container py-5">
        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <c:set var="size" value="<%=imageList.size()%>" />
                        <c:choose>
                            <c:when test="${size > 0}">
                                <img src="images/<%=imageList.get(imageList.size() - 1).getUuid()%>" id="avatar-image"
                                    class="avatar_img" alt="Avatar" />
                            </c:when>
                            <c:otherwise>
                                <img src="https://cdn-images-1.medium.com/max/1200/1*OJAuAh5qU6DoJTvt7s7lpw.png"
                                    id="avatar-image" class="avatar_img" alt="Default img" />
                            </c:otherwise>
                        </c:choose>
                        <h5 class="my-3">
                            <%= u.getFirstName() + " " + u.getLastName() %>
                        </h5>
                        <p class="text-muted mb-4">
                            <%= u.getEmail() %>
                        </p>
                        <div class="d-flex justify-content-center mb-2">
                            <form action="images/" method="post" enctype="multipart/form-data">
                                <div class="mb-3 mw-100">
                                    <input type="file" name="file" accept="image/*" class="form-control" required>
                                </div>
                                <input type="submit" value="Upload File" class="btn btn-outline-primary ms-1">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8" style="height: 70vh;">
                <div class="overflow-auto card card-table mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">File name</p>
                            </div>
                            <div class="col-sm-3">
                                <p class="text mb-0">Size</p>
                            </div>
                            <div class="col-sm-3">
                                <p class="text mb-0">MIME</p>
                            </div>
                        </div>
                        <c:forEach var="i" items="<%= imageList %>">
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0"><a href="images/${i.uuid.toString()}" target="_blank">${i.name}</a>
                                    </p>
                                </div>
                                <div class="col-sm-3">
                                    <p class="text mb-0">${i.sizeInStr}</p>
                                </div>
                                <div class="col-sm-3">
                                    <p class="text mb-0">${i.mime}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="overflow-auto card card-table mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Date</p>
                            </div>
                            <div class="col-sm-3">
                                <p class="text mb-0">Time</p>
                            </div>
                            <div class="col-sm-3">
                                <p class="text mb-0">IP</p>
                            </div>
                        </div>
                        <c:forEach var="s" items="<%= sessionList %>">
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">${s.formatedDate}</p>
                                </div>
                                <div class="col-sm-3">
                                    <p class="text mb-0">${s.formatedTime}</p>
                                </div>
                                <div class="col-sm-3">
                                    <p class="text mb-0">${s.ip}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>

</html>