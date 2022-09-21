<!DOCTYPE html>
<html lang="en">

<head>
    <title>Halls manage</title>
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
    <div class="container-fluid pb-2">
        <fieldset>
            <legend class="h1">Add movie hall</legend>
            <form name="car" action="/admin/panel/halls" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row pb-3 g-3">
                    <div class="col-md-4">
                        <input class="form-control" type="number" name="serialNumber" min="1"
                               aria-label="Serial number" placeholder="Serial number" required />
                    </div>

                    <div class="col-md-4">
                        <input class="form-control" type="number" name="numberOfSeats" min="1"
                               aria-label="Number of seats" placeholder="Number of seats" required />
                    </div>

                    <div class="d-grid col-md-1">
                        <input class="btn btn-primary" type="submit" value="Add hall" />
                    </div>
                </div>
            </form>
        </fieldset>
    </div>
    <hr />
    <div class="container table-responsive">
        <#if model["HallsList"]?has_content>
            <table class="datatable table table-hover ">
                <thead>
                <th>Serial Number</th>
                <th>Number of seats</th>
                </thead>
                <#list model["HallsList"] as hall>
                    <tr>
                        <td>${hall.serialNumber?string.computer}</td>
                        <td>${hall.numberOfSeats?string.computer}</td>
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
