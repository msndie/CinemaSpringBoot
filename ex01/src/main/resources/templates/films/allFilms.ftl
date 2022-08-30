<html>
<head>
    <title>Films</title>
</head>
<body>
    <#if model["Films"]?has_content>
        <table class="filmTable">
            <tr>
                <th>Title</th>
                <th>Year of release</th>
                <th>Age restrictions</th>
                <th>Description</th>
                <th>Poster</th>
            </tr>
            <#list model["Films"] as film>
                <tr>
                    <td><a href="/films/${film.id}/chat">${film.title}</a></td>
                    <td>${film.year?string.computer}</td>
                    <td>${film.ageRestrictions}</td>
                    <td>${film.description}</td>
                    <td>
                        <#if film.image??>
                            <a href="/images/${film.image.uuid}" target="_blank">${film.image.name}</a>
                        <#else>
                            No poster
                        </#if>
                    </td>
                </tr>
            </#list>
        </table>
    </#if>
</body>
</html>