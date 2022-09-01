<html>
<head>
    <title>Search session</title>
</head>
<body>
    <div id="feedback"></div>
    <h1>Search</h1>
    <br>
    <form id="search-form">
        <label>
            Film name
            <input type="text" id="query"/>
        </label>
        <button type="submit" id="search-button">Search</button>
    </form>

    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
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
                type : "GET",
                contentType : "application/json",
                url : "/sessions/search",
                data : { filmName : search },
                timeout : 100000,
                success : function(data) {
                    console.log("SUCCESS: ", data);
                    display(data);
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                    display(e);
                },
                done : function(e) {
                    console.log("DONE");
                    enableSearchButton(true);
                }
            });
        }

        function enableSearchButton(flag) {
            $("#search-button").prop("disabled", flag);
        }

        function display(data) {
            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                    + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            $('#feedback').html(json);
        }
    </script>
</body>
</html>