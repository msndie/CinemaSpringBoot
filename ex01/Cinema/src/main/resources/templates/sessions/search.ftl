<!DOCTYPE html>
<html lang="en">

<head>
    <title>Search session</title>
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
    <fieldset>
        <legend class="h1">Search</legend>
        <form id="search-form">
            <div class="row pb-3 g-3">
                <div class="col-md-4">
                    <input class="form-control" type="text" id="query" aria-label="Film name"
                           placeholder="Film name" />
                </div>
                <div class="d-grid col-md-1">
                    <button class="btn btn-primary" type="submit" id="search-button">Search</button>
                </div>
            </div>
        </form>
    </fieldset>

    <div id="feedback" class="row g-3">
    </div>
</div>

<script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
<#noparse>
    <script>
        jQuery(document).ready(function($) {
            $("#search-form").submit(function(event) {
                // Disble the search button
                enableSearchButton(false);
                // Prevent the form from submitting via the browser.
                event.preventDefault();
                searchViaAjax();
            });
	    });

        function searchViaAjax() {

            var search = $("#query").val();

            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/sessions/search",
                data: { filmName: search },
                timeout: 100000,
                success: function(data) {
                    console.log("SUCCESS: ", data);
                    display(data);
                },
                error: function(e) {
                    console.log("ERROR: ", e);
                    display(e);
                },
                done: function(e) {
                    console.log("DONE");
                    enableSearchButton(true);
                }
            });
        }

        function enableSearchButton(flag) {
            $("#search-button").prop("disabled", flag);
        }

        function display(data) {
            let jsonResponse = '';
            let noImageUrl = 'https://media.istockphoto.com/vectors/default-image-icon-vector-missing-picture-page-for-website-design-or-vector-id1357365823?k=20&m=1357365823&s=612x612&w=0&h=ZH0MQpeUoSHM3G2AWzc8KkGYRg4uP_kuu0Za8GFxdFc=';
            data.sessions.forEach(function(session) {
                jsonResponse +=
                `\n<div class="col-sm-6 col-md-4 col-lg-3">
                    <div class="card">
                        <img src="${session.film.imageUrl !== 'null' ? session.film.imageUrl : noImageUrl}" class="card-img-top" alt="film poster">
                        <div class="card-body">
                            <h5 class="card-title">
                                <a href="sessions/${session.id}">${session.film.title}</a>
                            </h5>
                            <p class="card-text">${session.dateTime}</p>
                            <a href="sessions/${session.id}" class="btn btn-outline-primary stretched-link">Details</a>
                        </div>
                    </div>
                </div>`
            });
            $('#feedback').html(jsonResponse);
        }
    </script>
</#noparse>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>
</html>