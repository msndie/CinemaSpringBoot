<html>
<head>
    <title>Manage sessions</title>
</head>
<body>
    <div id="header">
        <h2>FreeMarker Spring MVC Hello World</h2>
    </div>
    <div id="content">
        <#if model["FilmsList"]?has_content && model["HallsList"]?has_content>
            <fieldset>
                <legend>Add movie session</legend>
                <form name="session" action="/admin/panel/sessions" method="post">
                    Film : <select name="film" required>
                        <#list model["FilmsList"] as film>
                            <option value="${film.id}">${film.title} ${film.year?string.computer}</option>
                        </#list>
                    </select>
                    Hall : <select name="hall" required>
                        <#list model["HallsList"] as hall>
                            <option value="${hall.id}">${hall.serialNumber?string.computer}</option>
                        </#list>
                    </select>
                    Time : <input type="datetime-local" name="date" required/>
                    Coast : <input type="number" name="price" required/>
                    <input type="submit" value="Add session" />
                </form>
            </fieldset>
            <br/>
        </#if>
        <table class="datatable">
            <tr>
                <th>Title (year)</th>
                <th>Serial number of hall</th>
                <th>Ticket price</th>
                <th>Date and time</th>
            </tr>
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
</body>
</html>