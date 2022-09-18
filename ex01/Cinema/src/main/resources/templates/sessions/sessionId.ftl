<!DOCTYPE html>
<html lang="en">

<head>
    <title>
        <#if model["Session"]?has_content>
            Session ${model["Session"].id}
        <#else>
            Session unknown
        </#if>
    </title>
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
            justify-content: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
    </style>
</head>

<body>
<div class="container col">
    <#if model["Session"]?has_content>
        <div class="container table-responsive">
            <h2 class="h2">Movie info</h2>
            <table class="filmTable table table-hover">
                <thead>
                <th>Title</th>
                <th>Year of release</th>
                <th>Age restrictions</th>
                <th>Description</th>
                <th>Poster</th>
                </thead>
                <tr>
                    <td>${model["Session"].film.title}</td>
                    <td>${model["Session"].film.year?string.computer}</td>
                    <td>${model["Session"].film.ageRestrictions}</td>
                    <td>${model["Session"].film.description}</td>
                    <td>
                        <#if model["Session"].film.image??>
                            <a href="/images/${model["Session"].film.image.uuid}" class="link-primary"
                               target="_blank">${model["Session"].film.image.name}</a>
                        <#else>
                            No poster
                        </#if>
                    </td>
                </tr>
            </table>
        </div>
        <hr />
        <div class="container table-responsive">
            <h2 class="h2">Hall info</h2>
            <table class="hallTable table table-hover">
                <thead>
                <th>Serial Number</th>
                <th>Number of seats</th>
                </thead>
                <tr>
                    <td>${model["Session"].hall.serialNumber?string.computer}</td>
                    <td>${model["Session"].hall.numberOfSeats?string.computer}</td>
                </tr>
            </table>
        </div>
    <#else>
        <h1 class="h1">No such session</h1>
    </#if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>

</html>