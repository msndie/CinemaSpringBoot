<!DOCTYPE html>
<html lang="en">

<#import "../../concern/utils.ftl" as u>

<head>
    <title>Sessions manage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <@u.commonStyles/>
</head>

<body>
    <#--  <@u.adminNavbar/>  -->
    <div class="container pt-4">
        <#if model["FilmsList"]?has_content && model["HallsList"]?has_content>
            <div class="container-fluid pb-2">
                <fieldset>
                    <legend class="h1">Add movie session</legend>
                    <form name="session" action="/admin/panel/sessions" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="row pb-3 g-3">
                            <div class="col-md-6">
                                <select class="form-select" name="film" aria-label="Film" required>
                                    <option selected>Select movie</option>
                                    <#list model["FilmsList"] as film>
                                        <option value="${film.id}">${film.title} ${film.year?string.computer}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <select class="form-select" name="hall" aria-label="Hall" required>
                                    <option selected>Select hall</option>
                                    <#list model["HallsList"] as hall>
                                        <option value="${hall.id}">${hall.serialNumber?string.computer}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="row g-3">
                            <div class="col-md-6">
                                <input class="form-control" type="datetime-local" name="date" aria-label="Time"
                                    placeholder="Time" required />
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="number" name="price" min="1" aria-label="Price"
                                    placeholder="Price" required />
                            </div>
                            <div class="d-grid col-md-1">
                                <input class="btn btn-primary" type="submit" value="Add session" />
                            </div>
                        </div>
                    </form>
                </fieldset>
            </div>
            <hr />
            <div class="container table-responsive">
                <table class="datatable table table-hover">
                    <thead>
                    <th>Title (year)</th>
                    <th>Serial number of hall</th>
                    <th>Ticket price</th>
                    <th>Date and time</th>
                    </thead>
                    <#if model["SessionsList"]?has_content>
                        <#list model["SessionsList"] as session>
                            <tr>
                                <td>${session.film.title} (${session.film.year?string.computer})</td>
                                <td>${session.hall.serialNumber?string.computer}</td>
                                <td>${session.price?string.computer}</td>
                                <td>${session.getFormattedDateTime()}</td>
                            </tr>
                        </#list>
                    </#if>
                </table>
            </div>
        <#else>
            <h1 class="h1">Add halls and films first</h1>
        </#if>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
</body>

</html>