<html>
<head>
    <title>Manage films</title>
</head>
<body>
    <div id="header">
        <h2>FreeMarker Spring MVC Hello World</h2>
    </div>
    <div id="content">
        <fieldset>
            <legend>Add movie hall</legend>
            <form name="film" action="/admin/panel/films" method="post" enctype="multipart/form-data">
                Title : <input type="text" name="title" required/><br/>
                Year of release (1895 - 2022) : <input type="number" name="year" min="1895" max="2022" required/><br/>
                Age restrictions : <input type="number" name="age" required min="0" max="21" /><br/>
                Description : <input type="text" name="description" required/><br/>
                Poster (10mb max) : <input type="file" accept="image/*" name="file"/>
                <input type="submit" value="Add film" />
            </form>
        </fieldset>
        <br/>
        <table class="datatable">
            <tr>
                <th>Title</th>
                <th>Year of release</th>
                <th>Age restrictions</th>
                <th>Description</th>
                <th>Poster</th>
            </tr>
            <#if model["FilmsList"]?has_content>
                <#list model["FilmsList"] as film>
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
                            <td>
                                <form action="/images" method="post" enctype="multipart/form-data">
                                    <input type="file" name="file" accept="image/*" required/>
                                    <input type="hidden" name="id" value="${film.id}">
                                    <input type="submit" value="Upload poster">
                                </form>
                            </td>
                        </#if>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</body>
</html>
