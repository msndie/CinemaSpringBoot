<!DOCTYPE html>
<html lang="en">

<#import "../../concern/utils.ftl" as u>

<head>
    <title>Films manager</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <@u.commonStyles/>
</head>

<body>
    <#--  <@u.adminNavbar/>  -->
    <div class="container pt-4">
        <div class="container-fluid pb-2">
            <fieldset>
                <legend class="h1">Add movie</legend>
                <form name="film" action="/admin/panel/films" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="row pb-3 g-3">
                        <div class="col-md-4">
                            <input class="form-control" type="text" name="title" required aria-label="Title"
                                placeholder="Title">
                        </div>
                        <div class="col-md-4">
                            <input class="form-control" type="number" name="year" min="1895" max="2022" required
                                aria-label="Year" placeholder="Year of release (1895 - 2022)">
                        </div>
                        <div class="col-md-4">
                            <input class="form-control" type="number" name="age" min="0" max="21" required
                                aria-label="Age" placeholder="Age restrictions">
                        </div>
                    </div>

                    <div class="row g-3">
                        <div class="col-md-6">
                            <input class="form-control" type="text" name="description" required aria-label="Descr"
                                placeholder="Description">
                        </div>
                        <div class="col-md-4">
                            <input class="form-control" type="file" accept="image/*" name="file">
                        </div>
                        <div class="d-grid col-md-1">
                            <input class="btn btn-primary" type="submit" value="Add film" />
                        </div>
                    </div>
                </form>
            </fieldset>
        </div>
        <hr />
        <div class="container table-responsive">
            <#if model["FilmsList"]?has_content>
                <table class="datatable table table-hover">
                    <thead>
                    <th>Title</th>
                    <th>Year of release</th>
                    <th>Age restrictions</th>
                    <th>Description</th>
                    <th>Poster</th>
                    </thead>
                    <#list model["FilmsList"] as film>
                        <tr>
                            <td>${film.title}</td>
                            <td>${film.year?string.computer}</td>
                            <td>${film.ageRestrictions}</td>
                            <td>${film.description}</td>
                            <#if film.image??>
                                <td>
                                    <a href="/images/${film.image.uuid}" target="_blank">${film.image.name}</a>
                                </td>
                            <#else>
                                <td>
                                    <form action="/images" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="hidden" name="type" value="poster">
                                        <div class="row g-3 pt-1">
                                            <div class="col-12">
                                                <input class="form-control" type="file" accept="image/*" name="file"
                                                    required>
                                            </div>
                                            <div class="col">
                                                <input class="btn btn-primary" type="submit"
                                                    value="Upload poster" />
                                            </div>
                                            <input type="hidden" name="id" value="${film.id}">
                                        </div>
                                    </form>
                                </td>
                            </#if>
                        </tr>
                    </#list>
                </table>
            </#if>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
</body>

</html>
