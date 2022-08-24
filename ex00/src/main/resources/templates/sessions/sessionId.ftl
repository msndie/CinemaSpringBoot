<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>
        <#if model["Session"]?has_content>
            Session ${model["Session"].id}
        <#else>
            Session unknown
        </#if>
    </title>
</head>
<body>
    <#if model["Session"]?has_content>
        <table class="filmTable">
            <tr>
                <th>Title</th>
                <th>Year of release</th>
                <th>Age restrictions</th>
                <th>Description</th>
                <th>Poster</th>
            </tr>
            <tr>
                <td>${model["Session"].film.title}</td>
                <td>${model["Session"].film.year?string.computer}</td>
                <td>${model["Session"].film.ageRestrictions}</td>
                <td>${model["Session"].film.description}</td>
                <td>
                    <#if model["Session"].film.poster??>
                        <a href="/images/${model["Session"].film.poster.uuid}" target="_blank">${model["Session"].film.poster.name}</a>
                    <#else>
                        No poster
                    </#if>
                </td>
            </tr>
        </table>
        <table class="hallTable">
            <tr>
                <th>Serial Number</th>
                <th>Number of seats</th>
            </tr>
            <tr>
                <td>${model["Session"].hall.serialNumber?string.computer}</td>
                <td>${model["Session"].hall.numberOfSeats?string.computer}</td>
            </tr>
        </table>
    <#else>
        <h1>No such session</h1>
    </#if>
</body>
</html>