<html>
<head>
    <title></title>
</head>
<body>
    <#assign film=model["FilmsList"]/>
    <table class="datatable">
        <tr>
            <th>Title</th>
            <th>Year of release</th>
            <th>Age restrictions</th>
            <th>Description</th>
            <th>Poster</th>
        </tr>
        <tr>
            <td>${film.title}</td>
            <td>${film.year?string.computer}</td>
            <td>${film.ageRestrictions}</td>
            <td>${film.description}</td>
            <#if film.poster??>
                <td>
                    <a href="/images/${film.poster.uuid}" target="_blank">${film.poster.name}</a>
                </td>
            <#else>
                <td>No poster</td>
            </#if>
        </tr>
    </table>
</body>
</html>