<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<#import "concern/utils.ftl" as u>

<head>
    <#import "/spring.ftl" as spring/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <@u.commonStyles/>
    <style>
        .avatar_img {
            width: 200px;
            height: 200px;
            object-fit: cover;
            border-radius: 50%;
        }

        .card-table {
            max-height: 50%;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
        }
    </style>
    <title><@spring.message code="title.profile"/></title>
</head>

<body>
<#assign size=model["Images"]?sequence?size/>
<#assign user=model["User"]/>
    <@u.navbar/>
    <div class="container py-5">
        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <#if size == 0 >
                            <img src="https://cdn-images-1.medium.com/max/1200/1*OJAuAh5qU6DoJTvt7s7lpw.png"
                                id="avatar-image" class="avatar_img" alt="Default img" />
                        <#else>
                            <img src="images/${model["Images"]?sequence?last.getUuid().toString()}" id="avatar-image"
                                class="avatar_img" alt="Avatar" />
                        </#if>
                        <h5 class="my-3">
                            ${user.getFirstName()} ${user.getLastName()}
                        </h5>
                        <p class="text-muted mb-4">
                            ${user.getEmail()}
                        </p>
                        <div class="d-flex justify-content-center mb-2">
                            <form action="/images" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="mb-3 mw-100">
                                    <input type="hidden" name="id" value="${user.getId()}">
                                    <input type="hidden" name="type" value="avatar">
                                    <input type="file" name="file" accept="image/*" class="form-control" required>
                                </div>
                                <input type="submit" value="<@spring.message code="upload.file"/>" class="btn btn-outline-primary ms-1">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8" style="height: 70vh;">
                <div class="overflow-auto card card-table mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0"><@spring.message code="file.name"/></p>
                            </div>
                            <div class="col-sm-3">
                                <p class="text mb-0"><@spring.message code="size"/></p>
                            </div>
                            <div class="col-sm-3">
                                <p class="text mb-0"><@spring.message code="mime"/></p>
                            </div>
                        </div>
                        <#list model["Images"] as img>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0"><a href="images/${img.getUuid().toString()}" target="_blank">${img.getName()}</a>
                                    </p>
                                </div>
                                <div class="col-sm-3">
                                    <p class="text mb-0">${img.getSizeInStr()}</p>
                                </div>
                                <div class="col-sm-3">
                                    <p class="text mb-0">${img.getMime()}</p>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
                <div class="overflow-auto card card-table mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0"><@spring.message code="date"/></p>
                            </div>
                            <div class="col-sm-3">
                                <p class="text mb-0"><@spring.message code="time"/></p>
                            </div>
                            <div class="col-sm-3">
                                <p class="text mb-0"><@spring.message code="ip"/></p>
                            </div>
                        </div>
                        <#list model["Sessions"] as s>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">${s.getFormattedDate()}</p>
                                </div>
                                <div class="col-sm-3">
                                    <p class="text mb-0">${s.getFormattedTime()}</p>
                                </div>
                                <div class="col-sm-3">
                                    <p class="text mb-0">${s.getIp()}</p>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous">
    </script>
</body>

</html>